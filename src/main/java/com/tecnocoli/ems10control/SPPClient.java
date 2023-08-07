/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import static com.tecnocoli.ems10control.App.debug;
import static com.tecnocoli.ems10control.App.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import com.tecnocoli.ems10control.util.Alerts;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
//import javafx.event.

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;


/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief SPPClient
 * @package com.tecnocoli.ems10control
 * @date 8/3/23 - 10:07
 */
public class SPPClient implements DiscoveryListener{

    public static Object lock = new Object();
    private Vector<RemoteDevice> vecDevices = new Vector();
    public static String connectionURL = null;
    private LocalDevice localDevice;
    private ActionEvent onDeviceDiscovery;
    private ActionEvent onConnectionSuccessful;
    private ActionEvent onConnectionFailed;
    private DiscoveryAgent agent;
    String partnerName;
    BufferedReader in;
    PrintWriter out;
    boolean isOK = false;

    public LocalDevice getLocalDevice_1() {
        if (debug) {
            logger.info("Address: " + localDevice.getBluetoothAddress());
            logger.info("Name: " + "localDevice.getFriendlyName()");
        }
        return localDevice;
    }
    public DiscoveryAgent getAgent() {
        return agent;
    }
    public Vector<RemoteDevice> getVecDevices() {
        return vecDevices;
    }
    public static Object getLock() {
        return lock;
    }
    public SPPClient() {
        try {
            localDevice = LocalDevice.getLocalDevice();
            if (debug) {
                logger.info("Address: " + localDevice.getBluetoothAddress());
                logger.info("Name: " + "localDevice.getFriendlyName()");
            }
            agent = localDevice.getDiscoveryAgent();
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }
    }
    public void setOnDeviceDiscovery(ActionEvent onDeviceDiscovery) {
        this.onDeviceDiscovery = onDeviceDiscovery;
    }
    public void setOnConnectionFailed(ActionEvent onConnectionFailed) {
        this.onConnectionFailed = onConnectionFailed;
    }
    public void setOnConnectionSuccessful() {
        this.onConnectionSuccessful = onConnectionSuccessful;
    }
    public void startDiscovery() {
        logger.info("Starting device inquiry…");
        try {
            boolean complete = false;
            complete = agent.startInquiry(DiscoveryAgent.GIAC, this);
            while (!complete) {
                // wait until discovery completes before continuing
            }
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<RemoteDeviceInfo> getDeviceInfos() {
        ArrayList<RemoteDeviceInfo> res = new ArrayList<>();
        for (RemoteDevice rd : vecDevices) {
            try {
                RemoteDeviceInfo rdi = new RemoteDeviceInfo("rd.getFriendlyName(true)", rd.getBluetoothAddress());
                res.add(rdi);
                logger.info("Device: " + rd.getBluetoothAddress() + " - " + "rd.getFriendlyName(true)");
                logger.info("rdi....");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    public void connect(int index) {
        try {
            // check for spp service
            RemoteDevice remoteDevice = vecDevices.elementAt(index);
            if (debug) {
                logger.info("connecting...");
                logger.info("remote device: " + remoteDevice.getBluetoothAddress() + " - " +"remoteDevice.getFriendlyName(true)");
            }
            partnerName = "EMS";
            UUID[] uuidSet = new UUID[1];
            uuidSet[0] = new UUID("1101", true);
            if (debug) {
                logger.info("Searching for service...");
            }
            agent.searchServices(null, uuidSet, remoteDevice, this);
            synchronized (lock) {
                lock.wait();
            }
            if (connectionURL == null) {
                Alerts.showAlert("Error", "Erro: ", "Device does not support Simple SPP Service.", Alert.AlertType.ERROR);
                logger.info("Device does not support Simple SPP Service.");
            }else{
                logger.info("Connected");
            }
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        // add the device to the vector
        if (!vecDevices.contains(btDevice)) {
            vecDevices.addElement(btDevice);
        }
    }
    @Override
    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        if (debug) {
            logger.info("service discovered...");

        }
        if (servRecord != null && servRecord.length > 0) {
            connectionURL = servRecord[0].getConnectionURL(0, false);
        }
        isOK = true;
        try {
            StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);
            // send string
            OutputStream outStream = streamConnection.openOutputStream();
            out = new PrintWriter(new OutputStreamWriter(outStream));
            // read response
            InputStream inStream = streamConnection.openInputStream();
            in = new BufferedReader(new InputStreamReader(inStream));
            if (onConnectionSuccessful != null) {
//                onConnectionSuccessful.
//                .actionPerformed(new ActionEvent(this,ActionEvent.ANY.getClass(),""));
                if (debug) {
                    logger.info("Connection OK");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void serviceSearchCompleted(int transID, int respCode) {
        if (!isOK) {
            if (onConnectionFailed != null) {
                if (debug) {
                    logger.info("Connection Fail");
                }
            }
        }else{
            synchronized (lock) {
                lock.notify();
            }
        }
    }
    @Override
    public void inquiryCompleted(int discType) {
        logger.info("Complete device inquiry…");
        synchronized (lock) {
            lock.notify();
        }
        if (onDeviceDiscovery != null) {
            if (debug) {
                logger.info("Device Inquiry Completed. ");
            }

        }
    }
}
