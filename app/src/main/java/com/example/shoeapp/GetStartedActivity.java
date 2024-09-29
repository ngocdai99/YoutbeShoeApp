package com.example.shoeapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;



public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary));

        ImageButton btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(GetStartedActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }
}