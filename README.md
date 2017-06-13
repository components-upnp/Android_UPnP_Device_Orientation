# Android_UPnP_Device_Orientation
Composant qui permet d'envoyer par évènement l'inclinaison du smartphone

<strong>Description :</strong>

Ce composant se présente sous la forme d'un service Android qui récupère l'inclinaison du smartphone sur trois axes
X, Y et Z afin de la transmettre par événement UPnP.

<strong>Lancement de l'application : </strong>

L'application ne peut pas communiquer via UPnP lorsque lancée dans un émulateur, elle doit être lancée sur un terminal physique et appartenir au même réseau local que les autres composants.

Il faut donc installer l'apk sur le terminal, vérifier d'avoir autorisé les sources non vérifiées.

Après démarrage de l'application, il est possible d'ajouter le composant sur wcomp en suivant la méthode décrite sur le wiki oppocampus.

<strong>Spécification UPnP : </strong>

Ce composant offre le service OrientationService dont voici la description :

  1. sendCommande(String commande) : permet d'envoyer un fichier XML contenant l'inclinaison de l'appareil sur les axes X, Y et Z via
  un événement UPnP Commande.
  
Voici le schéma correspondant à ce composant :

![alt tag](https://github.com/components-upnp/Android_UPnP_Device_Orientation/blob/master/DeviceOrientation.png)

<strong>Maitenance : </strong>

Le projet de l'application est un projet gradle.
  

