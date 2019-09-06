package com.example.tracker;

import android.content.Context;

import androidx.annotation.NonNull;

import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.building.BuildingPlugin;
import com.mapbox.mapboxsdk.plugins.traffic.TrafficPlugin;

public class Mode{
    private static BuildingPlugin buildingPlugin;
    private static TrafficPlugin trafficPlugin;

    public static void night(MapboxMap mapboxMap){
        MainActivity.mapboxMap = mapboxMap;
        MainActivity.mapboxMap.setStyle(Style.TRAFFIC_NIGHT , new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                buildingPlugin = new BuildingPlugin(MainActivity.mapView, MainActivity.mapboxMap, style);
                buildingPlugin.setMinZoomLevel(15f);
                buildingPlugin.setVisibility(true);

                trafficPlugin = new TrafficPlugin(MainActivity.mapView, MainActivity.mapboxMap, style);
                // Enable the traffic view by default
                trafficPlugin.setVisibility(true);

                MainActivity ob = new MainActivity();
                ob.enableLocationComponent(style);
            }
        });


    }



}
