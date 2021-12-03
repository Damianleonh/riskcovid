package com.leon.prueb1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.leon.prueb1.includes.MyToolbar;

public class ActivityResultado extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    Double factorRiesgo,imc;
    int factores;
    TextView textPorcentaje, resultadotxt;
    Button diabetes, hipertension, enfpulmonar, enfrenal, inmuno, edadavaz;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //MOSTRAR TOOLBAR
        MyToolbar.show(this, "Tu riesgo", true);

        progressBar = (ProgressBar) findViewById(R.id.actResulProgressBar);
        textPorcentaje = (TextView) findViewById(R.id.actResulTextvPorcentaje);
        resultadotxt = (TextView) findViewById(R.id.resultadotxt);

        diabetes = (Button) findViewById(R.id.actResulBtnDiabetes);
        hipertension = (Button) findViewById(R.id.actResulBtnHipertension);
        enfpulmonar = (Button) findViewById(R.id.actResulBtnPulmon);
        enfrenal = (Button) findViewById(R.id.actResulBtnRenal);
        inmuno = (Button) findViewById(R.id.actResulBtnInmunosup);
        edadavaz = (Button) findViewById(R.id.actResulBtnEdadav);


        imc = Double.parseDouble(getIntent().getStringExtra("imcresultado"));
        factorRiesgo = Double.parseDouble(getIntent().getStringExtra("factorRiesgo"));
        factores = Integer.parseInt(getIntent().getStringExtra("factores"));

        int porcentaje = Integer.valueOf(factorRiesgo.intValue());

        progressBar.setProgress(porcentaje);
        textPorcentaje.setText(porcentaje+"%");
        resultadotxt.setText("Tu resultado mostro un "+porcentaje+"% de probabilidad de contrer la enfermedad COVID y esta resulte prerjudicial. Cuentas con un IMC de "+
                Math.round(imc)+" como resultado normal. Cuentas con "+factores+" factores de riesgo.");

        diabetes.setOnClickListener(this);
        hipertension.setOnClickListener(this);
        enfpulmonar.setOnClickListener(this);
        enfrenal.setOnClickListener(this);
        inmuno.setOnClickListener(this);
        edadavaz.setOnClickListener(this);

        registerForContextMenu(diabetes);
        registerForContextMenu(hipertension);
        registerForContextMenu(enfpulmonar);
        registerForContextMenu(enfrenal);
        registerForContextMenu(inmuno);
        registerForContextMenu(edadavaz);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actResulBtnDiabetes:
                String url = "https://www.imss.gob.mx/salud-en-linea/diabetes-mellitus";
                Uri uri = Uri.parse(url);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
                break;

            case R.id.actResulBtnHipertension:
                String url1 = "https://www.imss.gob.mx/salud-en-linea/hipertension-arterial";
                Uri uri1 = Uri.parse(url1);
                Intent i1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(i1);
                break;

            case R.id.actResulBtnPulmon:
                String url2 = "https://www.imss.gob.mx/prensa/archivo/201808/203#:~:text=En%202017%2C%20la%20Enfermedad%20Pulmonar,del%20Seguro%20Social%20(IMSS).";
                Uri uri2 = Uri.parse(url2);
                Intent i2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(i2);
                break;

            case R.id.actResulBtnRenal:
                String url3 = "https://medlineplus.gov/spanish/ency/article/000471.htm";
                Uri uri3 = Uri.parse(url3);
                Intent i3 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(i3);
                break;

            case R.id.actResulBtnInmunosup:
                String url4 = "https://www.cancer.gov/espanol/cancer/causas-prevencion/riesgo/inmunosupresion";
                Uri uri4 = Uri.parse(url4);
                Intent i4 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(i4);
                break;

            case R.id.actResulBtnEdadav:
                String url5 = "https://www.msdmanuals.com/es/hogar/salud-de-las-personas-de-edad-avanzada/cuestiones-sociales-que-afectan-a-las-personas-mayores/cuidado-familiar-de-las-personas-mayores";
                Uri uri5 = Uri.parse(url5);
                Intent i5 = new Intent(Intent.ACTION_VIEW, uri5);
                startActivity(i5);
                break;

        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.actResulBtnDiabetes:
                getMenuInflater().inflate(R.menu.opciones, menu);
                break;
            case R.id.actResulBtnHipertension:
                getMenuInflater().inflate(R.menu.opciones, menu);
                break;
            case R.id.actResulBtnPulmon:
                getMenuInflater().inflate(R.menu.opciones, menu);
                break;
            case R.id.actResulBtnRenal:
                getMenuInflater().inflate(R.menu.opciones, menu);
                break;

            case R.id.actResulBtnInmunosup:
                getMenuInflater().inflate(R.menu.opciones, menu);
                break;

            case R.id.actResulBtnEdadav:
                getMenuInflater().inflate(R.menu.opciones, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menudesOpcionInfo:
                Toast.makeText(this, "El siguiente boton lleva a un link con informacion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menudesOpcionSalir:
                Intent i = new Intent(ActivityResultado.this,MainActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }
}