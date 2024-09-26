package com.polun.sample.service;

import com.polun.fsm.Trigger;
import com.polun.fsm.state.State;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import java.util.HashMap;
import java.util.Map;

public class BotManageService {
  private final Map<SampleState, State<SampleState, SampleEvent>> stateMap = new HashMap<>();
  private final BotDefiniteService botDefiniteService;

  public BotManageService(Bot bot) {
    this.botDefiniteService = new BotDefiniteService(bot);

    State<SampleState, SampleEvent> interacting = botDefiniteService.getInteracting();
    State<SampleState, SampleEvent> defaultConversation = botDefiniteService.getDefaultConversation();
    addState(interacting);
    addState(defaultConversation);
    addState(botDefiniteService.getNormaState(interacting, defaultConversation));

    State<SampleState, SampleEvent> waitingState = botDefiniteService.getWaitingState();
    State<SampleState, SampleEvent> recordingState = botDefiniteService.getRecordingState();
    addState(waitingState);
    addState(recordingState);
    addState(botDefiniteService.getRecordState(waitingState, recordingState));

    State<SampleState, SampleEvent> questioning = botDefiniteService.getQuestioning();
    State<SampleState, SampleEvent> thanksForJoining = botDefiniteService.getThanksForJoining();
    addState(questioning);
    addState(thanksForJoining);
    addState(botDefiniteService.getKnowledgeKing(questioning, thanksForJoining));
  }

  public void addTrigger(SampleState sampleState, Trigger<SampleState, SampleEvent> trigger) {
    stateMap.get(sampleState).addTrigger(trigger);
  }

  public void addState(State<SampleState, SampleEvent> state) {
    stateMap.put(state.getId(), state);
  }

  public State<SampleState, SampleEvent> getState(SampleState sampleState) {
    return stateMap.get(sampleState);
  }
}
