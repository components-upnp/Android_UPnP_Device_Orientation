package com.example.mkostiuk.android_upnp_device_orientation.main;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.EditText;

import com.example.mkostiuk.android_upnp_device_orientation.upnp.OrientationController;
import com.example.mkostiuk.android_upnp_device_orientation.xml.GenerateurXml;

import org.fourthline.cling.model.meta.LocalService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Created by mkostiuk on 01/06/2017.
 */

public class SensorListener implements SensorEventListener {


    private GenerateurXml gen;
    private LocalService<OrientationController> orientationService;
    private String udn;

    public SensorListener(LocalService<OrientationController> o, String u) {
        orientationService = o;
        gen = new GenerateurXml();
        udn = u;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            String commande = gen.getDocXml( udn,
                    String.valueOf(event.values[0]),
                    String.valueOf(event.values[1]),
                    String.valueOf(event.values[2]));

        orientationService.getManager().getImplementation()
                .sendCommande(commande);

        } catch (ParserConfigurationException e) {
         e.printStackTrace();
        } catch (TransformerException e) {
         e.printStackTrace();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        System.err.println("Changement de précision!!!");
    }
}
