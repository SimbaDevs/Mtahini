package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterCourse extends AppCompatActivity {

    SwitchCompat mapSwitch, compilerConstructionSwitch, tocSwitch, mmsSwitch;
    SwitchCompat snmSwitch, daaSwitch, rsmSwitch, aiSwitch, seSwitch, iapSwitch;

    Button coursesSaveButton, cancelButton;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    String userID;
    DocumentReference documentReference;

    List<Unit> registeredUnits = new ArrayList<>();
    Navigation appNavigation = new Navigation(this, RegisterCourse.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);

        mAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("Students").document(userID);

        // binding
        mapSwitch = findViewById(R.id.map_save_toggle_switch);
        compilerConstructionSwitch = findViewById(R.id.compiler_save_toggle_switch);
        tocSwitch = findViewById(R.id.toc_save_toggle_switch);
        mmsSwitch = findViewById(R.id.multimedia_save_toggle_switch);
        snmSwitch = findViewById(R.id.simulation_save_toggle_switch);
        daaSwitch = findViewById(R.id.DAA_save_toggle_switch);
        rsmSwitch = findViewById(R.id.research_save_toggle_switch);
        aiSwitch = findViewById(R.id.AI_save_toggle_switch);
        seSwitch = findViewById(R.id.SWE_save_toggle_switch);
        iapSwitch = findViewById(R.id.IAP_save_toggle_switch);

        coursesSaveButton = findViewById(R.id.course_save_button);
        cancelButton = findViewById(R.id.cancel_button);

        coursesSaveButton.setOnClickListener(v -> {
            // set onChange behaviours
            handleSwitchChange(mapSwitch, "Mobile Application Programming");
            handleSwitchChange(compilerConstructionSwitch, "Compiler Construction");
            handleSwitchChange(tocSwitch, "Theory of Computation");
            handleSwitchChange(mmsSwitch, "Multimedia Systems");
            handleSwitchChange(snmSwitch, "Simulation and Modelling");
            handleSwitchChange(daaSwitch, "Design and Analysis of Algorithms");
            handleSwitchChange(rsmSwitch, "Research Methodology");
            handleSwitchChange(aiSwitch, "Artificial Intelligence");
            handleSwitchChange(seSwitch, "Software Engineering");
            handleSwitchChange(iapSwitch, "Internet Application Programming");

            // register the courses
            documentReference.update("unitList", registeredUnits).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("DB_FETCH", registeredUnits.toString());
                    Toast.makeText(this, "Unit Update Success!", Toast.LENGTH_SHORT).show();
//                    appNavigation.moveTo(StudentHomePage.class);
                } else {
                    Log.e("DB_FETCH", Objects.requireNonNull(Objects.requireNonNull(task.getException()).getLocalizedMessage()));
                    Toast.makeText(this, "Unit Update Fail!", Toast.LENGTH_SHORT).show();
                }
            });

        });
        cancelButton.setOnClickListener(v -> appNavigation.moveTo(StudentHomePage.class));

    }

    public void handleSwitchChange(SwitchCompat switchCompat, String course){
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Unit newUnit = new Unit();
            newUnit.setName(course);
            if (isChecked) {
                registeredUnits.add(newUnit);
            }
            else registeredUnits.remove(newUnit);
        });
    }
}