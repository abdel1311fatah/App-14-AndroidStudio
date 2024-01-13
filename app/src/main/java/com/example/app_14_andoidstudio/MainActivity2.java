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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.InputStreamReader;

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
    private TextView resultat;
    private Spinner escola;
    private RadioButton treballa_si;
    private RadioButton treballa_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        constraintLayout = findViewById(R.id.constraintLayout);
        carrer = findViewById(R.id.editTextCarrer);
        numero = findViewById(R.id.editTextNumero);

        postal = findViewById(R.id.editTextPostal);
        poblacio = findViewById(R.id.editTextPoblacio);
        resultat = findViewById(R.id.resultat);
        estudis_acabats = findViewById(R.id.editTextEstudisAcabats);
        estudis_cursant = findViewById(R.id.editTextEstudisNoAcabats);

        escola = findViewById(R.id.schoolSpinner);
        String[] escoles = {"LaSalle", "Escuelas manolita", "Escuelas Paquita", "Escuelas Pablo Motos"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, escoles);
        escola.setAdapter(adaptador);

        treballa_si = findViewById(R.id.radioButtonSi);
        treballa_no = findViewById(R.id.radioButtonNo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void save(View view) {
        try {

            ArrayList<String> items = new ArrayList<>();
            items.add(carrer.getText().toString());
            items.add(numero.getText().toString());
            items.add(postal.getText().toString());
            items.add(poblacio.getText().toString());
            items.add(estudis_acabats.getText().toString());
            items.add(estudis_cursant.getText().toString());
            items.add(escola.getSelectedItem().toString());

            for (String s : items) {
                if (s.isEmpty()) { // mire que el string tingui mes llargada que 0 per a que no estigui buit
                    Toast.makeText(this, "No pots deixar camps en blanc", Toast.LENGTH_SHORT).show();
                }
                char[] paraula = s.toCharArray();
                for (int i = 0; i < paraula.length; i++) {
                    if (!Character.isLetterOrDigit(paraula[i])) { // https://www.geeksforgeeks.org/character-isletterordigit-in-java-with-examples/ per validar que no fiquin nomes espais en blanc o simbols
                        Toast.makeText(this, "Has d' introduir caracters valids, lletres o numeros", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("dades_alumne.txt", MainActivity2.MODE_PRIVATE));

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
            } else {
                Toast.makeText(this, "Has de seleccionar si treballes o no", Toast.LENGTH_SHORT).show();
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

        String archivos [ ] = fileList();
        if(ArxiuExis(archivos, "dades_alumne.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("dades_alumne.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine( );
                String dadesEscriure = "";


                while(linea != null){
                    dadesEscriure = dadesEscriure + linea + "\n";
                    linea = br.readLine();
                }
                br.close();   //tancar el buffer
                archivo.close();   //tancar l’arxiu
                resultat.setText(dadesEscriure);
            }catch (IOException e){
            }
        }
    }
    public void go(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean ArxiuExis(String archivos[], String NombreArchivo) {
        Boolean exist = false;
        for (int i = 0; i < archivos.length; i++) {
            if (NombreArchivo.equals(archivos[i])) {
                exist = true;
            }

        }
        return exist;
    }
}
