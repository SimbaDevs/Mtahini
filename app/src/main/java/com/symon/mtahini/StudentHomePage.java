package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class StudentHomePage extends AppCompatActivity {

    TextView displayName,studentEmail,regNo,courses_Count;
    Button registerCourseBtn;
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
        courses_Count = findViewById(R.id.student_register_count);
        registerCourseBtn = findViewById(R.id.register_new_courses);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        documentReference = fStore.collection("Student").document(userID);


        DocumentReference documentReference = fStore.collection("Students").document(userID);
        documentReference.addSnapshotListener((value, error) -> {
            if (error != null){
                Log.e("DB_FETCH", Objects.requireNonNull(error.getLocalizedMessage()));
            }

            if (value != null) {
                    String name = value.getString("name");
                    String email = value.getString("email");
                    String reg = value.getString("regno");

                if (name != null) {
                    displayName.setText(name);
                }
                if (email != null) {
                    studentEmail.setText(email);
                }
                if (reg != null) {
                    regNo.setText(reg);
                }
            }

            displayName.setText(value.getString("FullName"));
            studentEmail.setText(value.getString("email"));
            regNo.setText(value.getString("RegNo"));
        });

        registerCourseBtn.setOnClickListener(v -> navigation.moveTo(RegisterCourse.class));
    }
}