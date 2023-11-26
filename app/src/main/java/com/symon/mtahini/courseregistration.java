package com.symon.mtahini;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class courseregistration extends AppCompatActivity {

    private EditText editTextRegistrationNumber;
    private EditText editTextName;
    private EditText editTextEmail;
    private ListView listViewCourses;
    private Button buttonSubmit;

    private ArrayAdapter<String> courseAdapter;
    private List<String> selectedCourses;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseregistration);

        editTextRegistrationNumber = findViewById(R.id.editTextRegistrationNumber);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        listViewCourses = findViewById(R.id.listViewCourses);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Initialize the list of courses
        List<String> coursesList = new ArrayList<>();
        coursesList.add("Mobile Application Programming");
        coursesList.add("Compiler Construction");
        coursesList.add("Theory Of Computation");
        coursesList.add("Multimedia Systems");
        coursesList.add("Simulation And Modelling");
        coursesList.add("Design Analysis of Algorithms");
        coursesList.add("Research Methodology");
        coursesList.add("Artificial Intelligence");
        coursesList.add("Software Engineering");
        coursesList.add("Internet Application");

        // Initialize the selected courses list
        selectedCourses = new ArrayList<>();

        // Set up the adapter for the ListView
        courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, coursesList);
        listViewCourses.setAdapter(courseAdapter);
        listViewCourses.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Set item click listener for the ListView
        listViewCourses.setOnItemClickListener((parent, view, position, id) -> handleCourseSelection(position));

        // Initialize Firebase Firestore
        firestore = FirebaseFirestore.getInstance();

        buttonSubmit.setOnClickListener(v -> submitRegistration());
    }

    private void handleCourseSelection(int position) {
        // Handle item click to update the selected courses list
        if (listViewCourses.isItemChecked(position)) {
            if (selectedCourses.size() < 5) {
                selectedCourses.add(courseAdapter.getItem(position));
            } else {
                // If already 5 courses are selected, uncheck the item
                listViewCourses.setItemChecked(position, false);
                Toast.makeText(courseregistration.this, "You can select up to 5 courses", Toast.LENGTH_SHORT).show();
            }
        } else {
            selectedCourses.remove(courseAdapter.getItem(position));
        }
    }

    private boolean isValidEmail(String email) {
        // Use Patterns.EMAIL_ADDRESS to validate email format
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void submitRegistration() {
        // Get registration details
        String registrationNumber = editTextRegistrationNumber.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        // Validate registration details
        if (!TextUtils.isEmpty(registrationNumber) && !TextUtils.isEmpty(name) && isValidEmail(email) && !selectedCourses.isEmpty()) {
            // Create a User object with the selected courses
            User user = new User(registrationNumber, name, email, selectedCourses);


            addDataToFirestore(user);

            // Reset the form after successful submission
            resetForm();

            // Display a success message
            Toast.makeText(courseregistration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
        } else {
            // Display an error message if any field is empty or no course is selected
            Toast.makeText(this, "Invalid input. Please check your entries.", Toast.LENGTH_SHORT).show();
        }
    }

    private void addDataToFirestore(User user) {
        // Create a map to store user data
        Map<String, Object> userData = new HashMap<>();
        userData.put("registrationNumber", user.getRegistrationNumber());
        userData.put("name", user.getName());
        userData.put("email", user.getEmail());
        userData.put("selectedCourses", user.getCourses());

        // Add data to Firestore
        firestore.collection("users")
                .add(userData)
                .addOnSuccessListener(documentReference -> {
                    // Handle success
                    // You can log or handle success in any way you prefer
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Toast.makeText(courseregistration.this, "Firestore Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void resetForm() {
        editTextRegistrationNumber.getText().clear();
        editTextName.getText().clear();
        editTextEmail.getText().clear();
        listViewCourses.clearChoices();
        selectedCourses.clear();
    }
}
