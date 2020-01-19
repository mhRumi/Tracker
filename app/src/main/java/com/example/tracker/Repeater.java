package com.example.tracker;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;

import static com.example.tracker.MainActivity.context;
import static com.example.tracker.MainActivity.locationEngine;
import static com.example.tracker.MainActivity.longitude;
import static com.example.tracker.MainActivity.point;

public class Repeater implements Runnable {
    TextView Meter;
    @Override
    public void run() {

        //SpeedMeter.distance(MainActivity.firstLatitude, MainActivity.firstLongitude,MainActivity.latitude, longitude);

        // this line commenting out because don't using android phone's gps
        //LocationStore.updateLocation(MainActivity.context, MainActivity.latitude, MainActivity.longitude);

        GetLocation.getLocation(MainActivity.context,MainActivity.androidId,MainActivity.mapboxMap);

        LatLng start = new LatLng(GetLocation.startLatitude,GetLocation.startLongitude);
        if(MainActivity.destination == null){
            MainActivity.destination = point;
        }
        if(start == null){
            start = new LatLng( MainActivity.latitude, MainActivity.longitude);
        }



        if(MainActivity.startTrack) {


            if (MainActivity.animator != null && MainActivity.animator.isStarted()) {
                MainActivity.currentPosition = (LatLng) MainActivity.animator.getAnimatedValue();
                MainActivity.animator.cancel();
            }

            MainActivity.animator = ObjectAnimator
                    .ofObject(latLngEvaluator, start, MainActivity.destination)
                    .setDuration(1000);
            MainActivity.animator.addUpdateListener(animatorUpdateListener);
            MainActivity.animator.start();



        }
        MainActivity.handler.postDelayed(this, 500);
    }

    private final ValueAnimator.AnimatorUpdateListener animatorUpdateListener =
            new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    LatLng animatedPosition = (LatLng) valueAnimator.getAnimatedValue();
                    MainActivity.geoJsonSource.setGeoJson(Point.fromLngLat(animatedPosition.getLongitude(), animatedPosition.getLatitude()));
                }
            };

    private static final TypeEvaluator<LatLng> latLngEvaluator = new TypeEvaluator<LatLng>() {

        private final LatLng latLng = new LatLng();

        @Override
        public LatLng evaluate(float fraction, LatLng startValue, LatLng endValue) {
            latLng.setLatitude(startValue.getLatitude()
                    + ((endValue.getLatitude() - startValue.getLatitude()) * fraction));
            latLng.setLongitude(startValue.getLongitude()
                    + ((endValue.getLongitude() - startValue.getLongitude()) * fraction));
            return latLng;
        }
    };
}
