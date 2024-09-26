package com.polun.fsm;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommunityApplicationTest {

  public static final String PATH = "src/main/resources/testcases/";
  public static final ContextParser<SampleState, Event> CONTEXT_PARSER =
      new BroadcastingSpeakContextParser(
          new LoginContextParser(
              new LogoutContextParser(
                  new NewMessageContextParser(
                      new NewPostContextParser(
                          new StartBroadcastContextParser(
                              new StopBroadcastContextParser(
                                  new TimeElapsedContextParser(null))))))));
  private ByteArrayOutputStream outputStream;
  private PrintStream originalOut;
  private Community community;

  @ParameterizedTest
  @CsvSource({
    "normal.chit-chat",
    "interaction.chit-chat",
    "knowledge-king.normal-play",
    "knowledge-king.stop",
    "knowledge-king.timeout",
    "recording.normal",
    "quota",
  })
  void When_CommandsAreSubmitted_Then_CommunityResponseShouldBeCorrect(String fileName)
      throws Exception {
    // arrange
    List<String> commands = Files.readAllLines(Paths.get(PATH + fileName + ".in"));

    // act
    commands.forEach(community::submit);

    // assert
    List<String> capturedOutput = getCapturedOutput();
    List<String> expectedOutputLines = Files.readAllLines(Paths.get(PATH + fileName + ".out"));
    assertLinesMatch(expectedOutputLines, capturedOutput);
  }

  @BeforeEach
  void setUp() {
    // 設置捕捉 System.out 輸出
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    originalOut = System.out;
    System.setOut(printStream);

    community = new Community(new ChatRoom(), new Forum(), new Broadcast());
    community.setContextParser(CONTEXT_PARSER);

    Bot bot = new Bot();
    bot.login(community);

    FiniteStateMachineFacade facade = new FiniteStateMachineFacade(bot);
    var fsm = facade.getDefaultFSM(bot);
    facade.subscribeCommunity(bot, fsm);
  }

  @AfterEach
  void tearDown() throws Exception {
    // 還原 System.out
    System.setOut(originalOut);
  }

  private List<String> getCapturedOutput() throws UnsupportedEncodingException {
    String[] actualOutputLines =
        outputStream.toString(StandardCharsets.UTF_8.name()).split(System.lineSeparator());
    return List.of(actualOutputLines);
  }
}
