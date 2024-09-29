package com.example.shoeapp.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoeapp.models.Product;

import java.util.ArrayList;

public class ProductDAO {
    private final DbHelper shoeStoreDatabase;


    public ProductDAO(Context context) {
        shoeStoreDatabase = new DbHelper(context);
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = shoeStoreDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCT", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                products.add(new Product(
                        cursor.getInt(0), // id
                        cursor.getString(1), // name
                        cursor.getString(2), // desc
                        cursor.getDouble(3), // price
                        cursor.getString(4), // image
                        cursor.getDouble(5) // ratings
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

    public boolean createProduct(String name, String description, double price, String image, double ratings) {
        SQLiteDatabase sqLiteDatabase = shoeStoreDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", name);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("PRICE", price);
        contentValues.put("IMAGE", image);
        contentValues.put("RATINGS", ratings);

        long check = sqLiteDatabase.insert("PRODUCT", null, contentValues);
        return check != -1;
    }

    public boolean updateProduct(int productId, String name, String description, double price, String image) {
        SQLiteDatabase sqLiteDatabase = shoeStoreDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", name);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("PRICE", price);
        contentValues.put("IMAGE", image);


        int check = sqLiteDatabase.update("PRODUCT", contentValues, "PRODUCTID = ?", new String[]{String.valueOf(productId)});
        return check > 0;
    }

}
