package com.example.thoughtwell;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //added a test comment
    final String TAG = "LOGIN_ACTIVITY";

    // for signing in the user anonymously
    private FirebaseAuth mAuth;

    private Button btnSignup;
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;

    // holds user input strings, used as arguments in login and signup firebase functions
    private String inputEmail;
    private String inputPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG, "entered the 'onCreate' function");

        // initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // check if user is already signed in
        // if so, go to MainActivity and finish
        // otherwise, below is code that will let the user either:
        // sign in or create an account
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // if this doesn't seem to work right, then try moving it into a onStart method
        if (currentUser != null) {
            Toast.makeText(this, "User is already signed in", Toast.LENGTH_SHORT).show();
            goMainActivity();
        }

        // initializing our inputs from user
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);


        btnSignup.setOnClickListener(new OnClickListener() {
            // this is an anonymous class of View.OnClickListener
            // it allows us to override method onClick with our own implementation.
            // good for one time use since it takes less work than creating another class
            // that extends View.OnClickListener to then only override a single method (onClick)
            @Override
            public void onClick(View v) {
                // fails if no email or password is given
                if (checkAndGetInput()) {
                    // create an account with the provided credentials
                    mAuth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                            // you have to specify 'LoginActivity.this' or else it throws an error;
                            // if you only use 'this' it will use 'btnSignup.setOnClickListener'
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success
                                        Log.i(TAG, "createUserWithEmail:success");
                                        Toast.makeText(LoginActivity.this,
                                                "Account created!",
                                                Toast.LENGTH_SHORT).show();
                                        // after user creates an account successfully, send them to
                                        // MainActivity
                                        //TODO: check if you need to write code to automatically sign in
                                        // the user at this point BEFORE sending to MainAcitvity
                                        goMainActivity();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this,
                                                "Account creation failed",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Email or password is empty, please enter something",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // fails if no email or password is given
                if(checkAndGetInput()){
                    mAuth.signInWithEmailAndPassword(inputEmail, inputPassword).
                            addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.w(TAG, "signInWithEmail:success");
                                        Toast.makeText(LoginActivity.this,
                                                "Signed in successfully!",
                                                Toast.LENGTH_SHORT).show();
                                        goMainActivity();
                                    } else {
                                        //TODO: add a nicer alert to let user know of an error
                                        try {
                                            throw task.getException();
                                        }
                                        catch (Exception e) {
                                            Toast.makeText(LoginActivity.this,
                                                    e.getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        }

                                        Log.w(TAG, "signInWithEmail:failed", task.getException());
//                                        Toast.makeText(LoginActivity.this,
//                                                "Signed in failed",
//                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    //use the intent system in android to navigate to the next activity
    private void goMainActivity() {
        // 'this' <- the context, which is referring to 'LoginActivity' which is an instance of a context
        // MainActivity.class <- the class where you want to navigate to
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private boolean checkAndGetInput() {
        inputEmail = etEmail.getText().toString();
        inputPassword = etPassword.getText().toString();

        if (inputEmail.length() > 0 && inputPassword.length() > 0) {
            Log.i(TAG, "email: " + inputEmail);
            Log.i(TAG, "password: " + inputPassword);
            return true;

        } else {
            return false;
        }
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
