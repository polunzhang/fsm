package com.polun.sample.entity.fsm.action.king;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.bot.QuestionGroup;
import com.polun.sample.entity.community.Community;
import com.polun.sample.entity.community.broadcast.Broadcast;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class SendQuestioningResult implements Action<SampleState, SampleEvent> {

  private final Bot bot;

  public SendQuestioningResult(Bot bot) {
    this.bot = bot;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    QuestionGroup questionGroup = bot.getQuestionGroup();

    String result =
        questionGroup
            .getWinner()
            .map(winner -> String.format("The winner is %s", winner.value()))
            .orElse("Tie!");
    
    Community community = bot.getCommunity();
    Broadcast broadcast = community.getBroadcast();
    if (broadcast.isBroadcasting()) {
      bot.sendMessage(result);
    } else {
      community.goBroadcasting(bot.getUser().getUserId());
      bot.sendSpeak(result);
      broadcast.stopBroadCasting(bot.getUser().getUserId());
    }
  }
}
