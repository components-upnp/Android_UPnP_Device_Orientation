package com.example.mkostiuk.android_upnp_device_orientation.upnp;

import org.fourthline.cling.binding.LocalServiceBindingException;
import org.fourthline.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.fourthline.cling.model.DefaultServiceManager;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.DeviceDetails;
import org.fourthline.cling.model.meta.DeviceIdentity;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.ManufacturerDetails;
import org.fourthline.cling.model.meta.ModelDetails;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;
import org.fourthline.cling.model.types.UDN;

/**
 * Created by mkostiuk on 01/06/2017.
 */

public class OrientationDevice {

    /*Fonction permettant la création du device UPnP, on définit son type,
   * lui ajoute des détails et lui donne une classe qui contient le service
   * UPnP
   * */
    static LocalDevice createDevice(UDN udn)
            throws ValidationException, LocalServiceBindingException {

        DeviceType type =
                new UDADeviceType("AndroidDeviceOrientation", 1);

        DeviceDetails details =
                new DeviceDetails(
                        "Android Device Orientation",
                        new ManufacturerDetails("IRIT"),
                        new ModelDetails("AndroidController", "a UPnP Android based remote controller", "v1")
                );

        LocalService service =
                new AnnotationLocalServiceBinder().read(OrientationController.class);

        service.setManager(
                new DefaultServiceManager<>(service, OrientationController.class)
        );

        return new LocalDevice(
                new DeviceIdentity(udn),
                type,
                details,

                service
        );
    }

}
