package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class home extends AppCompatActivity {
    Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logOutButton = findViewById(R.id.log_out_button);

        logOutButton.setOnClickListener(
                v -> {
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                }
        );
    }
}