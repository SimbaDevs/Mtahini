package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class LecturerLanding extends AppCompatActivity {

    Navigation appNav = new Navigation(this, LecturerLanding.this);
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_landing);

        button = findViewById(R.id.lec_assignment_1_button);
        button.setOnClickListener(
                v -> {
                    appNav.moveTo(CatMarksInput.class);
                }
        );
    }
}