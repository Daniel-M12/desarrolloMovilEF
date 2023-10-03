package com.matos.examen_final_matos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.matos.examen_final_matos.model.Lugar;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText txtNombreLugar, txtLatitud, txtLongitud;
    Button btnRegistrar;
    FirebaseDatabase database;
    DatabaseReference reference;

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
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {
            registrar();
        });
    }

    private void registrar() {
        String nombreLugar = txtNombreLugar.getText().toString();
        String latitud = txtLatitud.getText().toString();
        String longitud = txtLongitud.getText().toString();

        Lugar lugar = new Lugar();

        lugar.setId(UUID.randomUUID().toString());
        lugar.setNombreLugar(nombreLugar);
        lugar.setLatitud(latitud);
        lugar.setLongitud(longitud);

        reference.child("Lugar")
                .child(lugar.getId())
                .setValue(lugar);

        mostrarMensaje("Lugar Registrado");
    }

    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje inormativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", (dialogInterface, i) -> {
            Intent intent = new Intent(this, Activity_listar.class);
            startActivity(intent);
        });
        ventana.create().show();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
}