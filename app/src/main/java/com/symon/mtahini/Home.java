package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    Button logOutButton;
    FirebaseAuth auth;

    Navigation navigation = new Navigation(this, Home.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        logOutButton = findViewById(R.id.log_out_button);
        logOutButton.setOnClickListener(
                v -> {
                    auth.signOut();
                    navigation.moveTo(MainActivity.class);
                }
        );
    }
}