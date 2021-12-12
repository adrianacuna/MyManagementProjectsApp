package com.example.mymanagementprojectsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "management";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+ DBSchema.UsersTable.TABLE_USER + "("
                + DBSchema.UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBSchema.UsersTable.NOMBRE + " TEXT , "
                + DBSchema.UsersTable.CORREO + " TEXT , "
                + DBSchema.UsersTable.CONTRASENA + " TEXT , "
                + DBSchema.UsersTable.RECONTRASENA + " TEXT , "
                + "UNIQUE (" + DBSchema.UsersTable.CORREO + "))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + DBSchema.UsersTable.TABLE_USER);
    }

    public Boolean insertData(String username, String email, String password, String repasword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBSchema.UsersTable.NOMBRE, username);
        values.put(DBSchema.UsersTable.CORREO, email);
        values.put(DBSchema.UsersTable.CONTRASENA, password);
        values.put(DBSchema.UsersTable.RECONTRASENA, repasword);

        long result = db.insert(
                DBSchema.UsersTable.TABLE_USER,
                null,
                values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean validateEmailExist(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from " +
                DBSchema.UsersTable.TABLE_USER +
                " where " +DBSchema.UsersTable.CORREO+"=?",
                new String[] {email});
        if (cursor.getCount() > 1) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean validateEmailPasswordExist(String email, String password) {
        Log.d("Dataaaaa email->", email);
        Log.d("Dataaaaa password->", password);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from " +
                        DBSchema.UsersTable.TABLE_USER +
                        " where "+DBSchema.UsersTable.CORREO+"=? and "
                        +DBSchema.UsersTable.CONTRASENA+"=?",
                new String[] {email, password});
        Log.d("Dataaaaa cursor->", String.valueOf(cursor.getCount()));
        if (cursor.getCount() >= 1) {
            return true;
        } else {
            return false;
        }
    }
}
