package com.example.mymanagementprojectsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        email = findViewById(R.id.EmailAddressLogin);
        password = findViewById(R.id.PasswordLogin);
        login = findViewById(R.id.InicioIngresar);

        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "¡Todos los campos son requeridos!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean validateEmailUser = DB.validateEmailPasswordExist(mail, pass);
                    if(validateEmailUser == true) {
                        Toast.makeText(LoginActivity.this, "¡Acceso correcto!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "¡Acceso incorrecto, intente nuevamente!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}