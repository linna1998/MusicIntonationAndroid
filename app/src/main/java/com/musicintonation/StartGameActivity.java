package com.musicintonation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.musicintonation.core.GeneralInstrumentAndroid;
import com.musicintonation.core.Instrumental;
import com.musicintonation.core.Note;

public class StartGameActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.start_game_activity);

    Button start = findViewById(R.id.start_btn);
    start.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(StartGameActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });

    GridLayout gridLayout = findViewById(R.id.center_notes);
    final Instrumental instrumental = new GeneralInstrumentAndroid();

    // add sample buttons for all notes
    for (final Note note : Note.values()) {
      Button button = new Button(this);
      button.setText(note.getName());
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          instrumental.beep(note.getHertz());
        }
      });
      gridLayout.addView(button);
    }
  }
}
