package com.example.app_14_andoidstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private HashMap<String, Integer> adreça;
    private EditText carrer;
    private EditText numero;
    private EditText postal;
    private EditText poblacio;
    private EditText estudis_acabats;
    private EditText estudis_cursant;
    private Spinner escola;
    private RadioButton treballa_si;
    private RadioButton treballa_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        constraintLayout = findViewById(R.id.constraintLayout);
        carrer = (EditText) findViewById(R.id.editTextCarrer);
        numero = (EditText) findViewById(R.id.editTextNumero);

        adreça.put(carrer.getText().toString(), Integer.parseInt(numero.getText().toString()));

        postal = (EditText) findViewById(R.id.editTextPostal);
        poblacio = (EditText) findViewById(R.id.editTextPoblacio);
        estudis_acabats = (EditText) findViewById(R.id.editTextEstudisAcabats);
        estudis_cursant = (EditText) findViewById(R.id.editTextEstudisNoAcabats);

        escola = findViewById(R.id.schoolSpinner);
        String[] escoles = {"LaSalle", "Escuelas manolita", "Escuelas Paquita", "Escuelas Pablo Motos"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, escoles);
        escola.setAdapter(adaptador);

        treballa_si = (RadioButton) findViewById(R.id.radioButtonSi);
        treballa_no = (RadioButton) findViewById(R.id.radioButtonNo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void save(View view) {

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("fitxer.txt", MainActivity2.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}

    }
public void recover(View view){

        }
public void go(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        }

        }