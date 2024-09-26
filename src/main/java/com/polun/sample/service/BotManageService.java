package com.polun.sample.service;

import com.polun.fsm.Trigger;
import com.polun.fsm.state.State;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import java.util.HashMap;
import java.util.Map;

public class BotManageService {
  private final Map<SampleState, State<SampleState, Event>> stateMap = new HashMap<>();
  private final BotDefiniteService botDefiniteService;

  public BotManageService(Bot bot) {
    this.botDefiniteService = new BotDefiniteService(bot);

    State<SampleState, Event> interacting = botDefiniteService.getInteracting();
    State<SampleState, Event> defaultConversation = botDefiniteService.getDefaultConversation();
    addState(interacting);
    addState(defaultConversation);
    addState(botDefiniteService.getNormaState(interacting, defaultConversation));

    State<SampleState, Event> waitingState = botDefiniteService.getWaitingState();
    State<SampleState, Event> recordingState = botDefiniteService.getRecordingState();
    addState(waitingState);
    addState(recordingState);
    addState(botDefiniteService.getRecordState(waitingState, recordingState));

    State<SampleState, Event> questioning = botDefiniteService.getQuestioning();
    State<SampleState, Event> thanksForJoining = botDefiniteService.getThanksForJoining();
    addState(questioning);
    addState(thanksForJoining);
    addState(botDefiniteService.getKnowledgeKing(questioning, thanksForJoining));
  }

  public void addTrigger(SampleState sampleState, Trigger<SampleState, Event> trigger) {
    stateMap.get(sampleState).addTrigger(trigger);
  }

  public void addState(State<SampleState, Event> state) {
    stateMap.put(state.getId(), state);
  }

  public State<SampleState, Event> getState(SampleState sampleState) {
    return stateMap.get(sampleState);
  }
}
