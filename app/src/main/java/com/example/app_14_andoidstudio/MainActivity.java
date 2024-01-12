package com.example.app_14_andoidstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText dni;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraintLayout);
        name = (EditText) findViewById(R.id.editTextName);
        surname = (EditText) findViewById(R.id.editTextSurname);
        email = (EditText) findViewById(R.id.editTextmailAddress);
        dni = (EditText) findViewById(R.id.editTextDni);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void save(View view){
        SharedPreferences preferences=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String nom = name.getText().toString();
        String cognom = surname.getText().toString();
        String correu = email.getText().toString();
        String nif = dni.getText().toString();

        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("username",nom);
        editor.putString("usersurname",cognom);
        editor.putString("correu",correu);
        editor.putString("dni", nif);
        editor.commit();
    }

    public void recover(View view){
        SharedPreferences preferences = getSharedPreferences("agenda",Context.MODE_PRIVATE);
        String nom = preferences.getString("username","");
        String cognom = preferences.getString("usersurname","");
        String correu = preferences.getString("correu","");
        String nif = preferences.getString("dni","");
        name.setText(nom);
        surname.setText(cognom);
        email.setText(correu);
        dni.setText(nif);

//        Intent intent = new Intent(this, MainActivity2.class);
//        intent.putExtra("username", nom);
//        intent.putExtra("usersurname", cognom);
//        intent.putExtra("useremail", correu);
//        intent.putExtra("dni", nif);
//        startActivity(intent);

    }

    public void go(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    public void exit(View view) {
        finish();
        System.exit(0);
        // https://stackoverflow.com/questions/6014028/closing-application-with-exit-button
    }
}