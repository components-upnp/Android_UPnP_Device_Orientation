package com.example.mkostiuk.android_upnp_device_orientation.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mkostiuk.android_upnp_device_orientation.R;

import xdroid.toaster.Toaster;


public class App extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);


        //Vérification que l'autorisation d'accès au système de stockage est accrodée
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Cela signifie que la permission à déjà était
                //demandé et l'utilisateur l'a refusé
                //Vous pouvez aussi expliquer à l'utilisateur pourquoi
                //cette permission est nécessaire et la redemander
                Toaster.toast("Vous avez refusé l'accés au Stockage, fermeture");
                finish();
            } else {
                //Sinon demander la permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_STORAGE);
            }
        }
        else {
            //Permission déjà accrodée
            init();
        }
    }

    public void init() {
        startService(new Intent(this, AppService.class));
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // La permission est garantie on initialise les services et boutons
                    init();
                } else {
                    Toaster.toast("Permission refusée, fermeture");
                    finish();
                }
                return;
            }
        }
    }

}
