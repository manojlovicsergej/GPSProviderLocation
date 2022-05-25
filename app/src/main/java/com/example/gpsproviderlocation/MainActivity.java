package com.example.gpsproviderlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView geoLatitudeTextView;
    private TextView geoLongitudeTextView;

    private Button getGpsLocationButton;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initializing variables
        geoLatitudeTextView = findViewById(R.id.geoLatitude);
        geoLongitudeTextView = findViewById(R.id.geoLongitude);
        getGpsLocationButton = findViewById(R.id.getGpsLocationButton);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //getting location on button click , refreshing state
        getGpsLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

    }

    @SuppressLint("MissingPermission")
    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30, 1, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    geoLatitudeTextView.setText(String.valueOf(location.getLatitude()));
                    geoLongitudeTextView.setText(String.valueOf(location.getLongitude()));
                }
            });
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        getLocation();
    }
}
