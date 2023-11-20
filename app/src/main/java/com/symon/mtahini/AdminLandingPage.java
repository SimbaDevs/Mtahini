package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class AdminLandingPage extends AppCompatActivity {

    Navigation appNavigation = new Navigation(this, AdminLandingPage.this);
    Button registerLecBtn, generateReportBtn, generateSummaryBtn, logOutBtn;
    Button cat1MarksBtn, cat2MarksBtn, assign1marksBtn, assign2MarksBtn, examMarksBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);

        assign1marksBtn = findViewById(R.id.assgn_1_button);
        assign2MarksBtn = findViewById(R.id.assgn_2_button);
        cat1MarksBtn = findViewById(R.id.cat_1_button);
        cat2MarksBtn = findViewById(R.id.cat_2_button);
        examMarksBtn= findViewById(R.id.exam_edit_button);
        registerLecBtn = findViewById(R.id.admit_lec_button);
        generateReportBtn = findViewById(R.id.generate_student_report);
        generateSummaryBtn = findViewById(R.id.print_summary_button);
        logOutBtn = findViewById(R.id.log_out_button);

        assign1marksBtn.setOnClickListener(v -> appNavigation.moveTo(Assignment1Marks.class));
        assign2MarksBtn.setOnClickListener(v -> appNavigation.moveTo(Assignment2Marks.class));
        cat1MarksBtn.setOnClickListener(v -> appNavigation.moveTo(Cat1Marks.class));
        cat2MarksBtn.setOnClickListener(v -> appNavigation.moveTo(Cat2Marks.class));
        registerLecBtn.setOnClickListener(v -> appNavigation.moveTo(RegisterLecturer.class));
        examMarksBtn.setOnClickListener(v -> appNavigation.moveTo(ExamMarks.class));

        logOutBtn.setOnClickListener(v -> appNavigation.moveTo(MainActivity.class));
    }
}