package com.musicintonation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.musicintonation.core.GeneralInstrumentAndroid;
import com.musicintonation.core.Instrumental;
import com.musicintonation.core.MusicIntonationImplementation;
import com.musicintonation.core.MusicIntonationInterface;

import java.util.Arrays;
import java.util.List;

import static com.musicintonation.core.MusicIntonationImplementation.NOTE_COUNT;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Instrumental instrumental = new GeneralInstrumentAndroid();
    final MusicIntonationInterface musicIntonation = new MusicIntonationImplementation(instrumental);

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
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
