package com.example.thoughtwell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "com.example.thoughtwell.MESSAGE";

    //private Button btnNext;
    //private Button btnDeposit;
    private Button btnLogin;
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
        btnLogin = findViewById(R.id.btnLogin);
        tvSampleThought = findViewById(R.id.tvSampleThought);

        String words = "dont stand there come in";
        tvSampleThought.setText(words);
        myRef.setValue(words);

    }

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

    public void gotoLogin(View view) {
        Log.i(TAG, "entered gotoLogin");
        Intent intent = new Intent(this, LoginActivity.class);
        Log.i(TAG, "entered gotoLogin2");
        startActivity(intent);

    }
}