package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class StudentHomePage extends AppCompatActivity {

    TextView displayName,studentEmail,regNo;
    Button registerCourseBtn;
    RecyclerView coursesRecyclerView;
    ArrayList<String> courses;
    Navigation navigation = new Navigation(this, StudentHomePage.this);
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    DocumentReference documentReference;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_student_home_page);

        displayName = findViewById(R.id.student_name);
        studentEmail = findViewById(R.id.student_email);
        regNo = findViewById(R.id.student_regNo);
        registerCourseBtn = findViewById(R.id.register_new_courses);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        documentReference = fStore.collection("Student").document(userID);

        courses = new ArrayList<>();

        coursesRecyclerView = findViewById(R.id.courses_recycler_view);

        // get a list of courses from the database
        FirebaseDatabase.getInstance().getReference().child("Courses").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (int i = 0; i < Objects.requireNonNull(task.getResult()).getChildrenCount(); i++) {
                    ArrayList<String> courses = new ArrayList<>();
                    String courseName = task.getResult().child(String.valueOf(i)).child("name").getValue(String.class);
                    courses.add(courseName);
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                coursesRecyclerView.setLayoutManager(layoutManager);
                CoursesAdapter coursesAdapter = new CoursesAdapter(courses);
                coursesRecyclerView.setAdapter(coursesAdapter);
            } else {
                Log.e("DB_FETCH", Objects.requireNonNull(Objects.requireNonNull(task.getException()).getLocalizedMessage()));
            }
        });

        DocumentReference documentReference = fStore.collection("Students").document(userID);
        documentReference.addSnapshotListener((value, error) -> {
            if (error != null){
                Log.e("DB_FETCH", Objects.requireNonNull(error.getLocalizedMessage()));
            }

            if (value != null) {
                    String name = value.getString("name");
                    String email = value.getString("email");
                    String reg = value.getString("regNo");

                if (name != null) {
                    Log.d("Name", name);
                    displayName.setText(name);
                }
                if (email != null) {
                    studentEmail.setText(email);
                }
                if (reg != null) {
                    Log.d("Registration", reg);
                    regNo.setText(reg.trim());
                }
            }

            displayName.setText(value.getString("name"));
            studentEmail.setText(value.getString("email"));
            regNo.setText(value.getString("regNo"));
        });

        registerCourseBtn.setOnClickListener(v -> navigation.moveTo(RegisterCourse.class));
    }
}