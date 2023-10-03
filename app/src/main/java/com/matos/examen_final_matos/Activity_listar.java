package com.matos.examen_final_matos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
        reference.child("Lugar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaLugares.clear();
                for (DataSnapshot item:snapshot.getChildren()) {
                    Lugar lugar = item.getValue(Lugar.class);
                    listaLugares.add(lugar);
                }
                adaptador = new ArrayAdapter<Lugar>(Activity_listar.this,
                        android.R.layout.simple_list_item_1,
                        listaLugares);
                lstLugares.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    private void asignarReferencias() {
        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        lstLugares = findViewById(R.id.lstLugares);
        lstLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Activity_listar.this,
                        "" + listaLugares.get(i).getLatitud(),
                        Toast.LENGTH_LONG);

                //mostrarMensaje(listaLugares.get(i).getLatitud());
            }
        });
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
}