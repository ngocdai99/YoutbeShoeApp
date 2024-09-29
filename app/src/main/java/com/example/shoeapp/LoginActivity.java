package com.example.shoeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.shoeapp.daos.UserDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary));

        TextView txtRegister = findViewById(R.id.txtRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);

        txtRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        btnLogin.setOnClickListener(v -> {

            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();


            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Fields should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                UserDAO userDAO = new UserDAO(LoginActivity.this);
                boolean loginSuccess = userDAO.onLogin(username, password);

                if (loginSuccess) {
                    saveUser(username);
                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUser(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }
}