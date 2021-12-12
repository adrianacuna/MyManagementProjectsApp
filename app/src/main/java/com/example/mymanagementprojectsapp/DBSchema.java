package com.example.mymanagementprojectsapp;

import android.provider.BaseColumns;

import java.util.Date;

public class DBSchema {
    public static abstract  class UsersTable implements BaseColumns {
        public static final String TABLE_USER = "usuarios";
        public static final String NOMBRE = "nombre";
        public static final String CORREO = "correo";
        public static final String CONTRASENA = "contrasena";
        public static final String RECONTRASENA = "recontrasena";
    }

    public static abstract  class ProjectsTable implements BaseColumns {
        public static final String TABLE_PROJECTS = "proyectos";
        public static final String NOMBREPROYECTO = "nombreProyecto";
        public static final String TIPOPROYECTO = "tipoProyecto";
        public static final String RECURSOSPROYECTO = "recursosproyecto";
        public static final String INICIOPROYECTO = "inicioProyecto";
        public static final String FINPROYECTO = "finProyecto";
        public static final String ENCARGADOPROYECTO = "encargadoProyecto";
        public static final String DESCRIPCIONPROYECTO = "descripcionProyecto";
    }
}
