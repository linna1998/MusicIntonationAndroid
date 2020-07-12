package com.musicintonation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.musicintonation.core.GeneralInstrumentAndroid;
import com.musicintonation.core.Instrumental;
import com.musicintonation.core.Note;

public class StartGameActivity extends AppCompatActivity {

  private static final int RC_SIGN_IN = 9001;
  //Firebase Auth
  private FirebaseAuth mAuth;

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

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();
    final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    mAuth = FirebaseAuth.getInstance();

    findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
    // Check if user is signed in (non-null) and update UI accordingly.
    FirebaseUser currentUser = mAuth.getCurrentUser();
    updateUI(currentUser);
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      try {
        // Google Sign In was successful, authenticate with Firebase
        GoogleSignInAccount account = task.getResult(ApiException.class);
        firebaseAuthWithGoogle(account.getIdToken());
      } catch (ApiException e) {
        System.out.println("ApiException:" + e);
        updateUI(null);
      }
    }
  }


  private void firebaseAuthWithGoogle(String idToken) {
    AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  // Sign in success, update UI with the signed-in user's information
                  FirebaseUser user = mAuth.getCurrentUser();
                  updateUI(user);
                } else {
                  // If sign in fails, display a message to the user.
                  updateUI(null);
                }

              }
            });
  }

  private void updateUI(FirebaseUser user) {
    if (user != null) {
      TextView welcome = findViewById(R.id.welcome);
      welcome.setText("Welcome " + user.getDisplayName() + " !");
    } else {
      System.out.println("User is null!");
    }
  }
}
