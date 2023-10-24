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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.login_text_view);
//        registerText = findViewById(R.id.register_lec_text);
        studentRegButton = findViewById(R.id.register_student_button);
        lecturerLoginButton = findViewById(R.id.lecturer_login_button);

        textView.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);
                }
        );

        studentRegButton.setOnClickListener(
                v -> {
                    Intent studentRegActivity = new Intent(this, StudentRegistration.class);
                    startActivity(studentRegActivity);
                }
        );

        lecturerLoginButton.setOnClickListener(
                v -> {
                    Intent lecLoginActivity = new Intent(this, LecturerLogin.class);
                    startActivity(lecLoginActivity);
                }
        );

    }
}