package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Student_home_page extends AppCompatActivity {

    Navigation navigation = new Navigation(this, Student_home_page.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        Button registerCourseBtn = findViewById(R.id.register_new_courses);

        registerCourseBtn.setOnClickListener(
                v -> {
                    navigation.moveTo(RegisterCourse.class);
                }
        );
    }
}