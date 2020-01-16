package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.tracker.MainActivity.longitude;

public class SpeedMeter extends AppCompatActivity implements LocationListener {

    public static TextView Meter;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_meter);

        Meter = findViewById(R.id.meter);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.onLocationChanged(null);
    }



    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getApplicationContext(), "location is changed", Toast.LENGTH_SHORT).show();

        if(location == null)
        {

        }
        else
        {
            float crntspd = location.getSpeed();
            String currentSpeed = String.valueOf(crntspd);
            Meter.setText(currentSpeed);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
