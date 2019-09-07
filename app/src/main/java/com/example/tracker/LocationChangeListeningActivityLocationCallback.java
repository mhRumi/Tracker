package com.example.tracker;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineResult;

import java.lang.ref.WeakReference;

public class LocationChangeListeningActivityLocationCallback implements LocationEngineCallback<LocationEngineResult>  {

    private final WeakReference< MainActivity> activityWeakReference;

    LocationChangeListeningActivityLocationCallback(MainActivity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onSuccess(LocationEngineResult result) {

        MainActivity activity = activityWeakReference.get();

        if (activity != null) {
            MainActivity.location = result.getLastLocation();
            MainActivity.latitude = MainActivity.location.getLatitude();
            MainActivity.longitude = MainActivity.location.getLongitude();
            if (MainActivity.location == null) {
                return;
            }

            LocationStore.updateLocation(MainActivity.context, MainActivity.latitude, MainActivity.longitude);

            // Pass the new location to the Maps SDK's LocationComponent
            if (activity.mapboxMap != null && result.getLastLocation() != null) {
                activity.mapboxMap.getLocationComponent().forceLocationUpdate(result.getLastLocation());
            }
        }
    }

    @Override
    public void onFailure(@NonNull Exception exception) {
        Log.d("LocationChangeActivity", exception.getLocalizedMessage());
        MainActivity activity = activityWeakReference.get();
        if (activity != null) {
            Toast.makeText(activity, exception.getLocalizedMessage(),
                    Toast.LENGTH_SHORT).show();
        }

    }
}
