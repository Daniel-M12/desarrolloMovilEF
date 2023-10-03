package com.matos.examen_final_matos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtNombreLugar, txtLatitud, txtLongitud;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarFirebase();
        asignarReferencias();
    }

    private void asignarReferencias() {
        txtNombreLugar = findViewById(R.id.txtNombreLugar);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        btnRegistrar.setOnClickListener(v -> {
            registrar();
        });
    }

    private void registrar() {

    }

    private void inicializarFirebase() {
    }
}