package com.example.thoughtwell.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// since we are coding inside of java/com.example.thoughtwell/fragments we need to include this to
// be able to reach main/res/layout/fragment_home
import com.example.thoughtwell.LoginActivity;
import com.example.thoughtwell.MainActivity;
import com.example.thoughtwell.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    public static final String EXTRA_MESSAGE = "com.example.thoughtwell.MESSAGE";

    //private Button btnNext;
    //private Button btnDeposit;
    private Button btnLogout;
    private TextView tvSampleThought;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference().child("test");

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get references to our elements in the activity.xml
        Button btnLogout = view.findViewById(R.id.btnLogout);
        tvSampleThought = view.findViewById(R.id.tvSampleThought);

        String words = "dont stand there come in";
        tvSampleThought.setText(words);
        myRef.setValue(words);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                goLoginActivity();
            }
        });
    }

    private void goLoginActivity() {
        Log.i(TAG, "entered goLoginActivity");
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        // not the best way to design this
        // take a look at  user1923551's post on top answer
        // https://stackoverflow.com/questions/7907900/finishing-current-activity-from-a-fragment
        requireActivity().finish();
    }
}
