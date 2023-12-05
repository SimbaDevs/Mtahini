package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentHomePage extends AppCompatActivity {

    TextView displayName, studentEmail, regNo;
    Button registerCourseBtn;
    RecyclerView unitsRecyclerView;
    List<Unit> recyclerUnits;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
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
        fStore = FirebaseFirestore.getInstance();

        unitsRecyclerView = findViewById(R.id.units_recycler_view);
        recyclerUnits = new ArrayList<>();

        userID = mAuth.getCurrentUser().getUid();
        retrieveUserData();
    }

    private void retrieveUserData() {
        setupRecyclerView();

        fStore.collection("Students").document(userID).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document != null && document.exists()) {
                    Map<String, Object> userData = document.getData();
                    if (userData != null) {
                        String name = (String) userData.get("name");
                        String email = (String) userData.get("email");
                        String regNumber = (String) userData.get("regNo");

                        displayName.setText(name);
                        studentEmail.setText(email);
                        regNo.setText(regNumber);

                          List<Map<String, Object>> units = (List<Map<String, Object>>) userData.get("units");
                        Log.i("UNITS_SIZE", "No of Units = " + units.size() );
                        if (units != null) {
                            for (Map<String, Object> unit : units) {
                                String unitName = (String) unit.get("name");
                                System.out.println(unitName);
                                // Handle other unit properties similarly
                                recyclerUnits.add(new Unit(unitName));
                            }
                        }
                    }
                }
            } else {
                // Handle error or log the exception
            }
        });
    }

    private void setupRecyclerView() {
        ArrayList<Unit> recyclerUnitsArrayList = (ArrayList<Unit>) recyclerUnits;
        UnitAdapter unitAdapter = new UnitAdapter(recyclerUnitsArrayList);
        unitsRecyclerView.setAdapter(unitAdapter);
        unitsRecyclerView.setLayoutManager(new LinearLayoutManager(StudentHomePage.this));
    }
}
