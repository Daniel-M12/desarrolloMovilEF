package com.matos.examen_final_matos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    float latitud, longitud;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);

        recuperarDatos();

        LatLng lugarUbicado = new LatLng(latitud, longitud);
        map.addMarker(new MarkerOptions().position(lugarUbicado).title(nombre));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(lugarUbicado, 17));

    }

    private void recuperarDatos() {
        latitud = Float.parseFloat(getIntent().getStringExtra("pLatitud"));
        longitud = Float.parseFloat(getIntent().getStringExtra("pLongitud"));
        nombre = getIntent().getStringExtra("pNombre");
    }
}