package com.symon.mtahini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class StudentRegistration extends AppCompatActivity {

    EditText name_input, email_input, password_input, confirm_password_input;
    ImageButton imageButton;
    Button register_button;
    TextView loginTextView;

    String name, email, password, confirm_password;
    String regexPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    protected FirebaseAuth mAuth;
    /**
     * reload - changes the activity to the home activity
     */

    //TODO: make this function accessible in other classes
    public void moveToHomeActivity() {
        Intent homeActivity = new Intent(this, home.class);
        startActivity(homeActivity);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and move to the home activity.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            moveToHomeActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        mAuth = FirebaseAuth.getInstance();

        imageButton = findViewById(R.id.stdRegArrowButton);
        loginTextView = findViewById(R.id.login_text_view);
        name_input = findViewById(R.id.student_name_edit_text);
        email_input = findViewById(R.id.email_edit_text);
        password_input = findViewById(R.id.password_edit_text);
        confirm_password_input = findViewById(R.id.confirm_password_edit_text);
        register_button = findViewById(R.id.reg_button);

        // handle user registration
        register_button.setOnClickListener(v -> {
            name = String.valueOf(name_input.getText());
            email = String.valueOf(email_input.getText());
            password = String.valueOf(password_input.getText());
            confirm_password = String.valueOf(confirm_password_input.getText());

            if (name.isEmpty()){
                name_input.setError("This field cannot be empty!");
                name_input.requestFocus();
                name_input.setBackgroundResource(R.drawable.alert_bg);
                return;
            }
            if (email.isEmpty()){
                email_input.setError("This field cannot be empty!");
                email_input.requestFocus();
                email_input.setBackgroundResource(R.drawable.alert_bg);
                return;
            }
            if (password.isEmpty()){
                password_input.setError("This field cannot be empty!");
                password_input.requestFocus();
                password_input.setBackgroundResource(R.drawable.alert_bg);
                return;
            }
            if (confirm_password.isEmpty()){
                confirm_password_input.setError("This field cannot be empty!");
                confirm_password_input.requestFocus();
                confirm_password_input.setBackgroundResource(R.drawable.alert_bg);
            }

            if (!(confirm_password.equals(password))){
                confirm_password_input.setError("Password mismatch!");
                confirm_password_input.requestFocus();
                confirm_password_input.setBackgroundResource(R.drawable.alert_bg);
            }

            boolean isStrong = Pattern.matches(regexPattern, password);

            if (!(isStrong)){
                password_input.setError("Password is not strong enough!");
                password_input.requestFocus();
                confirm_password_input.requestFocus();
                confirm_password_input.setBackgroundResource(R.drawable.alert_bg);
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                moveToHomeActivity();

                            } else {
                                Toast.makeText(StudentRegistration.this, "Unable to create account", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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
}