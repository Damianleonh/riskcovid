package com.leon.prueb1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.leon.prueb1.includes.MyToolbar;

public class  Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //MOSTRAR TOOLBAR
        MyToolbar.show(this,"Registro",true);
    }
}