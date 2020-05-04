package com.musicintonation.core;

public interface MusicIntonationInterface {
  void addGameChangeListener(GameChangeListener listener);

  void startNewGame();

  void beepNote(int index);

  void randomHertz();

  String getNoteName();

  int getLevel();

  int getHealthPoints();

  void updateStatus();
}
