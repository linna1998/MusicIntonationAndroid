package com.musicintonation.gui;

import core.MusicIntonationInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static core.MusicIntonationImplementation.NOTE_COUNT;

public class MusicPanel extends JPanel {
  private List<JButton> buttonList = new ArrayList<>();
  private Image naturalNote;
  private Image selectedNote;

  MusicPanel(MusicIntonationInterface musicIntonation) {
    setLayout(new FlowLayout());

    try {
      BufferedImage naturalImage = ImageIO.read(new File("src/main/resources/music_note.png"));
      naturalNote = naturalImage.getScaledInstance(116, 200, Image.SCALE_SMOOTH);
      BufferedImage selectedImage = ImageIO.read(new File("src/main/resources/selected_note.png"));
      selectedNote = selectedImage.getScaledInstance(116, 200, Image.SCALE_SMOOTH);
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < NOTE_COUNT; i++) {
      JButton button = new JButton();
      button.setIcon(new ImageIcon(naturalNote));
      int finalI = i;
      button.addActionListener(e -> {
        clearSelections();
        button.setSelected(true);
        button.setIcon(new ImageIcon(selectedNote));

        new Thread(() -> musicIntonation.beepNote(finalI)).start();
      });
      add(button);
      buttonList.add(button);
    }
  }

  void clearSelections() {
    for (JButton button : buttonList) {
      button.setIcon(new ImageIcon(naturalNote));
    }
  }
}
