package com.symon.mtahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.login_text_view);
        textView.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);
                }
        );

    }
}