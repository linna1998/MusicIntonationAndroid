package com.musicintonation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.musicintonation.core.ScoreRecord;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.score_board_activity);

    GridLayout gridLayout = findViewById(R.id.score_board);
    gridLayout.setColumnCount(ScoreRecord.COLUMN_COUNT);

    // add the score board header
    for (String str : ScoreRecord.getHeader()) {
      TextView textView = new TextView(this);
      textView.setText(str);
      gridLayout.addView(textView);
    }

    List<ScoreRecord> records = new ArrayList<>();

    records.add(new ScoreRecord("Lina", 0));
    records.add(new ScoreRecord("Diana", 4));

    for (ScoreRecord scoreRecord : records) {
      for (String str : scoreRecord.getRecords()) {
        TextView textView = new TextView(this);
        textView.setText(str);
        gridLayout.addView(textView);
      }
    }

    Button restart = findViewById(R.id.restart_btn_in_scoreboard);
    restart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(ScoreBoardActivity.this, StartGameActivity.class);
        startActivity(intent);
      }
    });
  }
}
