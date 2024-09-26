package com.polun.sample.service;

import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.builder.FiniteStateMachineBuilder;
import com.polun.fsm.builder.TriggerBuilder;
import com.polun.fsm.guard.CompositeGuard;
import com.polun.fsm.state.Transition;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.Command;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import com.polun.sample.entity.fsm.action.common.DecreaseQuota;
import com.polun.sample.entity.fsm.action.common.EnterState;
import com.polun.sample.entity.fsm.action.king.AskQuestion;
import com.polun.sample.entity.fsm.action.king.RecordQuestioningScore;
import com.polun.sample.entity.fsm.action.normal.CommentAndTagAllOnlineUsers;
import com.polun.sample.entity.fsm.action.normal.CommentAndTagAuthor;
import com.polun.sample.entity.fsm.action.normal.Converse;
import com.polun.sample.entity.fsm.action.record.DoRecording;
import com.polun.sample.entity.fsm.guard.AnswerCorrectGuard;
import com.polun.sample.entity.fsm.guard.CommandGuard;
import com.polun.sample.entity.fsm.guard.ConverseGuard;
import com.polun.sample.entity.fsm.guard.PermissionGuard;
import com.polun.sample.entity.fsm.guard.RecorderGuard;
import com.polun.sample.entity.fsm.guard.TagGuard;
import com.polun.sample.entity.fsm.guard.TimeElapsedGuard;

public class FiniteStateMachineFacade {

  private final BotManageService botManageService;

  public FiniteStateMachineFacade(Bot bot) {
    this.botManageService = new BotManageService(bot);
  }

  public void subscribeCommunity(Bot bot, FiniteStateMachine<SampleState, SampleEvent> fsm) {
    bot.getCommunity()
        .subscribe(
            event -> {
              if (fsm.canHandle(event)) {
                fsm.handle(event);
              }
            });
  }

  public FiniteStateMachine<SampleState, SampleEvent> getDefaultFSM(Bot bot) {
    Command recordCommand = new Command("record", 1);
    Command kingCommand = new Command("king", 3);
    FiniteStateMachine<SampleState, SampleEvent> fsm =
        new FiniteStateMachineBuilder<SampleState, SampleEvent>()
            .initialState(SampleState.NORMAL)
            .states(
                botManageService.getState(SampleState.NORMAL),
                botManageService.getState(SampleState.KNOWLEDGE_KING),
                botManageService.getState(SampleState.RECORD))
            .transitions(
                Transition.<SampleState, SampleEvent>builder()
                    .source(SampleState.NORMAL)
                    .target(SampleState.RECORD)
                    .event(SampleEvent.NEW_MESSAGE)
                    .guard(new CommandGuard(recordCommand))
                    .action(new DecreaseQuota(recordCommand))
                    .build(),
                Transition.<SampleState, SampleEvent>builder()
                    .source(SampleState.NORMAL)
                    .target(SampleState.KNOWLEDGE_KING)
                    .event(SampleEvent.NEW_MESSAGE)
                    .guard(
                        new CompositeGuard<>(
                            new CommandGuard(kingCommand),
                            new PermissionGuard(bot, true),
                            new TagGuard(bot.getUser().getUserId())))
                    .action(new DecreaseQuota(kingCommand))
                    .build())
            .build();

    botManageService.addTrigger(
        SampleState.KNOWLEDGE_KING,
        new TriggerBuilder<SampleState, SampleEvent>()
            .guard(
                new CommandGuard(new Command("king-stop", 0)),
                new PermissionGuard(bot, true),
                new TagGuard(bot.getUser().getUserId()))
            .event(SampleEvent.NEW_MESSAGE)
            .action(new EnterState<>(fsm, SampleState.NORMAL))
            .build());

    botManageService.addTrigger(
        SampleState.THANKS_FOR_JOINING,
        new TriggerBuilder<SampleState, SampleEvent>()
            .event(SampleEvent.TIME_ELAPSED)
            .guard(new TimeElapsedGuard(20 * 1000L))
            .action(new EnterState<>(fsm, SampleState.NORMAL))
            .build());

    botManageService.addTrigger(
        SampleState.QUESTIONING,
        new TriggerBuilder<SampleState, SampleEvent>()
            .event(SampleEvent.NEW_MESSAGE)
            .guard(new TagGuard(bot.getUser().getUserId()), new AnswerCorrectGuard(bot))
            .action(new RecordQuestioningScore(bot), new AskQuestion(bot))
            .build());

    botManageService.addTrigger(
        SampleState.RECORD,
        new TriggerBuilder<SampleState, SampleEvent>()
            .event(SampleEvent.NEW_MESSAGE)
            .guard(new CommandGuard(new Command("stop-recording", 0)), new RecorderGuard(bot))
            .action(new EnterState<>(fsm, SampleState.NORMAL))
            .build());

    botManageService.addTrigger(
        SampleState.RECORDING,
        new TriggerBuilder<SampleState, SampleEvent>()
            .event(SampleEvent.BROADCASTING_SPEAK)
            .action(new DoRecording(bot))
            .build());

    botManageService.addTrigger(
        SampleState.INTERACTING,
        new TriggerBuilder<SampleState, SampleEvent>()
            .event(SampleEvent.NEW_MESSAGE)
            .guard(new ConverseGuard(bot))
            .action(new Converse(bot, "Hi hi😁", "I like your idea!"))
            .build());

    botManageService.addTrigger(
        SampleState.INTERACTING,
        new TriggerBuilder<SampleState, SampleEvent>()
            .event(SampleEvent.NEW_POST)
            .action(new CommentAndTagAllOnlineUsers(bot, "How do you guys think about it?"))
            .build());

    botManageService.addTrigger(
        SampleState.DEFAULT_CONVERSATION,
        new TriggerBuilder<SampleState, SampleEvent>()
            .event(SampleEvent.NEW_MESSAGE)
            .guard(new ConverseGuard(bot))
            .action(new Converse(bot, "good to hear", "thank you", "How are you"))
            .build());

    botManageService.addTrigger(
        SampleState.DEFAULT_CONVERSATION,
        new TriggerBuilder<SampleState, SampleEvent>()
            .event(SampleEvent.NEW_POST)
            .action(new CommentAndTagAuthor(bot, "Nice post"))
            .build());

    return fsm;
  }
}
