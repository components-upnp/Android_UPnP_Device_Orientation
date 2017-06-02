package com.example.mkostiuk.android_upnp_device_orientation.main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.mkostiuk.android_upnp_device_orientation.upnp.OrientationController;

import org.fourthline.cling.android.AndroidUpnpServiceImpl;
import org.fourthline.cling.model.meta.LocalService;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import xdroid.toaster.Toaster;

/**
 * Created by mkostiuk on 01/06/2017.
 */

public class AppService extends Service {

    private ServiceConnection serviceConnection;
    private Context context;
    private com.example.mkostiuk.android_upnp_device_orientation.upnp.Service service;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread().start();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        File dir;
        System.err.println(Build.BRAND);
        if (Build.BRAND.toString().equals("htc_europe"))
            dir = new File("/mnt/emmc/AndroidOrientation/");
        else
            dir = new File(Environment.getExternalStorageDirectory().getPath() + "/AndroidOrientation/");

        while (!dir.exists()) {
            dir.mkdir();
            dir.setReadable(true);
            dir.setExecutable(true);
            dir.setWritable(true);
        }

        context = this;
        service = new com.example.mkostiuk.android_upnp_device_orientation.upnp.Service();
        serviceConnection = service.getService();

        getApplicationContext().bindService(
                new Intent(this, AndroidUpnpServiceImpl.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE
        );



        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);


        Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                sensorManager.registerListener(new SensorListener(service.getOrientationService(), service.getUdn().toString()),
                        sensor,
                        100000);
            }
        }, 20000);


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Permet de mettre l'exécution en pause, afin d'avoir le temps de recevoir les évènements
    public static void pause(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
