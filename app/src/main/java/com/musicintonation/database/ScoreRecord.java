package com.musicintonation.database;

import java.util.Arrays;
import java.util.List;

public class ScoreRecord {

  public static final int COLUMN_COUNT = 2;

  private String nickName;
  private int score;

  public ScoreRecord(String nickName, int score) {
    this.nickName = nickName;
    this.score = score;
  }

  public static List<String> getHeader() {
    return Arrays.asList("Nickname", "Score");
  }

  public List<String> getRecords() {
    return Arrays.asList(nickName, String.valueOf(score));
  }
}
