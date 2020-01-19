package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class Bus_schedule extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule);

        layout = findViewById(R.id.one);
        layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.one){
            Toast.makeText(getApplicationContext(),"Tracker is connecting with this bus",Toast.LENGTH_SHORT).show();
            MainActivity.needed_location_Id = "1a68e8adc0113507";
            MainActivity.androidId = "01";

            Repeater ob = new Repeater();
            ob.run();

        }
    }
}
