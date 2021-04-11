package com.example.thoughtwell;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //added a test comment
    final String TAG = "LOGIN_ACTIVITY";

    // for signing in the user anonymously
    private FirebaseAuth mAuth;

    private Button btnSubmit;
    private EditText etEmail;
    private EditText etPassword;


    @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG, "entered the 'onCreate' function");

        // initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // check if user is already signed in
        // if so, go to MainActivity and finish
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(this, "User is already signed in", Toast.LENGTH_SHORT).show();
            goMainActivity();
        }

        // initializing our inputs from user
        btnSubmit = findViewById(R.id.btnSignup);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            // this is an anonymous class of View.OnClickListener
            // it allows us to override method onClick with our own implementation.
            // good for one time use since it takes less work than creating another class
            // that extends View.OnClickListener to then only override a single method (onClick)
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "button tapped", Toast.LENGTH_SHORT).show();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                Log.i(TAG, "email: " + email);
                Log.i(TAG, "password: " + password);


            }

        });

    }



    // use the intent system in android to navigate to the next activity
    private void goMainActivity() {
        // 'this' <- the context, which is referring to 'LoginActivity' which is an instance of a context
        // MainActivity.class <- the class where you want to navigate to
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }



    // TESTING APP LIFELINE
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "entered the 'onStart' function");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "entered the 'onResume' function");
    }

}
