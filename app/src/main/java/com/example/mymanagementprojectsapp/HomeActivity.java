package com.example.mymanagementprojectsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    EditText nombreproyecto, tiporproyecto, recursosproyecto, inicioproyecto, finproyecto, encargadoproyecto, descripcionproyecto;
    Button logout;
    FloatingActionButton saveproject;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nombreproyecto = findViewById(R.id.ProyectName);
        tiporproyecto = findViewById(R.id.ProyectType);
        recursosproyecto = findViewById(R.id.ProyectResources);
        inicioproyecto = findViewById(R.id.ProyectStartDate);
        finproyecto = findViewById(R.id.ProyectEndDate);
        encargadoproyecto = findViewById(R.id.ProjectManager);
        descripcionproyecto = findViewById(R.id.ProyectDescription);
        logout = findViewById(R.id.logoutButton);
        saveproject = findViewById(R.id.saveProjectButton);

        DB = new DBHelper(this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        saveproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projname = nombreproyecto.getText().toString();
                String projtipo = tiporproyecto.getText().toString();
                String projrec = recursosproyecto.getText().toString();
                String projinicio = inicioproyecto.getText().toString();
                String projfin = finproyecto.getText().toString();
                String projmana = encargadoproyecto.getText().toString();
                String projdesc = descripcionproyecto.getText().toString();

                Log.d("Dataaaaa projname->", projname);
                Log.d("Dataaaaa projtipo->", projtipo);
                Log.d("Dataaaaa projrec->", projrec);
                Log.d("Dataaaaa projinicio->", projinicio);
                Log.d("Dataaaaa projfin->", projfin);
                Log.d("Dataaaaa projmana->", projmana);
                Log.d("Dataaaaa projdesc->", projdesc);

                if(TextUtils.isEmpty(projname) || TextUtils.isEmpty(projtipo) || TextUtils.isEmpty(projrec) ||
                        TextUtils.isEmpty(projinicio) || TextUtils.isEmpty(projfin) || TextUtils.isEmpty(projmana) ||
                        TextUtils.isEmpty(projdesc)) {
                    Toast.makeText(HomeActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insertProjData = DB.insertProjectData(projname, projtipo, projrec, projinicio, projfin, projmana, projdesc);
                    if(insertProjData == true) {
                        Toast.makeText(HomeActivity.this, "¡Proyecto guardado correctamente!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(HomeActivity.this, "¡Error al registrar proyecto!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}