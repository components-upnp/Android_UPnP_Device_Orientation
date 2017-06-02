package com.example.mkostiuk.android_upnp_device_orientation.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mkostiuk.android_upnp_device_orientation.R;


public class App extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        startService(new Intent(this, AppService.class));

        finish();
    }



}
