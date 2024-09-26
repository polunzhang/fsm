package com.polun.sample;

import com.polun.sample.adapter.BroadcastingSpeakContextParser;
import com.polun.sample.adapter.ContextParser;
import com.polun.sample.adapter.LoginContextParser;
import com.polun.sample.adapter.LogoutContextParser;
import com.polun.sample.adapter.NewMessageContextParser;
import com.polun.sample.adapter.NewPostContextParser;
import com.polun.sample.adapter.StartBroadcastContextParser;
import com.polun.sample.adapter.StopBroadcastContextParser;
import com.polun.sample.adapter.TimeElapsedContextParser;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.Community;
import com.polun.sample.entity.community.broadcast.Broadcast;
import com.polun.sample.entity.community.chatroom.ChatRoom;
import com.polun.sample.entity.community.forum.Forum;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import com.polun.sample.service.FiniteStateMachineFacade;

public class CommunityApplication {

  public static final ContextParser<SampleState, Event> CONTEXT_PARSER =
      new BroadcastingSpeakContextParser(
          new LoginContextParser(
              new LogoutContextParser(
                  new NewMessageContextParser(
                      new NewPostContextParser(
                          new StartBroadcastContextParser(
                              new StopBroadcastContextParser(
                                  new TimeElapsedContextParser(null))))))));

  public static void main(String[] args) {
    Community community = new Community(new ChatRoom(), new Forum(), new Broadcast());
    community.setContextParser(CONTEXT_PARSER);

    Bot bot = new Bot();
    bot.login(community);

    FiniteStateMachineFacade facade = new FiniteStateMachineFacade(bot);
    var fsm = facade.getDefaultFSM(bot);
    facade.subscribeCommunity(bot, fsm);
  }
}
