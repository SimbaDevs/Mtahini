package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    ImageButton imageButton;
    TextView regText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageButton = findViewById(R.id.arrowButton);
        regText = findViewById(R.id.register_student_text);

        imageButton.setOnClickListener(
                v -> {
                    Intent previousPage = new Intent(this, MainActivity.class);
                    startActivity(previousPage);
                }
        );
        regText.setOnClickListener(
                v -> {
                    Intent regActivity = new Intent(this, StudentRegistration.class);
                    startActivity(regActivity);
                }
        );
    }
}