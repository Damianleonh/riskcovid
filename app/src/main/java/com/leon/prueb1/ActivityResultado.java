package com.leon.prueb1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.leon.prueb1.includes.MyToolbar;

public class ActivityResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //MOSTRAR TOOLBAR
        MyToolbar.show(this, "Tu riesgo", true);


    }

}