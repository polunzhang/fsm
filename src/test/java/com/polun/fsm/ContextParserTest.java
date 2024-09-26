package com.polun.fsm;

import com.polun.sample.adapter.BroadcastingSpeakContextParser;
import com.polun.sample.adapter.ContextParser;
import com.polun.sample.adapter.LoginContextParser;
import com.polun.sample.adapter.LogoutContextParser;
import com.polun.sample.adapter.NewMessageContextParser;
import com.polun.sample.adapter.NewPostContextParser;
import com.polun.sample.adapter.StartBroadcastContextParser;
import com.polun.sample.adapter.StopBroadcastContextParser;
import com.polun.sample.adapter.TimeElapsedContextParser;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// main one
// use when writing exception tests
// for Iterable/Array assertions
// for floating number assertions
// use with Condition
// use with File assertions

class ContextParserTest {

  public static final String PATH = "src/main/resources/testcases/";
  private ContextParser<SampleState, Event> parser;

  @BeforeEach
  void setUp() {
    parser =
        new BroadcastingSpeakContextParser(
            new LoginContextParser(
                new LogoutContextParser(
                    new NewMessageContextParser(
                        new NewPostContextParser(
                            new StartBroadcastContextParser(
                                new StopBroadcastContextParser(
                                    new TimeElapsedContextParser(null))))))));
  }

  @ParameterizedTest
  @CsvSource({
      "interaction.chit-chat",
      "knowledge-king.normal-play",
      "knowledge-king.stop",
      "knowledge-king.timeout",
      "normal.chit-chat",
      "quota",
      "recording.normal"
  })
  void when_CommandsAreHandled_then_ObjectsShouldBeParsedCorrectly(String fileName)
      throws Exception {
    // arrange
    List<String> commands = Files.readAllLines(Paths.get(PATH + fileName + ".in"));

    // act
    commands.stream().map(parser::handle).collect(Collectors.toList());

    // assert
  }
}
