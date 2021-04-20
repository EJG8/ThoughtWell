package com.example.thoughtwell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "com.example.thoughtwell.MESSAGE";

    //private Button btnNext;
    //private Button btnDeposit;
    private Button btnLogout;
    private TextView tvSampleThought;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference().child("test");

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "entered onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to buttons
        //btnNext = findViewById(R.id.btnNext);
        //btnDeposit = findViewById(R.id.btnDeposit);
        btnLogout = findViewById(R.id.btnLogout);
        tvSampleThought = findViewById(R.id.tvSampleThought);

        String words = "dont stand there come in";
        tvSampleThought.setText(words);
        myRef.setValue(words);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Signed out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                goLoginActivity();
            }
        });

    }

    private void goLoginActivity() {
        Log.i(TAG, "entered goLoginActivity");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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
}