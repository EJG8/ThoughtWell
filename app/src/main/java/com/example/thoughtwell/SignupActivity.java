package com.example.thoughtwell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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

public class SignupActivity extends AppCompatActivity {

    //added a test comment
    final String TAG = "SIGNUP_ACTIVITY";

    // for signing in the user anonymously
    private FirebaseAuth mAuth;

    private Button btnSubmit;
    private EditText etEmail;
    private EditText etPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.i(TAG, "entered the 'onCreate' function");

        // initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // check if user is already signed in
        // if so, go to MainActivity and finish
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // if this doesn't seem to work right, then try moving it into a onStart method
        if (currentUser != null) {
            Toast.makeText(this, "User is already signed in", Toast.LENGTH_SHORT).show();
            goMainActivity();
        }

        // initializing our inputs from user
        btnSubmit = findViewById(R.id.btnSignup);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        // this code performs the functions to sign in the user after they
        // give their email and password
        //TODO: provide error and bad input checking, notify user if empty email/pw
        // if badly formatted email was entered and if incorrect email or password is given
        btnSubmit.setOnClickListener(new OnClickListener() {
            // this is an anonymous class of View.OnClickListener
            // it allows us to override method onClick with our own implementation.
            // good for one time use since it takes less work than creating another class
            // that extends View.OnClickListener to then only override a single method (onClick)
            @Override
            public void onClick(View v) {
                Toast.makeText(SignupActivity.this, "button tapped", Toast.LENGTH_SHORT).show();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                Log.i(TAG, "email: " + email);
                Log.i(TAG, "password: " + password);

                // create an account with the provided credentials
                mAuth.createUserWithEmailAndPassword(email, password)
                        // you have to specify 'SignupActivity.this' or else it throws an error;
                        // if you only use 'this' it will use 'btnSubmit.setOnClickListener'
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    Log.i(TAG, "createUserWithEmail:success");
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }


    //use the intent system in android to navigate to the next activity
    private void goMainActivity() {
        // 'this' <- the context, which is referring to 'SignupActivity' which is an instance of a context
        // MainActivity.class <- the class where you want to navigate to
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }




        // TESTING APP LIFELINE
        @Override
        protected void onStart () {
            super.onStart();
            Log.i(TAG, "entered the 'onStart' function");
        }

        @Override
        protected void onResume () {
            super.onResume();
            Log.i(TAG, "entered the 'onResume' function");
        }
}
