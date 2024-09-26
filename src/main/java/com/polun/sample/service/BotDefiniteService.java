package com.polun.sample.service;

import com.polun.fsm.action.CompositeAction;
import com.polun.fsm.guard.CompositeGuard;
import com.polun.fsm.guard.ReverseGuard;
import com.polun.fsm.state.State;
import com.polun.fsm.state.Transition;
import com.polun.fsm_plugin.CompositeState;
import com.polun.fsm_plugin.StateBuilder;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.Command;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import com.polun.sample.entity.fsm.action.common.SendMessage;
import com.polun.sample.entity.fsm.action.king.AskQuestion;
import com.polun.sample.entity.fsm.action.king.ResetQuestionGroup;
import com.polun.sample.entity.fsm.action.king.SendQuestioningResult;
import com.polun.sample.entity.fsm.action.normal.InitializeNormalState;
import com.polun.sample.entity.fsm.action.record.InitializeRecordState;
import com.polun.sample.entity.fsm.action.record.ReplayRecords;
import com.polun.sample.entity.fsm.action.record.StartRecording;
import com.polun.sample.entity.fsm.guard.CommandGuard;
import com.polun.sample.entity.fsm.guard.OnlineUserLimitReached;
import com.polun.sample.entity.fsm.guard.QuestionGroupAllAnsweredGuard;
import com.polun.sample.entity.fsm.guard.TagGuard;
import com.polun.sample.entity.fsm.guard.TimeElapsedGuard;

public class BotDefiniteService {
  private final Bot bot;

  public BotDefiniteService(Bot bot) {
    this.bot = bot;
  }

  @SuppressWarnings("unchecked")
  public State<SampleState, Event> getRecordState(State<SampleState, Event>... states) {
    InitializeRecordState entryAction = new InitializeRecordState(bot);
    CompositeState<SampleState, Event> state =
        new StateBuilder<SampleState, Event>()
            .id(SampleState.RECORD)
            .entryAction(new CompositeAction<>(new StartRecording(bot), entryAction))
            .initialState(SampleState.RECORDING)
            .states(states)
            .transitions(
                Transition.<SampleState, Event>builder()
                    .source(SampleState.WAITING)
                    .target(SampleState.RECORDING)
                    .event(Event.START_BROADCAST)
                    .build(),
                Transition.<SampleState, Event>builder()
                    .source(SampleState.RECORDING)
                    .target(SampleState.WAITING)
                    .event(Event.STOP_BROADCAST)
                    .build())
            .buildCompositeState();
    entryAction.setFsm(state);
    return state;
  }

  public State<SampleState, Event> getWaitingState() {
    return new StateBuilder<SampleState, Event>().id(SampleState.WAITING).build();
  }

  @SuppressWarnings("unchecked")
  public State<SampleState, Event> getRecordingState() {
    return new StateBuilder<SampleState, Event>()
        .id(SampleState.RECORDING)
        .entryAction(new StartRecording(bot))
        .exitAction(new ReplayRecords(bot))
        .build();
  }

  @SuppressWarnings("unchecked")
  public State<SampleState, Event> getKnowledgeKing(State<SampleState, Event>... states) {
    return new StateBuilder<SampleState, Event>()
        .id(SampleState.KNOWLEDGE_KING)
        .initialState(SampleState.QUESTIONING)
        .entryAction(new SendMessage(bot, "KnowledgeKing is started!"))
        .states(states)
        .transitions(
            Transition.<SampleState, Event>builder()
                .source(SampleState.QUESTIONING)
                .target(SampleState.THANKS_FOR_JOINING)
                .event(Event.TIME_ELAPSED)
                .guard(new TimeElapsedGuard(60 * 60 * 1000L))
                .build(),
            Transition.<SampleState, Event>builder()
                .source(SampleState.QUESTIONING)
                .target(SampleState.THANKS_FOR_JOINING)
                .event(Event.NEW_MESSAGE)
                .guard(new QuestionGroupAllAnsweredGuard(bot))
                .build(),
            Transition.<SampleState, Event>builder()
                .source(SampleState.THANKS_FOR_JOINING)
                .target(SampleState.QUESTIONING)
                .event(Event.NEW_MESSAGE)
                .guard(
                    new CompositeGuard<>(
                        new CommandGuard(new Command("play again", 5)),
                        new TagGuard(bot.getUser().getUserId())))
                .action(new SendMessage(bot, "KnowledgeKing is gonna start again!"))
                .build())
        .buildCompositeState();
  }

  public State<SampleState, Event> getThanksForJoining() {
    return new StateBuilder<SampleState, Event>()
        .id(SampleState.THANKS_FOR_JOINING)
        .entryAction((new SendQuestioningResult(bot)))
        .build();
  }

  @SuppressWarnings("unchecked")
  public State<SampleState, Event> getQuestioning() {
    return new StateBuilder<SampleState, Event>()
        .id(SampleState.QUESTIONING)
        .entryAction(new CompositeAction<>(new ResetQuestionGroup(bot), new AskQuestion(bot)))
        .build();
  }

  @SuppressWarnings("unchecked")
  public State<SampleState, Event> getNormaState(State<SampleState, Event>... states) {
    OnlineUserLimitReached onlineUserLimitReached = new OnlineUserLimitReached(bot, 10);
    InitializeNormalState entryAction = new InitializeNormalState(onlineUserLimitReached);
    var state =
        new StateBuilder<SampleState, Event>()
            .id(SampleState.NORMAL)
            .initialState(SampleState.DEFAULT_CONVERSATION)
            .entryAction(entryAction)
            .states(states)
            .transitions(
                Transition.<SampleState, Event>builder()
                    .source(SampleState.INTERACTING)
                    .target(SampleState.DEFAULT_CONVERSATION)
                    .event(Event.LOGOUT)
                    .guard(new ReverseGuard<>(onlineUserLimitReached))
                    .build(),
                Transition.<SampleState, Event>builder()
                    .source(SampleState.DEFAULT_CONVERSATION)
                    .target(SampleState.INTERACTING)
                    .event(Event.LOGIN)
                    .guard(onlineUserLimitReached)
                    .build())
            .buildCompositeState();
    entryAction.setFsm(state);
    return state;
  }

  @SuppressWarnings("unchecked")
  public State<SampleState, Event> getInteracting() {
    return new StateBuilder<SampleState, Event>().id(SampleState.INTERACTING).build();
  }

  @SuppressWarnings("unchecked")
  public State<SampleState, Event> getDefaultConversation() {
    return new StateBuilder<SampleState, Event>().id(SampleState.DEFAULT_CONVERSATION).build();
  }
}
