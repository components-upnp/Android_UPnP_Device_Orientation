package com.example.mkostiuk.android_upnp_device_orientation.upnp;

import org.fourthline.cling.binding.annotations.UpnpAction;
import org.fourthline.cling.binding.annotations.UpnpInputArgument;
import org.fourthline.cling.binding.annotations.UpnpService;
import org.fourthline.cling.binding.annotations.UpnpServiceId;
import org.fourthline.cling.binding.annotations.UpnpServiceType;
import org.fourthline.cling.binding.annotations.UpnpStateVariable;

import java.beans.PropertyChangeSupport;

/**
 * Created by mkostiuk on 01/06/2017.
 */

@UpnpService(
        serviceType = @UpnpServiceType(value = "OrientationService"),
        serviceId = @UpnpServiceId("OrientationService")
)
public class OrientationController {

    private final PropertyChangeSupport propertyChangeSupport;

    public OrientationController() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    @UpnpStateVariable(defaultValue = "")
    private String commande = "";

    @UpnpAction(name = "SendCommande")
    public void sendCommande(@UpnpInputArgument(name = "Commande") String c) {
        commande = c;
        getPropertyChangeSupport().firePropertyChange("Commande", "", commande);
    }
}
