package com.musicintonation.gui;

import core.GameChangeListener;
import core.MusicIntonationInterface;

import javax.swing.*;

public class MusicIntonationPanel extends JPanel implements GameChangeListener {

  private MusicIntonationInterface musicIntonation;
  private GamePanel gamePanel;
  private GameEndedPanel gameEndedPanel;

  MusicIntonationPanel(MusicIntonationInterface musicIntonationInterface) {
    this.musicIntonation = musicIntonationInterface;
    musicIntonationInterface.addGameChangeListener(this);

    gamePanel = new GamePanel(musicIntonationInterface);
    gameEndedPanel = new GameEndedPanel(this);

    add(gamePanel);
    add(gameEndedPanel);
    startNewGame();
  }

  void startNewGame() {
    musicIntonation.startNewGame();
    gamePanel.updateStatus();
    gamePanel.setVisible(true);
    gamePanel.setEnabled(true);
    gameEndedPanel.setVisible(false);
    gameEndedPanel.setEnabled(false);
  }

  @Override
  public void gameEnded() {
    gameEndedPanel.updateScore(musicIntonation.getLevel());
    gamePanel.setVisible(false);
    gamePanel.setEnabled(false);
    gameEndedPanel.setVisible(true);
    gameEndedPanel.setEnabled(true);
  }
}
