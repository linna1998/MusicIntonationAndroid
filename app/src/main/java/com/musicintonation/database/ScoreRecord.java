package com.musicintonation.database;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreRecord implements Comparable<ScoreRecord> {

  public static final int COLUMN_COUNT = 2;

  private static final String NICKNAME = "nickname";
  private static final String SCORE = "score";

  private String nickname;
  private int score;

  private ScoreRecord() {
  }

  public ScoreRecord(String nickname, int score) {
    this.nickname = nickname;
    this.score = score;
  }

  public static List<String> getHeader() {
    return Arrays.asList(NICKNAME, SCORE);
  }

  public String getNickname() {
    return nickname;
  }

  public int getScore() {
    return score;
  }

  public List<String> getRecords() {
    return Arrays.asList(nickname, String.valueOf(score));
  }

  public Map<String, Object> toMap() {
    HashMap<String, Object> result = new HashMap<>();
    result.put(NICKNAME, nickname);
    result.put(SCORE, score);

    return result;
  }

  @Override
  public String toString() {
    return "ScoreRecord{" +
            "nickname='" + nickname + '\'' +
            ", score=" + score +
            '}';
  }

  @Override
  public int compareTo(ScoreRecord o2) {
    if (score < o2.score) {
      return -1;
    }
    if (score > o2.score) {
      return 1;
    }
    return nickname.compareTo(o2.nickname);
  }
}
