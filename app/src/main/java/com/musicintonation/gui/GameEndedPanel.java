//package com.musicintonation.gui;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class GameEndedPanel extends JPanel {
//  JTextArea result = new JTextArea("");
//  JButton button = new JButton("Restart");
//
//  GameEndedPanel(MusicIntonationPanel panel) {
//    setLayout(new BorderLayout());
//    add(result, BorderLayout.NORTH);
//    add(button, BorderLayout.SOUTH);
//    button.addActionListener(e -> {
//      panel.startNewGame();
//    });
//  }
//
//  void updateScore(int score) {
//    result.setText("Game ended!\n Your score is: " + score);
//    result.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
//  }
//}
