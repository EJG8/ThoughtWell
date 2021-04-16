package com.example.thoughtwell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "com.example.thoughtwell.MESSAGE";

    //private Button btnNext;
    //private Button btnDeposit;
    //private Button btnLogin;
    private Button btnLogout;
    private TextView tvSampleThought;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("test");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "entered onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to buttons
        //btnNext = findViewById(R.id.btnNext);
        // btnDeposit = findViewById(R.id.btnDeposit);
        //btnLogin = findViewById(R.id.btnLogin);
        btnLogout = findViewById(R.id.btnLogout);
        tvSampleThought = findViewById(R.id.tvSampleThought);

        String words = "dont stand there come in";
        tvSampleThought.setText(words);
        myRef.setValue(words);

        // if the user clicks this button, sign them out and return to login screen
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid = FirebaseAuth.getInstance().getUid();
                // sign out the user
                FirebaseAuth.getInstance().signOut();
                // send back to login page
                uid = FirebaseAuth.getInstance().getUid();
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                Log.i(TAG, "entered gotoLogin2");
                startActivity(intent);
            }
        });

    }





    //FirebaseAuth.getInstance().signOut();

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