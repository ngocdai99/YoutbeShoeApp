package com.example.shoeapp.models;

import java.io.Serializable;



public class Product implements Serializable {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int numberInCart;
    private String image;
    private double ratings;
    private int quantityInCart;

    public Product(int productId, String name, String description, double price, String image, double ratings) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.ratings = ratings;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }
}