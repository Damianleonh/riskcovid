package com.leon.prueb1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.leon.prueb1.includes.MyToolbar;

public class Login extends AppCompatActivity {

    TextInputEditText email, password;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //MOSTRAR TOOLBAR
        MyToolbar.show(this,"Login",true);

        email = (TextInputEditText) findViewById(R.id.email);
        password = (TextInputEditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.MainBtnLogin);
        register = (Button) findViewById(R.id.MainBtnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


    }

    private void login() {
        String sEmail = email.getText().toString();
        String sPass = password.getText().toString();

    }
}