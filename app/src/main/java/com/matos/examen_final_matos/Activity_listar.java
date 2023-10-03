package com.matos.examen_final_matos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.matos.examen_final_matos.model.Lugar;

import java.util.ArrayList;
import java.util.List;

public class Activity_listar extends AppCompatActivity {

    FloatingActionButton btnAgregar;
    ListView lstLugares;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<Lugar> listaLugares = new ArrayList<>();
    ArrayAdapter<Lugar> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        asignarReferencias();
        inicializarFirebase();
        listarDatos();
    }

    private void listarDatos() {
    }

    private void inicializarFirebase() {
    }

    private void asignarReferencias() {
    }
}