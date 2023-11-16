package com.symon.mtahini;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;


public class StudentRegistration extends AppCompatActivity {

    EditText name_input, email_input,reg_number_input, password_input, confirm_password_input;
    ImageButton imageButton;
    Button register_button;
    TextView loginTextView;
    Navigation appNavigation;
    String name, email, regNumber, password, confirm_password;
    String regexPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    FirebaseDatabase database;
    DatabaseReference reference;
    protected FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and move to the Home activity.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            appNavigation.moveToHomeActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        appNavigation = new Navigation(this,StudentRegistration.this);

        mAuth = FirebaseAuth.getInstance();

        imageButton = findViewById(R.id.stdRegArrowButton);
        loginTextView = findViewById(R.id.login_text_view);
        name_input = findViewById(R.id.student_name_edit_text);
        reg_number_input = findViewById(R.id.reg_number_edit_text);
        email_input = findViewById(R.id.email_edit_text);
        password_input = findViewById(R.id.password_edit_text);
        confirm_password_input = findViewById(R.id.confirm_password_edit_text);
        register_button = findViewById(R.id.reg_button);

        // handle user registration
        register_button.setOnClickListener(v -> {
            name = String.valueOf(name_input.getText());
            regNumber = String.valueOf(reg_number_input.getText());
            email = String.valueOf(email_input.getText());
            password = String.valueOf(password_input.getText());
            confirm_password = String.valueOf(confirm_password_input.getText());

            if (!(checkStringInput(name_input, name))) return;
            if (!(checkStringInput(reg_number_input, regNumber))) return;
            if (!(checkStringInput(email_input, email))) return;
            if (!(checkStringInput(password_input, password))) return;
            if (!(checkStringInput(confirm_password_input, confirm_password))) return;

            if (!(confirm_password.equals(password))){
                confirm_password_input.setError("Password mismatch!");
                confirm_password_input.requestFocus();
                confirm_password_input.setBackgroundResource(R.drawable.alert_bg);
            }

            boolean isStrong = Pattern.matches(regexPattern, password);
/*
            if (!(isStrong)){
                password_input.setError("Password is not strong enough!");
                password_input.requestFocus();
                confirm_password_input.requestFocus();
                confirm_password_input.setBackgroundResource(R.drawable.alert_bg);
                return;
            }
*/
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            appNavigation.moveToHomeActivity();

                        } else {
                            Toast.makeText(StudentRegistration.this, "Unable to create account", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener( e -> Log.d("FB_AUTH", Objects.requireNonNull(e.getLocalizedMessage())));
        });

        // go to main activity on click
        imageButton.setOnClickListener(
                v -> {
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                }
        );

        // go to login page on click
        loginTextView.setOnClickListener(
                v -> {
                    Intent loginPage = new Intent(this, Login.class);
                    startActivity(loginPage);
                }
        );

    }

    public boolean checkStringInput(EditText editText, String text){
        if (text.isEmpty()){
            editText.setError("This part cannot be empty");
            editText.requestFocus();
            editText.setBackgroundResource(R.drawable.alert_bg);
            return false;
        }
        return true;
    }
}