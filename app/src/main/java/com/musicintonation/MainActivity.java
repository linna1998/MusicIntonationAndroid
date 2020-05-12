package com.musicintonation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.musicintonation.core.GameChangeListener;
import com.musicintonation.core.GeneralInstrumentAndroid;
import com.musicintonation.core.Instrumental;
import com.musicintonation.core.MusicIntonationImplementation;
import com.musicintonation.core.MusicIntonationInterface;

import java.util.Arrays;
import java.util.List;

import static com.musicintonation.core.MusicIntonationImplementation.NOTE_COUNT;

public class MainActivity extends AppCompatActivity implements GameChangeListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Instrumental instrumental = new GeneralInstrumentAndroid();
    final MusicIntonationInterface musicIntonation = new MusicIntonationImplementation(instrumental);
    musicIntonation.addGameChangeListener(this);

    musicIntonation.startNewGame();

    final TextView level = findViewById(R.id.level);
    final TextView name = findViewById(R.id.name);
    final TextView hp = findViewById(R.id.hp);

    level.setText(String.valueOf(musicIntonation.getLevel()));
    name.setText(musicIntonation.getNoteName());
    hp.setText(String.valueOf(musicIntonation.getHealthPoints()));

    final List<ImageButton> noteList = Arrays.asList(
            (ImageButton) findViewById(R.id.click_btn1),
            (ImageButton) findViewById(R.id.click_btn2),
            (ImageButton) findViewById(R.id.click_btn3));

    for (int i = 0; i < NOTE_COUNT; i++) {
      final ImageButton button = noteList.get(i);
      final int finalI = i;
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          view.setFocusable(true);
          view.requestFocus();
          view.requestFocusFromTouch();

          new Thread(new Runnable() {
            @Override
            public void run() {
              System.out.println("I beep " + finalI);
              musicIntonation.beepNote(finalI);
            }
          }).start();
        }
      });
    }


    Button confirm = findViewById(R.id.click_btn);
    confirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        musicIntonation.updateStatus();
        musicIntonation.randomHertz();

        level.setText(String.valueOf(musicIntonation.getLevel()));
        name.setText(musicIntonation.getNoteName());
        hp.setText(String.valueOf(musicIntonation.getHealthPoints()));
      }
    });

  }

  @Override
  public void gameEnded(int score) {
    System.out.println("score in main activity: " + score);
    Intent intent = new Intent();
    intent.setClass(MainActivity.this, EndGameActivity.class);
    intent.putExtra("SCORE", score);
    startActivity(intent);
  }
}
