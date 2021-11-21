package com.leon.prueb1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Resources
    Button mMainBtnLogin,mMainBtnRegister, mMainBtnCalculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainBtnLogin = findViewById(R.id.MainBtnLogin);
        mMainBtnRegister = findViewById(R.id.MainBtnRegister);
        mMainBtnCalculadora = findViewById(R.id.MainBtnCalculadora);

        //BOTONES DE CAMBIO DE PANTALLA
        mMainBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

        mMainBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });

        mMainBtnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityCalculadora.class);
                startActivity(intent);
            }
        });
    }
}