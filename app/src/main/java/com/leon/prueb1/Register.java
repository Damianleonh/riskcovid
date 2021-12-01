package com.leon.prueb1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.leon.prueb1.models.User;

public class  Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    TextInputEditText correo, pass, passconfirm;
    Button registrar;
    SimpleArcDialog arcDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //MOSTRAR TOOLBAR
        MyToolbar.show(this,"Registro",true);

        correo = (TextInputEditText) findViewById(R.id.emailreg);
        pass = (TextInputEditText) findViewById(R.id.pass);
        passconfirm = (TextInputEditText) findViewById(R.id.passconfirm);
        registrar = (Button) findViewById(R.id.MainBtnRegister);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        /*ALER-DIALOG*/
        arcDialog = new SimpleArcDialog(Register.this);
        ArcConfiguration configuration = new ArcConfiguration(Register.this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.COMPLETE_ARC);
        configuration.setText("Cargando...");
        configuration.setTextSize(16);
        configuration.setArcMargin(6);
        configuration.setTextColor(Color.BLACK);
        arcDialog.setConfiguration(configuration);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });

    }

    private void registrarUsuario() {
        String sCorreo = correo.getText().toString();
        String sPass = pass.getText().toString();
        String sPassC = passconfirm.getText().toString();

        if (!sCorreo.isEmpty() && !sPass.isEmpty() && !sPassC.isEmpty()){
            if (sPass.equals(sPassC)){
                if (sPass.length()>=6){
                    arcDialog.show();
                    mAuth.createUserWithEmailAndPassword(sCorreo,sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                String mid = mAuth.getCurrentUser().getUid();
                                saveUser(mid, sCorreo);
                            }else{
                                Toast.makeText(Register.this, "Fallo al registrar al usuario", Toast.LENGTH_SHORT).show();
                            }
                            arcDialog.dismiss();
                        }
                    });

                }else{
                    Toast.makeText(Register.this, "La longitud de la contraseña debe ser mayor a 6 caracteres", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(Register.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Register.this, "Falta ingresar algun dato", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUser(String id, String email) {
        User user = new User();
        user.setEmail(email);

        mDatabase.child("Users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Register.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}