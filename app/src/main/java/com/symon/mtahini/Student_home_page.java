package com.symon.mtahini;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Student_home_page extends AppCompatActivity {

    TextView name,studentEmail,regNo,courses_Count;
    Button registerCourseBtn;
    Navigation navigation = new Navigation(this, Student_home_page.this);
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_student_home_page);

        name = findViewById(R.id.student_name);
        studentEmail = findViewById(R.id.student_email);
        regNo = findViewById(R.id.student_regNo);
        courses_Count = findViewById(R.id.student_register_count);
        registerCourseBtn = findViewById(R.id.register_new_courses);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Users").document(userID);
        documentReference.addSnapshotListener((value, error) -> {
            name.setText(value.getString("FullName"));
            studentEmail.setText(value.getString("email"));
            regNo.setText(value.getString("RegNo"));
        });

        registerCourseBtn.setOnClickListener(v -> navigation.moveTo(RegisterCourse.class));
    }
}