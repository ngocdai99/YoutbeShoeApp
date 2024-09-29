package com.example.shoeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.shoeapp.models.Product;
import com.example.shoeapp.utils.CartUtils;


public class ProductDetailActivity extends AppCompatActivity {
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary));


        ImageView imageBack = findViewById(R.id.imageBack);

        RelativeLayout layoutCart = findViewById(R.id.layoutCart);
        ImageButton btnPlus = findViewById(R.id.btnPlus);
        ImageButton btnMinus = findViewById(R.id.btnMinus);

        TextView txtQuantity = findViewById(R.id.txtQuantity);
        TextView txtCartQuantity = findViewById(R.id.txtCartQuantity);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);

        CartUtils.formatQuantityTextView(txtCartQuantity, CartUtils.getSize());

        imageBack.setOnClickListener(v -> finish());

        layoutCart.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(intent);
        });

        btnPlus.setOnClickListener(v -> {
            if (quantity < 99) {
                quantity += 1;
                CartUtils.formatQuantityTextView(txtQuantity, quantity);

            }
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity -= 1;
                CartUtils.formatQuantityTextView(txtQuantity, quantity);
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product(1,"Converse Classic", "Giày thể thao Converse cổ điển", 79.00, "https://example.com/converse_classic.jpg", 4.8);
                CartUtils.addToCart(product);
                CartUtils.formatQuantityTextView(txtCartQuantity, CartUtils.getSize());
            }
        });

    }


}