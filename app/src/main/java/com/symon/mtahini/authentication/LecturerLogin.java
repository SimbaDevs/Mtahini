package com.symon.mtahini.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.symon.mtahini.MainActivity;
import com.symon.mtahini.Navigation;
import com.symon.mtahini.R;

public class LecturerLogin extends AppCompatActivity {
    ImageButton arrowButton;
    TextView registerText;
    Navigation appNavigation = new Navigation(this, LecturerLogin.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_login);

        arrowButton = findViewById(R.id.arrowButton);
        registerText = findViewById(R.id.register_lec_text);

        arrowButton.setOnClickListener(
                v  -> { appNavigation.moveTo(MainActivity.class);}
        );
    }

    public static class Student {
        String name, email, registrationNumber;

        public Student() {
        }

        public Student(String name, String email, String registrationNumber) {
            this.name = name;
            this.email = email;
            this.registrationNumber = registrationNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }
    }
}