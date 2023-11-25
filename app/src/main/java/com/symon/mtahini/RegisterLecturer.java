package com.symon.mtahini;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    Spinner dptSpinner;
    Button register_btn;
    String userID;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lecturer);

        lName = findViewById(R.id.lec_name_edit_text);
        staffID = findViewById(R.id.lec_stuff_number_edit_text);
        lEmail = findViewById(R.id.email_edit_text);
        dptSpinner = findViewById(R.id.spinner);
        register_btn = findViewById(R.id.reg_button);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        register_btn.setOnClickListener(v -> {
            String name = lName.getText().toString().trim();
            String email = lEmail.getText().toString().trim();
            String password = staffID.getText().toString().trim();
            String selected_dpt = dptSpinner.getSelectedItem().toString();

            String lecturerPattern = ".*@dekut\\.com";
            boolean isLec = Pattern.matches(lecturerPattern,email);
            if(!(isLec)){
                lEmail.setError("email must end with @dekut.com");
                lEmail.requestFocus();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterLecturer.this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("lectures").document(userID);

                            Map<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("email",email);
                            user.put("password",password);
                            user.put("selectedDpt",selected_dpt);

                            documentReference.set(user)
                                    .addOnSuccessListener(unused -> Log.d(TAG, "onSuccess: user profile is created for" + userID))
                                    .addOnFailureListener(e -> Log.d(TAG,"onFailure " + e ));

                            Toast.makeText(RegisterLecturer.this, "signed-in successfully",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LecturerLanding.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterLecturer.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });

        });

    }

}