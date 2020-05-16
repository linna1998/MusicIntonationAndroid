package com.musicintonation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.musicintonation.database.Database;

public class EndGameActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.end_game_activity);

    final TextView score = findViewById(R.id.score);
    final int scoreValue = getIntent().getIntExtra("SCORE", 0);
    score.setText(String.valueOf(scoreValue));

    Button postScoreboard = findViewById(R.id.post_to_board_btn);
    postScoreboard.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        EditText nicknameView = findViewById(R.id.nickname_edit_text);
        String nickname = nicknameView.getText().toString();

        Database.addRecord(nickname, scoreValue);
      }
    });

    Button scoreboard = findViewById(R.id.scoreboard_btn);
    scoreboard.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(EndGameActivity.this, ScoreBoardActivity.class);
        startActivity(intent);
      }
    });

    Button restart = findViewById(R.id.restart_btn);
    restart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(EndGameActivity.this, StartGameActivity.class);
        startActivity(intent);
      }
    });
  }

}