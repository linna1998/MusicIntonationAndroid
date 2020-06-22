package com.musicintonation.database;

import android.os.Build;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.min;

public class Database {
  private static final String NAME = "score";
  private static final int LENGTH = 10;

  private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

  public static void addRecord(String nickname, int score) {
    // Write a message to the database
    String key = mDatabase.child(NAME).push().getKey();
    ScoreRecord scoreRecord = new ScoreRecord(nickname, score);
    Map<String, Object> postValues = scoreRecord.toMap();

    Map<String, Object> childUpdates = new HashMap<>();
    childUpdates.put("/" + NAME + "/" + key, postValues);

    mDatabase.updateChildren(childUpdates);
  }

  public static void setValueEventListener(final AppCompatActivity appCompatActivity, final GridLayout gridLayout) {

    DatabaseReference myRef = mDatabase.child(NAME);

    ValueEventListener valueEventListener = new ValueEventListener() {
      @RequiresApi(api = Build.VERSION_CODES.N)
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<ScoreRecord> scoreRecordList = new ArrayList<>();
        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
          scoreRecordList.add(postSnapshot.getValue(ScoreRecord.class));
        }

        Collections.sort(scoreRecordList);

        // Select top LENGTH
        for (int i = 0; i < min(LENGTH, scoreRecordList.size()); i++) {
          ScoreRecord scoreRecord = scoreRecordList.get(i);
          for (String str : scoreRecord.getRecords()) {
            TextView textView = new TextView(appCompatActivity);
            textView.setText(str);
            gridLayout.addView(textView);
          }
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getMessage());
      }
    };
    myRef.addValueEventListener(valueEventListener);
  }
}
