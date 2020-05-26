package com.example.latitud_longitud_geopics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private LocationManager locManager;
    private Location loc;
    TextView mensaje1;
    TextView mensaje2;
    TextView mensaje3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensaje1 = findViewById(R.id.latitud);
        mensaje2 = findViewById(R.id.longitud);
        mensaje3 = findViewById(R.id.textView5);
    }

    public void Conectar(View view){
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mensaje1.setText("No se han definido los permisos necesarios.");
            mensaje2.setText("");
        }
        try{
            locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            mensaje1.setText(String.valueOf(loc.getLatitude()));
            mensaje2.setText(String.valueOf(loc.getLongitude()));

        } catch (NullPointerException e) {
            mensaje3.setText("Error");
        }
    }

}
