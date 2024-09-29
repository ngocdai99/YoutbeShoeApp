package com.example.shoeapp.utils;

import android.widget.TextView;

import com.example.shoeapp.models.Product;

import java.util.ArrayList;


public class CartUtils {
    public static ArrayList<Product> MY_CART = new ArrayList<>();

    public static int getSize() {
        return MY_CART.size();
    }

    public static void updateQuantityInCart(TextView textView) {
        int quantity = getSize();
        String quantityText = (quantity < 10) ? ((quantity > 0) ? ("0" + quantity) : ("0")) : String.valueOf(quantity);
        textView.setText(quantityText);
    }


    public static void addToCart(Product product) {
        boolean founded = false;
        for (Product pr : MY_CART) {
            if (pr.getProductId() == product.getProductId()) {
                founded = true;
                int newQuantity = pr.getQuantityInCart() + 1;
                pr.setNumberInCart(newQuantity);
                break;
            }
        }
        if (!founded){
            product.setNumberInCart(1);
            MY_CART.add(product);
        }
    }


    public static void clearMyCart() {
        MY_CART.clear();
    }

    public static void formatQuantityTextView(TextView textView, int quantity) {
        if (textView != null) {
            String quantityText = (quantity < 10) ? ((quantity > 0) ? ("0" + quantity) : ("0")) : String.valueOf(quantity);
            textView.setText(quantityText);
        }

    }
}

