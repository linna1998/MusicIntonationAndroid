package com.musicintonation.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

public class MusicIntonationImplementation implements MusicIntonationInterface {
  public static final int NOTE_COUNT = 3;
  private List<Double> noteList = new ArrayList<>(NOTE_COUNT);
  private Instrumental instrument;
  private List<GameChangeListener> gameChangeListeners = new ArrayList<>();
  private int correctNoteIndex;
  private Note correctNote;
  private int level = 0;
  private int healthPoints = 3;
  private int selectNoteIndex;

  public MusicIntonationImplementation(Instrumental instrument) {
    this.instrument = instrument;
    startNewGame();
  }

  @Override
  public void addGameChangeListener(GameChangeListener listener) {
    gameChangeListeners.add(listener);
  }

  @Override
  public void startNewGame() {
    level = 0;
    healthPoints = 3;
    randomHertz();
  }

  @Override
  public void beepNote(int index) {
    instrument.beep(noteList.get(index));
    selectNoteIndex = index;
  }

  @Override
  public void randomHertz() {
    // first, select the correct one
    int correctIndex = new Random().nextInt(Note.values().length);
    correctNote = Note.values()[correctIndex];

    // range (-delta, delta)
    double delta = Double.MAX_VALUE;
    if (correctIndex > 0) {
      delta = min(delta, correctNote.getHertz() - Note.values()[correctIndex - 1].getHertz());
    }
    if (correctIndex + 1 < Note.values().length) {
      delta = min(delta, Note.values()[correctIndex + 1].getHertz() - correctNote.getHertz());
    }
    delta = delta / 2;

    // the difficulty increases with the level
    delta = delta / (level + 1);

    noteList.clear();
    noteList.add(correctNote.getHertz());
    for (int i = 1; i < NOTE_COUNT; i++) {
      noteList.add(correctNote.getHertz() + (Math.random() - 0.5) * delta);
    }

    Collections.shuffle(noteList);

    // set correct note Index
    for (int i = 0; i < NOTE_COUNT; i++) {
      if (noteList.get(i).equals(correctNote.getHertz())) {
        correctNoteIndex = i;
      }
    }

    System.out.println("noteList: " + noteList);
    System.out.println("correctNoteIndex: " + correctNoteIndex);
    System.out.println("correctNoteName: " + correctNote.getName());
  }

  @Override
  public String getNoteName() {
    return correctNote.getName();
  }

  @Override
  public int getLevel() {
    return level;
  }

  @Override
  public int getHealthPoints() {
    return healthPoints;
  }

  @Override
  public void updateStatus() {

    if (selectNoteIndex == correctNoteIndex) {
      level++;
    } else {
      healthPoints--;
    }

    if (healthPoints == 0) {
      for (GameChangeListener listener : gameChangeListeners) {
        listener.gameEnded(level);
      }
    }
  }
}
