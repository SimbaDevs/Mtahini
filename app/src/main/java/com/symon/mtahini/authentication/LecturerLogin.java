package com.symon.mtahini.authentication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.symon.mtahini.AdminLandingPage;
import com.symon.mtahini.MainActivity;
import com.symon.mtahini.Navigation;
import com.symon.mtahini.R;
import com.symon.mtahini.StudentHomePage;

import java.util.regex.Pattern;

public class LecturerLogin extends AppCompatActivity {
    ImageButton arrowButton;
    TextView registerText;
    EditText lEmail,lPassword;
    Button login_btn;
    Navigation appNavigation = new Navigation(this, LecturerLogin.this);

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_login);

        lEmail = findViewById(R.id.email_edit_text);
        lPassword = findViewById(R.id.password_edit_text);
        login_btn = findViewById(R.id.login_button);
        arrowButton = findViewById(R.id.arrowButton);
        registerText = findViewById(R.id.register_lec_text);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        login_btn.setOnClickListener(v -> {

          String  email = lEmail.getText().toString().trim();
          String  password = lPassword.getText().toString().trim();


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LecturerLogin.this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FB_AUTH", "signInWithEmail:success");
                            Toast.makeText(LecturerLogin.this,"Signed in Successful",Toast.LENGTH_SHORT).show();
                            String lecturerPattern = ".*@dekut\\.com";
                            String studentPattern = ".*@students\\.dekut\\.ac\\.ke";
                            String adminPattern = ".*@admin\\.dekut\\.com";
                            boolean isAdmin = Pattern.matches(adminPattern,email);
                            boolean isStudent = Pattern.matches(studentPattern,email);
                            boolean isLec = Pattern.matches(lecturerPattern,email);
                            if(isLec){
                                startActivity(new Intent(LecturerLogin.this,LecturerLanding.class));
                            } else if (isStudent) {
                                startActivity(new Intent(LecturerLogin.this, StudentHomePage.class));
                            } else if (isAdmin) {
                                startActivity(new Intent(LecturerLogin.this, AdminLandingPage.class));
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FB_AUTH", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LecturerLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        arrowButton.setOnClickListener(
                v  -> { appNavigation.moveTo(MainActivity.class);}
        );
    }

    public static class Student {
        String name, email, registrationNumber;

        public Student() {
        }

        public Student(String name, String email, String registrationNumber) {
            this.name = name;
            this.email = email;
            this.registrationNumber = registrationNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }
    }
}