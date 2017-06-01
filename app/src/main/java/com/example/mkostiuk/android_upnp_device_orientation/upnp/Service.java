package com.example.mkostiuk.android_upnp_device_orientation.upnp;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.types.UDAServiceType;
import org.fourthline.cling.model.types.UDN;

/**
 * Created by mkostiuk on 01/06/2017.
 */

public class Service {

    private AndroidUpnpService upnpService;
    private UDN udn;
    private ServiceConnection serviceConnection;

    public Service() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                upnpService = (AndroidUpnpService) service;

                LocalService<OrientationController> orientationService = getOrientationService();

                // Register the device when this activity binds to the service for the first time
                if (orientationService == null) {
                    try {
                        udn = new SaveUDN().getUdn();
                        LocalDevice remoteDevice = OrientationDevice.createDevice(udn);

                        upnpService.getRegistry().addDevice(remoteDevice);

                    } catch (Exception ex) {
                        System.err.println("Creating Android remote controller device failed !!!");
                        return;
                    }
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                upnpService = null;
            }
        };
    }

    public ServiceConnection getService() {
        return serviceConnection;
    }

    public UDN getUdn() {
        return udn;
    }

    public LocalService<OrientationController> getOrientationService() {
        if (upnpService == null)
            return null;

        LocalDevice remoteDevice;
        if ((remoteDevice = upnpService.getRegistry().getLocalDevice(udn, true)) == null)
            return null;

        return (LocalService<OrientationController>)
                remoteDevice.findService(new UDAServiceType("OrientationService", 1));
    }
}
