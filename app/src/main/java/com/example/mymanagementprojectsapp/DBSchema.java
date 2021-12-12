package com.example.mymanagementprojectsapp;

import android.provider.BaseColumns;

public class DBSchema {
    public static abstract  class UsersTable implements BaseColumns {
        public static final String TABLE_USER = "usuarios";
        public static final String NOMBRE = "nombre";
        public static final String CORREO = "correo";
        public static final String CONTRASENA = "contrasena";
        public static final String RECONTRASENA = "recontrasena";


    }
}
