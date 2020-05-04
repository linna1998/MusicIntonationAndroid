package com.musicintonation.gui;

import com.musicintonation.core.MusicIntonationInterface;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

  MusicIntonationInterface musicIntonation;
  ControlPanel controlPanel;
  MusicPanel musicPanel;

  GamePanel(MusicIntonationInterface musicIntonation) {
    this.musicIntonation = musicIntonation;
    setLayout(new BorderLayout());

    controlPanel = new ControlPanel(musicIntonation);
    add(controlPanel, BorderLayout.NORTH);

    musicPanel = new MusicPanel(musicIntonation);
    add(musicPanel, BorderLayout.CENTER);

    // add the confirm button
    JButton button = new JButton("Confirm");
    button.addActionListener(e -> {
      musicIntonation.updateStatus();
      musicIntonation.randomHertz();
      updateStatus();
    });
    add(button, BorderLayout.SOUTH);
  }

  void updateStatus() {
    controlPanel.updateStatus(musicIntonation);
    musicPanel.clearSelections();
  }
}
