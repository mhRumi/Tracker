package com.example.tracker;

import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tracker.MainActivity.context;
import static com.example.tracker.MainActivity.navigationMapRoute;

public class Route {

    public static void  getRoute(com.mapbox.geojson.Point Origin, Point destination){

        NavigationRoute.builder(context)
                .accessToken(Mapbox.getAccessToken())
                .origin(Origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {

                        DirectionsRoute currentRoute = response.body().routes().get(0);

                        if(navigationMapRoute != null)
                        {
                            navigationMapRoute.removeRoute();
                        }else{
                            navigationMapRoute = new NavigationMapRoute(null, MainActivity.mapView,MainActivity.mapboxMap);
                        }

                        navigationMapRoute.addRoute(currentRoute);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {

                    }
                });
    }
}
