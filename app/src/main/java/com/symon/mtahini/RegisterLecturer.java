package com.symon.mtahini;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.primitives.Chars;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.symon.mtahini.authentication.LecturerLanding;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class RegisterLecturer extends AppCompatActivity {

    EditText lName,staffID,lEmail;
    Spinner dptSpinner, unitSpinner;
    Button register_btn;
    String userID, selectedDpt, selectedUnit;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    Navigation navigation = new Navigation(this, RegisterLecturer.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lecturer);

        lName = findViewById(R.id.lec_name_edit_text);
        staffID = findViewById(R.id.lec_stuff_id_edit_text);
        lEmail = findViewById(R.id.email_edit_text);
        register_btn = findViewById(R.id.reg_button);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        dptSpinner = findViewById(R.id.spinner);
        unitSpinner = findViewById(R.id.unit_spinner);

        ArrayAdapter<CharSequence> departmentAdapter = ArrayAdapter.createFromResource(this, R.array.departments, android.R.layout.simple_spinner_item);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dptSpinner.setAdapter(departmentAdapter);

        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        unitSpinner.setAdapter(unitAdapter);

        dptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Perform actions when a department is selected
                selectedDpt = parent.getItemAtPosition(position).toString();
                // Add your logic here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no department selected
            }
        });
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        register_btn.setOnClickListener(v -> {

            if (navigation.isFieldEmpty(lName)) return;
            if (navigation.isFieldEmpty(lEmail)) return;
            if (navigation.isFieldEmpty(staffID)) return;

            String name = lName.getText().toString().trim();
            String email = lEmail.getText().toString().trim();
            String stuffId = staffID.getText().toString().trim();
            String selected_dpt = dptSpinner.getSelectedItem().toString();


            String lecturerPattern = ".*@dekut\\.com";
            boolean isLec = Pattern.matches(lecturerPattern,email);
            if(!(isLec)){
                lEmail.setError("email must end with @dekut.com");
                lEmail.requestFocus();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, stuffId)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Log.d("LecRegistration", "Successfully registered");
                                Lecturer lecturer = new Lecturer(name, email, stuffId, selected_dpt, selectedUnit);
                                fStore.collection("Lecturers").document(stuffId).set(lecturer);
                                Toast.makeText(RegisterLecturer.this, "Set Password: " + stuffId, Toast.LENGTH_SHORT).show();
                                navigation.moveTo(AdminLandingPage.class);
                            } else {
                                Log.e("Lecture", "Registration Failed");
                            }
                        }
                    });

        });

    }

}