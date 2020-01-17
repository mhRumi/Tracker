package com.example.tracker;

import android.annotation.SuppressLint;

import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;

import static android.os.Looper.getMainLooper;
import static com.example.tracker.MainActivity.DEFAULT_INTERVAL_IN_MILLISECONDS;
import static com.example.tracker.MainActivity.DEFAULT_MAX_WAIT_TIME;
import static com.example.tracker.MainActivity.locationEngine;

public class InitializeLocation {
    private static LocationChangeListeningActivityLocationCallback callback = new LocationChangeListeningActivityLocationCallback((MainActivity) MainActivity.context);

    @SuppressLint("MissingPermission")
    public static void initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(MainActivity.context);

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();

        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback);
    }
}
