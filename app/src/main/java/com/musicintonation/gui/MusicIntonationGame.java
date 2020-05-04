package com.musicintonation.gui;

import core.GeneralInstrument;
import core.Instrumental;
import core.MusicIntonationImplementation;
import core.MusicIntonationInterface;

import javax.swing.*;

public class MusicIntonationGame extends JPanel {

  MusicIntonationGame() {
    Instrumental instrumental = new GeneralInstrument();
    MusicIntonationInterface musicIntonation = new MusicIntonationImplementation(instrumental);
    MusicIntonationPanel panel = new MusicIntonationPanel(musicIntonation);

    add(panel);
    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      // add frame and set its closing operation
      JFrame frame = new JFrame("Music Intonation");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      frame.add(new MusicIntonationGame());

      //display the JFrame
      frame.pack();
      frame.setResizable(true);
      frame.setVisible(true);
    });
  }
}
