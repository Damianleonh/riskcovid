package com.leon.prueb1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leon.prueb1.includes.MyToolbar;


public class ActivityCalculadora extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner mSpinnerEdad, mSpinnerPeso, mSpinnerFamilia,
            mSpinnerEstatura, mSpinnerVacuna, mSpinnerContacto;

    Integer[] arrayEdades = new Integer[100];
    Integer[] arrayPeso = new Integer[100];
    Integer[] arrayEstatura = new Integer[100];
    String[] arrayFamiliares = {"0 a 2", "2 a 5", "5 o mas"};
    String[] arrayVacunacion = {"Ninguna", "1 dosis", "2 dosis"};
    String[] arrayContacto = {"Bajo", "Medio", "Alto"};

    Button mBtnCalcular;

    CheckBox mCheckBoxHip, mCheckBoxDiab, mCheckBoxInmun, mCheckBoxEnfRen, mCheckBoxEnfPul;

    Double peso, estatura;
    Double factoRiesgo = 0.0;
    Integer factores = 0;
    String imcresultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        //MOSTRAR TOOLBAR
        MyToolbar.show(this, "Calculadora", true);

        //LLAMAR VARIABLES DESDE XML
        mSpinnerEdad = findViewById(R.id.actCalSpinnerEdad);
        mSpinnerPeso = findViewById(R.id.actCalSpinnerPeso);
        mSpinnerFamilia = findViewById(R.id.actCalSpinnerFamilia);
        mSpinnerEstatura = findViewById(R.id.actCalSpinnerEstatura);
        mSpinnerVacuna = findViewById(R.id.actCalSpinnerVacuna);
        mSpinnerContacto = findViewById(R.id.actCalSpinnerContacto);
        mBtnCalcular = findViewById(R.id.actCalBtnCalcular);

        mCheckBoxDiab = findViewById(R.id.actCalCheckBoxDiabet);
        mCheckBoxEnfPul = findViewById(R.id.actCalCheckBoxEnfPul);
        mCheckBoxEnfRen = findViewById(R.id.actCalCheckBoxEnfRen);
        mCheckBoxHip = findViewById(R.id.actCalCheckBoxHipert);
        mCheckBoxInmun = findViewById(R.id.actCalCheckBoxInmun);

        //LENAR ARRAY
        for (int i = 0; i < arrayEdades.length; i++) {
            arrayPeso[i] = i + 20;
            arrayEdades[i] = i;
            arrayEstatura[i] = i + 100;
        }

//####### Sppiner Config ######################################################################
        ArrayAdapter<String> aac = new ArrayAdapter<String>(ActivityCalculadora.this,
                R.layout.color_spinner_layout, arrayFamiliares);
        ArrayAdapter<String> aae = new ArrayAdapter<String>(ActivityCalculadora.this,
                R.layout.color_spinner_layout, arrayVacunacion);
        ArrayAdapter<String> aaf = new ArrayAdapter<String>(ActivityCalculadora.this,
                R.layout.color_spinner_layout, arrayContacto);
        ArrayAdapter<Integer> aa = new ArrayAdapter<Integer>(ActivityCalculadora.this,
                R.layout.color_spinner_layout, arrayEdades);
        ArrayAdapter<Integer> aab = new ArrayAdapter<Integer>(ActivityCalculadora.this,
                R.layout.color_spinner_layout, arrayPeso);
        ArrayAdapter<Integer> aad = new ArrayAdapter<Integer>(ActivityCalculadora.this,
                R.layout.color_spinner_layout, arrayEstatura);
        aa.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        aab.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        aac.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        aad.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        aae.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        aaf.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        mSpinnerEdad.setAdapter(aa); //
        mSpinnerPeso.setAdapter(aab);//
        mSpinnerFamilia.setAdapter(aac);//
        mSpinnerEstatura.setAdapter(aad); //
        mSpinnerVacuna.setAdapter(aae);//
        mSpinnerContacto.setAdapter(aaf);

        mSpinnerEdad.setOnItemSelectedListener(this);
        mSpinnerPeso.setOnItemSelectedListener(this);
        mSpinnerFamilia.setOnItemSelectedListener(this);
        mSpinnerEstatura.setOnItemSelectedListener(this);
        mSpinnerVacuna.setOnItemSelectedListener(this);
        mSpinnerContacto.setOnItemSelectedListener(this);
//#########################################################################################


        mBtnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double imc = peso/(estatura*estatura);
                if (imc<18.5) {
                    imcresultado= "BAJO PESO";
                    factoRiesgo += 20;
                } else if (imc>=18.5 && imc<=24.9) {
                    imcresultado ="NORMAL";
                } else if (imc>=25 && imc<=29.9) {
                    imcresultado ="SOBREPESO";
                    factoRiesgo += 30;
                    factores += 1;
                } else {
                    imcresultado= "OBESIDAD";
                    factoRiesgo += 30;
                    factores += 1;
                }

                factoRiesgo = (factoRiesgo*100)/250;
                if (factoRiesgo > 100){
                    factoRiesgo = 100.0;
                }

                Toast.makeText(ActivityCalculadora.this,  factoRiesgo+"%" + " \nResultado imc: "+
                        imcresultado+"\nFactores = "+factores, Toast.LENGTH_LONG).show();


                Intent i = new Intent(ActivityCalculadora.this,ActivityResultado.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == R.id.actCalSpinnerEdad) {
            int edad = arrayEdades[i];
            if (edad <= 60) {
                factoRiesgo += 40;
                factores += 1;
            }if (edad > 40 && edad < 60) {
                factoRiesgo += 20;
            }
        }if (adapterView.getId() == R.id.actCalSpinnerPeso) {
            peso = arrayPeso[i]+0.0;
        }if (adapterView.getId() == R.id.actCalSpinnerEstatura) {
            estatura = arrayEstatura[i]/100.0;

        }if (adapterView.getId() == R.id.actCalSpinnerFamilia) {
            String familiares = arrayFamiliares[i];
            if (familiares.equals("2 a 5")) {
                factoRiesgo += 20;
            }
            if (familiares.equals("5 o mas")) {
                factoRiesgo += 30;
            }
        }if (adapterView.getId() == R.id.actCalSpinnerVacuna) {
            String vacuna = arrayVacunacion[i];
            if (vacuna.equals("1 dosis")) {
                factoRiesgo = factoRiesgo - 20;
            }
            if (vacuna.equals("2 dosis")) {
                factoRiesgo = factoRiesgo - 30;
            }
        }if (adapterView.getId() == R.id.actCalSpinnerContacto) {
            String contacto = arrayContacto[i];
            if (contacto.equals("Medio")) {
                factoRiesgo += 10;
            }
            if (contacto.equals("Alto")) {
                factoRiesgo += 20;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.actCalCheckBoxDiabet:
                if (checked) {
                    factoRiesgo += 50;
                    factores += 1;
                }else {
                    break;
                }
            case R.id.actCalCheckBoxEnfPul:
                if (checked) {
                    factoRiesgo += 80;
                    factores += 1;
                }else {
                    break;
                }
            case R.id.actCalCheckBoxEnfRen:
                if (checked) {
                    factoRiesgo += 50;
                    factores += 1;
                }else {
                    break;
                }
            case R.id.actCalCheckBoxHipert:
                if (checked) {
                    factoRiesgo += 50;
                    factores += 1;
                }else {
                    break;
                }
            case R.id.actCalCheckBoxInmun:
                if (checked) {
                    factoRiesgo += 80;
                    factores += 1;
                }else{
                    break;
                }
        }
    }
}