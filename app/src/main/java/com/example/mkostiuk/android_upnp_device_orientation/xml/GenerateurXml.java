package com.example.mkostiuk.android_upnp_device_orientation.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by mkostiuk on 01/06/2017.
 */

public class GenerateurXml {

    public String getDocXml(String udn, String x, String y, String z) throws ParserConfigurationException, TransformerException {
        String namespace = "/";
        Document doc;
        DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = db.newDocumentBuilder();
        doc = builder.newDocument();
        Element root = doc.createElementNS(namespace, "OrientationDevice");
        doc.appendChild(root);

        Element u = doc.createElementNS(namespace, "UDN");
        root.appendChild(u);
        u.appendChild(doc.createTextNode(udn.toString()));

        Element c = doc.createElementNS(namespace, "Angle");
        root.appendChild(c);

        Element xNode = doc.createElementNS(namespace, "X");
        c.appendChild(xNode);
        xNode.appendChild(doc.createTextNode(x));

        Element yNode = doc.createElementNS(namespace, "Y");
        c.appendChild(yNode);
        yNode.appendChild(doc.createTextNode(y));

        Element zNode = doc.createElementNS(namespace, "Z");
        c.appendChild(zNode);
        zNode.appendChild(doc.createTextNode(z));


        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
        return writer.toString();
    }
}
