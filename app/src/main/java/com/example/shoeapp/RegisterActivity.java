package com.example.shoeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.shoeapp.daos.UserDAO;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary));


        // Mapping
        TextView txtLogin = findViewById(R.id.txtLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        EditText edtFullName = findViewById(R.id.edtFullName);
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);


        txtLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        btnRegister.setOnClickListener(v -> {
            String fullName = edtFullName.getText().toString().trim();
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();


            if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Fields should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                UserDAO userDAO = new UserDAO(RegisterActivity.this);
                String message = userDAO.onRegister(username, password, fullName);
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                if (message.equals("Register Successfully")) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}