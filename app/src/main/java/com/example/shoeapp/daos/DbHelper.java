package com.example.shoeapp.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "ShoeApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable = "CREATE TABLE IF NOT EXISTS USER(\n" +
                "USERNAME TEXT PRIMARY KEY,\n" +
                "PASSWORD TEXT,\n" +
                "FULLNAME TEXT)";
        db.execSQL(createUserTable);

        String queryCreateShoeTable = "CREATE TABLE IF NOT EXISTS PRODUCT(\n" +
                "PRODUCTID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "NAME TEXT,\n" +
                "DESCRIPTION TEXT,\n" +
                "PRICE REAL,\n" +
                "IMAGE TEXT,\n" +
                "RATINGS REAL)";
        db.execSQL(queryCreateShoeTable);



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
