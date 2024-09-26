package com.polun.sample.entity.bot;

import com.polun.sample.entity.community.UserId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuestionGroup {
  private final List<Question> questions;
  private final Map<Question, UserId> answerRecord;
  private int currentIndex = 0;

  public QuestionGroup(List<Question> questions) {
    this.questions = new ArrayList<>(questions);
    this.answerRecord = new HashMap<>();
  }

  public String[] getQuestionDescriptions() {
    List<String> descriptions = new ArrayList<>();
    Question question = getCurrentQuestion();
    descriptions.add(String.format("%d. %s", currentIndex, question.getValue()));
    descriptions.addAll(question.getOptions().stream().map(Option::value).toList());
    return descriptions.toArray(String[]::new);
  }

  public void recordScore(UserId userId) {
    Question question = getCurrentQuestion();
    answerRecord.put(question, userId);
    currentIndex++;
  }

  private Question getCurrentQuestion() {
    return questions.get(currentIndex);
  }

  public boolean isCorrect(char answer) {
    return getCurrentQuestion().isCorrect(answer);
  }

  public boolean allAnswered() {
    return answerRecord.keySet().containsAll(questions);
  }

  public void reset() {
    answerRecord.clear();
    currentIndex = 0;
  }

  public Optional<UserId> getWinner() {
    Map<UserId, Integer> scoreMap = new HashMap<>();

    for (UserId userId : answerRecord.values()) {
      scoreMap.put(userId, scoreMap.getOrDefault(userId, 0) + 1);
    }

    UserId winner = null;
    int maxScore = 0;
    boolean isTie = false;

    for (Map.Entry<UserId, Integer> entry : scoreMap.entrySet()) {
      if (entry.getValue() > maxScore) {
        winner = entry.getKey();
        maxScore = entry.getValue();
        isTie = false;
      } else if (entry.getValue() == maxScore) {
        isTie = true;
      }
    }

    return Optional.ofNullable(isTie ? null : winner);
  }
}
