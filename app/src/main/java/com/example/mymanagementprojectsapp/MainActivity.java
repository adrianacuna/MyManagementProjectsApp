package com.example.mymanagementprojectsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, email, password, repasword;
    Button register, login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.PersonName);
        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
        repasword = findViewById(R.id.RePassword);
        register = findViewById(R.id.Registrarse);
        login = findViewById(R.id.Ingresar);

        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repasword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
                    Toast.makeText(MainActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(repass)) {
                        Boolean verifyEmail = DB.validateEmailExist(mail);
                        if(verifyEmail == false) {
                            Boolean insertData = DB.insertData(user, mail, pass, repass);
                            if(insertData == true) {
                                Toast.makeText(MainActivity.this, "¡Registro guardado correctamente!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "¡Error al realizar registro!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "¡Usuario ya está registrado!", Toast.LENGTH_SHORT).show();Toast.makeText(MainActivity.this, "¡Registro guardado correctamente!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "¡Las contraseñas no son iguales!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}