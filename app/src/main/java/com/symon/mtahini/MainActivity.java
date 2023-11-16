package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView registerText;
    Button studentRegButton;
    Button lecturerLoginButton;
    Navigation appNavigation = new Navigation(this, MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.login_text_view);
//        registerText = findViewById(R.id.register_lec_text);
        studentRegButton = findViewById(R.id.student_reg_button);
        lecturerLoginButton = findViewById(R.id.lecturer_login_button);

        textView.setOnClickListener(
                v -> {appNavigation.moveTo(Login.class);}
        );

        studentRegButton.setOnClickListener(
                v -> {appNavigation.moveTo(StudentRegistration.class);}
        );

        lecturerLoginButton.setOnClickListener(
                v -> {appNavigation.moveTo(LecturerLogin.class);}
        );

    }
}