package com.example.shoeapp.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoeapp.models.User;

public class UserDAO {
    private final DbHelper shoeStoreDatabase;

    public UserDAO(Context context) {
        shoeStoreDatabase = new DbHelper(context);
    }

    public String onRegister(String username, String password, String fullname) {
        SQLiteDatabase sqLiteDatabase = shoeStoreDatabase.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE USERNAME = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return "Username existed";
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("USERNAME", username);
            contentValues.put("PASSWORD", password);
            contentValues.put("FULLNAME", fullname);

            long check = sqLiteDatabase.insert("USER", null, contentValues);
            cursor.close();

            if (check != -1) {
                return "Register successfully";
            } else {
                return "Error happens";
            }
        }
    }

    public boolean onLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = shoeStoreDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from CUSTOMER where USERNAME = ? AND PASSWORD = ?", new String[]{username, password});
        cursor.close();
        return cursor.getCount() > 0;
    }


    public User getCustomer(String username) {
        User customer = null;
        SQLiteDatabase sqLiteDatabase = shoeStoreDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from CUSTOMER where USERNAME = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            customer = new User(
                    cursor.getString(0),// fullname
                    cursor.getString(1), // username
                    cursor.getString(2) // password
            );
            cursor.close();
        }

        return customer;
    }


}
