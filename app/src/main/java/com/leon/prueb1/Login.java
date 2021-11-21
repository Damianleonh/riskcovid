package com.leon.prueb1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.leon.prueb1.includes.MyToolbar;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //MOSTRAR TOOLBAR
        MyToolbar.show(this,"Login",true);
    }
}