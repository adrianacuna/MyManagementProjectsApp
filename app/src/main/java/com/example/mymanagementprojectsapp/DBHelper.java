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

        sqLiteDatabase.execSQL("Create table "+ DBSchema.ProjectsTable.TABLE_PROJECTS + "("
                + DBSchema.ProjectsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBSchema.ProjectsTable.NOMBREPROYECTO + " TEXT , "
                + DBSchema.ProjectsTable.TIPOPROYECTO + " TEXT , "
                + DBSchema.ProjectsTable.RECURSOSPROYECTO + " TEXT , "
                + DBSchema.ProjectsTable.INICIOPROYECTO + " TEXT , "
                + DBSchema.ProjectsTable.FINPROYECTO + " TEXT , "
                + DBSchema.ProjectsTable.ENCARGADOPROYECTO + " TEXT , "
                + DBSchema.ProjectsTable.DESCRIPCIONPROYECTO + " TEXT , "
                + "UNIQUE (" + DBSchema.ProjectsTable._ID + "))"
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

    public Boolean insertProjectData(String nombreproyecto, String tiporproyecto, String recursosproyecto,
                                     String inicioproyecto, String finproyecto, String encargadoproyecto,
                                     String descripcionproyecto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBSchema.ProjectsTable.NOMBREPROYECTO, nombreproyecto);
        values.put(DBSchema.ProjectsTable.TIPOPROYECTO, tiporproyecto);
        values.put(DBSchema.ProjectsTable.RECURSOSPROYECTO, recursosproyecto);
        values.put(DBSchema.ProjectsTable.INICIOPROYECTO, inicioproyecto);
        values.put(DBSchema.ProjectsTable.FINPROYECTO, finproyecto);
        values.put(DBSchema.ProjectsTable.ENCARGADOPROYECTO, encargadoproyecto);
        values.put(DBSchema.ProjectsTable.DESCRIPCIONPROYECTO, descripcionproyecto);

        long result = db.insert(
                DBSchema.ProjectsTable.TABLE_PROJECTS,
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
