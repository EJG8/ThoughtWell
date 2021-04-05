package com.example.thoughtwell;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private Button btnNext;
    private Button btnDeposit;
    private TextView tvSampleThought;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message2");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to buttons
        btnNext = findViewById(R.id.btnNext);
        btnDeposit = findViewById(R.id.btnDeposit);
        tvSampleThought = findViewById(R.id.tvSampleThought);

        myRef.setValue("Another test yet again");

    }
}