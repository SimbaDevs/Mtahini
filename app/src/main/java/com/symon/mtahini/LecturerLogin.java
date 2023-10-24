package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class LecturerLogin extends AppCompatActivity {
    ImageButton arrowButton;
    TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_login);

        arrowButton = findViewById(R.id.arrowButton);
        registerText = findViewById(R.id.register_lec_text);

        arrowButton.setOnClickListener(
                v  -> {
                    Intent backToMain = new Intent(this, MainActivity.class);
                    startActivity(backToMain);
                }
        );
    }
}