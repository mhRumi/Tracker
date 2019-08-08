package com.example.tracker;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class LocationStore {
    public static final String url_LocationUpdate = "http://192.168.1.102/Tracker/DbConnect.php";

    public static void updateLocation(Context context,String GpsId,Double Latitude, Double Longitude, String Road){

        StringRequest stringRequest = new StringRequest(POST, url_LocationUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    if(response.toString().equals("Updated successfully")){



                    }else{
                        //progressBar.setVisibility(View.GONE);
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
                params.put("GpsId",GpsId);
                params.put("Latitude",Latitude.toString());
                params.put("Longitude",Longitude.toString());
                params.put("Road",Road);

                return params;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
}
