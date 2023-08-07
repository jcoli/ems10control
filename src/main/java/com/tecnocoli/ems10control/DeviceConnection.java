/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import static com.tecnocoli.ems10control.App.logger;
import static com.tecnocoli.ems10control.App.debug;
import com.tecnocoli.ems10control.util.Alerts;
import com.tecnocoli.ems10control.util.Constraints;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.Vector;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer;

import javafx.application.Platform;

import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

import javax.bluetooth.*;


/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief DeviceConnection
 * @package com.tecnocoli.ems10control
 * @date 8/3/23 - 09:56
 */
public class DeviceConnection {
    BufferedReader in;
    PrintWriter out;
    RemoteDevice emsDevice;
    String myName;
    String partnerName;
    String newMsg;
    ReceiveMsg receiveMsg = new ReceiveMsg();
    SPPClient sppClient = new SPPClient();
    RemoteDevice rd;

    public DeviceConnection() {
    }

    public DeviceConnection(String newMsg) {
        this.newMsg = newMsg;
    }

    public RemoteDevice toConnectAdd(String add) throws IOException
    {
        try {

            if (debug) {
                logger.info("teste1");
            }
            this.scanDevices();
            Vector<RemoteDevice> vc = sppClient.getVecDevices();
            int deviceCount = vc.size();
            int device_int = 100;
            for (int i = 0; i < deviceCount; i++) {
                RemoteDevice rd1 = (RemoteDevice) vc.elementAt(i);
                if (rd1.getBluetoothAddress().equals(add)) {
                    device_int = i;
                    if (debug) {
                        logger.info("Device: " + rd1.getBluetoothAddress() + " - " + "rd1.getFriendlyName(true)");
                        logger.info("Device: " + add);
                        logger.info("Device: " + i);
                    }
                }
            }
            if (device_int != 100) {
                sppClient.connect(device_int);
                if (sppClient.connectionURL == null) {
                    if (debug) {
                        logger.info("disconnected");
                    }
                } else {
                    if (debug) {
                        logger.info("connect");
                    }
                    in = sppClient.in;
                    out = sppClient.out;
                    partnerName = sppClient.partnerName;
                    this.rd = (RemoteDevice) vc.elementAt(device_int);
                    return this.rd;
                }
            }
        } catch (Exception e) {
            logger.info("catch " + e);
        }
        return this.rd;
    }
    public void Disconnect() throws IOException {

    }

    public void scanDevices() throws IOException {
        try {
            LocalDevice localdeviceLocal = sppClient.getLocalDevice_1();
            if (!localdeviceLocal.setDiscoverable(DiscoveryAgent.GIAC)) {
                logger.info("DeviceLocal can't setDiscoverable " + "\n");
            } else {
                logger.info("DeviceLocal SetDiscoverable ");
            }
            sppClient.startDiscovery();
            try {
                synchronized (sppClient.lock) {
                    if (debug) {
                        logger.info("waiting discovery: ");
                    }
                    sppClient.lock.wait();
                }
            } catch (InterruptedException e) {
                logger.info("catch " + e);
            }
            logger.info("aqui ");
            Vector<RemoteDevice> vc = sppClient.getVecDevices();
            int deviceCount = vc.size();
            logger.info("aqui dev "+ deviceCount);
            if (deviceCount > 1) {
                logger.info("aqui 1");
//                for (int i = 0; i < deviceCount; i++) {
//                    RemoteDevice rd = (RemoteDevice) vc.elementAt(i);
//                    if (rd.getFriendlyName(true) != null) {
//                        if (debug) {
//                            logger.info("Device: " + rd.getBluetoothAddress() + " - " +" rd.getFriendlyName(true)");
//                        }
//                        if (!(rd.getFriendlyName(true).contains("EMS"))) {
//                            if (debug) {
//                                logger.info("Device: " + rd.getBluetoothAddress() + " - " + "rd.getFriendlyName(true)");
//                                logger.info("Remove");
//                            }
//                            vc.remove(i);
//                            i--;
//                        }
//                    } else {
//                        vc.remove(i);
//                        i--;
//                    }
//                }
            } else {
//                logger.info("aqui 2");
//                RemoteDevice rd = (RemoteDevice) vc.elementAt(0);
//                logger.info("aqui 3");
//
//                if (rd.getFriendlyName(true) != null) {
//                    logger.info("Device: " + rd.getBluetoothAddress() + " - " + rd.getFriendlyName(true));
//                    if (!(rd.getFriendlyName(true).contains("EMS"))) {
//                        if (debug) {
//                            logger.info("Device: " + rd.getBluetoothAddress() + " - " + rd.getFriendlyName(true));
//                            logger.info("Remove 1");
//                        }
//                        vc.remove(0);
//                    }
//                } else {
//                    vc.remove(0);
//                }
            }
            deviceCount = vc.size();
            if (deviceCount <= 0) {
                logger.info("No Device: ");
            } else {
                for (int i = 0; i < deviceCount; i++) {
                    RemoteDevice rd = (RemoteDevice) vc.elementAt(i);
                    if (debug) {
                        if (rd.isAuthenticated()) {
                            logger.info("Device: is authenticated");
                        }
                        logger.info("Device: " + rd.getBluetoothAddress() + " - " + "rd.getFriendlyName(true)"+ " - " + rd.toString());
                    }
                }
            }
        } catch (Exception e) {
//            Alerts.showAlert("Error", "Parse error", e.getMessage(), AlertType.ERROR);
            logger.info("scanDevices: " + e);
        }
    }

}
