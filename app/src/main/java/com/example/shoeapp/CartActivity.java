package com.example.shoeapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary));

        ImageView imageBack = findViewById(R.id.imageBack);

        imageBack.setOnClickListener(v -> finish());

    }
}