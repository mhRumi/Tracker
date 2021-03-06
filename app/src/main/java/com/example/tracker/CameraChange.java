package com.example.tracker;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;

public class CameraChange {

    public static void setCameraPosition(Double latitude, Double longitude){
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)) // Sets the new camera position
                .zoom(17)
                .bearing(180) // Rotate the camera
                .tilt(30) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder

        MainActivity.mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);

    }
}
