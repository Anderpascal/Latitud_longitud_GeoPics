package com.example.latitud_longitud_geopics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private LocationManager ubicacion;
    TextView latitud;
    TextView longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localizacion();
        registrarLocalizacion();
    }

    private void localizacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1000);
        }
        latitud = findViewById(R.id.txtLatitud);
        longitud = findViewById(R.id.txtLongitud);
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (loc != null) {
            latitud.setText("Latitud: " + loc.getLatitude());
            longitud.setText("Longitud: " + loc.getLongitude());
        }

    }

    private void registrarLocalizacion() {
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, new miLocalizacionListener());
    }

    private class miLocalizacionListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            System.out.println("La dirección ha cambiado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
//
//    private void listaProvider() {
//        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        List<String> listaProvider = ubicacion.getAllProviders();
//
//        String mejorProvider = ubicacion.getBestProvider(mejorCriterio(), false);
//        System.out.println(mejorProvider);
//
//        LocationProvider provider = ubicacion.getProvider(listaProvider.get(0));
//        System.out.println(provider.getAccuracy());
//        System.out.println(provider.getPowerRequirement());
//        System.out.println(provider.supportsAltitude());
//    }

//
//    private Criteria mejorCriterio() {
//        Criteria requerimiento = new Criteria();
//        requerimiento.setAccuracy(Criteria.ACCURACY_FINE);
//        requerimiento.setAltitudeRequired(true);
//        return requerimiento;
//    }
//
//    private boolean estadoGPS() {
//        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (!ubicacion.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            Log.d("GPS", "NO ACTIVADO");
//        } else {
//            Log.d("GPS", "ACTIVADO");
//        }
//        return true;
//    }


}
