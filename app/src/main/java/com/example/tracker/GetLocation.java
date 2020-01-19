package com.example.tracker;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class GetLocation {
    public static final String url_getLocation = "http://192.168.31.68/Tracker/getLocation.php";
    public static Double otherLatitude ;
    public static Double otherLongitude;

    public static Double startLatitude = MainActivity.latitude ;
    public static Double startLongitude = MainActivity.longitude;

    public static void getLocation(Context context, String Gps_Id, MapboxMap mapboxMap){


        StringRequest stringRequest = new StringRequest(POST, url_getLocation, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    String[] location = response.toString().split("\n");
                    for(int i=0; i<location.length; i++){
                        String[] individual = location[i].split(" ");
                        if(individual.length >= 2){

                            otherLatitude = Double.parseDouble(individual[0]);
                            otherLongitude = Double.parseDouble(individual[1]);
                            startLatitude = otherLatitude;
                            startLongitude = otherLongitude;
                            MainActivity.destination = new LatLng(otherLatitude,otherLongitude);
                        }

                    }
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("GpsId",Gps_Id);

                return params;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
}
