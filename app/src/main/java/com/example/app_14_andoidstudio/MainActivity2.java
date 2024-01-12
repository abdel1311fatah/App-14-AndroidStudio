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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

// cambiar variables i estructura
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
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("dades_alumne.txt", MainActivity2.MODE_PRIVATE));

            // Write data to the file
            archivo.write("Carrer: " + carrer.getText().toString() + "\n");
            archivo.write("Numero: " + numero.getText().toString() + "\n");
            archivo.write("Postal: " + postal.getText().toString() + "\n");
            archivo.write("Poblacio: " + poblacio.getText().toString() + "\n");
            archivo.write("Estudis Acabats: " + estudis_acabats.getText().toString() + "\n");
            archivo.write("Estudis Cursant: " + estudis_cursant.getText().toString() + "\n");
            archivo.write("Escola: " + escola.getSelectedItem().toString() + "\n");

            if (treballa_si.isChecked()) {
                archivo.write("Treballa: Si\n");
            } else if (treballa_no.isChecked()) {
                archivo.write("Treballa: No\n");
            }

            archivo.flush();
            archivo.close();
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recover(View view) {
        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("dades_alumne.txt"));
            BufferedReader br = new BufferedReader(archivo);

            // Read data from the file and set values to the corresponding variables
            carrer.setText(getValueFromLine(br.readLine()));
            numero.setText(getValueFromLine(br.readLine()));
            postal.setText(getValueFromLine(br.readLine()));
            poblacio.setText(getValueFromLine(br.readLine()));
            estudis_acabats.setText(getValueFromLine(br.readLine()));
            estudis_cursant.setText(getValueFromLine(br.readLine()));

            String selectedEscola = getValueFromLine(br.readLine());
            int position = ((ArrayAdapter<String>) escola.getAdapter()).getPosition(selectedEscola);
            escola.setSelection(position);

            String treballaStatus = getValueFromLine(br.readLine());
            if ("Si".equals(treballaStatus)) {
                treballa_si.setChecked(true);
            } else if ("No".equals(treballaStatus)) {
                treballa_no.setChecked(true);
            }

            br.close();
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getValueFromLine(String line) {
        // Assuming the line format is key: value
        String[] parts = line.split(": ");
        if (parts.length == 2) {
            return parts[1];
        } else {
            return "";
        }
    }

    public void go(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}