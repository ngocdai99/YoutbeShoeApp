package com.example.shoeapp.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "ShoeApp", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable = "CREATE TABLE IF NOT EXISTS USER(\n" +
                "USERNAME TEXT PRIMARY KEY,\n" +
                "PASSWORD TEXT,\n" +
                "FULLNAME TEXT)";
        db.execSQL(createUserTable);

        String insertUsers = "INSERT INTO USER (USERNAME, PASSWORD, FULLNAME) VALUES \n" +
                "('daingoc99', '123', 'Dai Ngoc')";
        db.execSQL(insertUsers);

        String createProductTable = "CREATE TABLE IF NOT EXISTS PRODUCT(\n" +
                "PRODUCTID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "NAME TEXT,\n" +
                "DESCRIPTION TEXT,\n" +
                "PRICE REAL,\n" +
                "IMAGE TEXT,\n" +
                "RATINGS REAL)";
        db.execSQL(createProductTable);

        String insertProducts = "INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE, IMAGE, RATINGS) VALUES \n" +
                "('Nike Air Max', 'High-quality sports shoes', 120.99, 'https://www.converse.vn/media/catalog/product/cache/e81e4f913a1cad058ef66fea8e95c839/0/8/0882-CONA03277C005006-1.jpg', 4.5),\n" +
                "('Adidas Ultraboost', 'Comfortable running shoes', 150.50, 'https://www.converse.vn/media/catalog/product/cache/e81e4f913a1cad058ef66fea8e95c839/0/8/0882-CONA07977C0PK06H-1.jpg', 4.8),\n" +
                "('Converse Chuck Taylor', 'Classic high-top sneakers', 70.00, 'https://www.converse.vn/media/catalog/product/cache/e81e4f913a1cad058ef66fea8e95c839/0/8/0882-CON166799C00008H-1.jpg', 4.3);";
        db.execSQL(insertProducts);



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS USER");
            db.execSQL("DROP TABLE IF EXISTS PRODUCT");
            onCreate(db);
        }
    }
}
