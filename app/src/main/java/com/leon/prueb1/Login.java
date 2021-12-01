package com.leon.prueb1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.leon.prueb1.includes.MyToolbar;

public class Login extends AppCompatActivity {

    TextInputEditText email, password;
    Button login, register;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    SimpleArcDialog arcDialog;

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


        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        /*ALER-DIALOG*/
        arcDialog = new SimpleArcDialog(Login.this);
        ArcConfiguration configuration = new ArcConfiguration(Login.this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.COMPLETE_ARC);
        configuration.setText("Cargando...");
        configuration.setTextSize(16);
        configuration.setArcMargin(6);
        configuration.setTextColor(Color.BLACK);
        arcDialog.setConfiguration(configuration);


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

        if(!sEmail.isEmpty() && !sPass.isEmpty()){
            if (sPass.length() >=6){
                arcDialog.show();
                auth.signInWithEmailAndPassword(sEmail,sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "Login iniciado correctamente", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        arcDialog.dismiss();
                    }
                });
            }else{
                Toast.makeText(Login.this, "La longitud de la contraseña debe ser mayor a 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Login.this, "Es necesario ingresar el correo y la contraseña", Toast.LENGTH_SHORT).show();
        }
    }
}