package com.musicintonation.gui;

import core.MusicIntonationInterface;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
  private JLabel levelName = new JLabel("level: ");
  private JTextArea level;
  private JLabel noteName = new JLabel("note: ");
  private JTextArea note;
  private JLabel healthPointsName = new JLabel("HP: ");
  private JTextArea healthPoints;

  ControlPanel(MusicIntonationInterface musicIntonation) {
    setLayout(new FlowLayout());

    level = new JTextArea(String.valueOf(musicIntonation.getLevel()));
    note = new JTextArea(musicIntonation.getNoteName());
    healthPoints = new JTextArea(String.valueOf(musicIntonation.getHealthPoints()));

    add(levelName);
    add(level);
    add(noteName);
    add(note);
    add(healthPointsName);
    add(healthPoints);
  }

  void updateStatus(MusicIntonationInterface musicIntonation) {
    level.setText(String.valueOf(musicIntonation.getLevel()));
    note.setText(musicIntonation.getNoteName());
    healthPoints.setText(String.valueOf(musicIntonation.getHealthPoints()));
  }
}
