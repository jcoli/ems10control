/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import static com.tecnocoli.ems10control.App.logger;
import static com.tecnocoli.ems10control.App.debug;
import com.tecnocoli.ems10control.util.Alerts;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.Vector;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer;

import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

import javax.bluetooth.*;

import com.tecnocoli.ems10control.EMSDeviceControl;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief PrimaryController
 * @package com.tecnocoli.ems10control
 * @date 8/3/23 - 10:03
 */
public class PrimaryController implements Initializable {

    BufferedReader in;
    PrintWriter out;
    String newMsg;
    ReceiveMsg receiveMsg = new ReceiveMsg();
    SPPClient sppClient = new SPPClient();
    DeviceConnection deviceConnection = new DeviceConnection(newMsg);
    Vector<EMSDevice> vcEmsDevice = new Vector<>(5);
    Integer countDevices = 0;
    Integer connectWatchDog = 0;
    Boolean btConnected = false;
    Timer tm = new Timer();

    @FXML
    private void toDiscon() throws IOException{
        try {
            if (btConnected){
                deviceConnection.Disconnect();
                lblConn.setText("Disconnected");
                String s = ("co,0,0,0,#");
                sendMsg(s);
            }
        }catch ( IOException e){
            logger.info("Discon " + e);
        }
    }
    @FXML
    private void toRun() throws IOException{
        try {
            if (btConnected){
                String s = ("ru,0,0,1,#");
                sendMsg(s);
                logger.info("toRun ");
            }else{
                String s = ("ru,0,0,0,#");
                sendMsg(s);
                logger.info("not Run " );
            }
        }catch ( IOException e){
            logger.info("toRun " + e);
        }
    }

    @FXML
    private void toConnectAdd() throws IOException {
        try {
            if (debug) {
                logger.info("teste1");
            }
            String add = txtAddress.getText();
            RemoteDevice rd;
            rd = deviceConnection.toConnectAdd(add);

            if (rd != null) {

                if (deviceConnection.sppClient.connectionURL == null) {
                    if (debug) {
                        logger.info("disconnected");
                        sendTxtAreaScan("Disconnect");
                    }
                    lblConn.setText("Disconnected");
                } else {
                    if (debug) {
                        logger.info("connect");
                        logger.info(deviceConnection.sppClient.connectionURL);
                        sendTxtAreaScan("Connected");
                        sendTxtAreaScan(deviceConnection.sppClient.connectionURL);
                    }
                    btConnected = true;
                    lblConn.setText("Connected");
                    logger.info("Test");
                    EMSDevice emsDevice1 = new EMSDevice(rd, deviceConnection.sppClient.partnerName, rd.getBluetoothAddress(), "EMS");
                    vcEmsDevice.add(emsDevice1);
                    vcEmsDevice.elementAt(0).setConnectedDevice(true);
                    countDevices++;
                    this.in = deviceConnection.in;
                    this.out = deviceConnection.out;
                    (new streamPoller()).start();
                    tm.scheduleAtFixedRate(new subtimer(), 0,2000l);
                    String s = ("co,0,0,1,#");
                    sendMsg(s);
                }
            }else{
                logger.info("Have no devices");
                sendTxtAreaScan("Have no devices");
            }
        } catch (Exception e) {
            logger.info("catch " + e);
        }
    }

    @FXML
    private void btnSendMsg() throws IOException {
        if (debug) {
            logger.info("send msg");
        }
        String s = txtSend.getText();
        sendMsg(s);
    }

    public void sendMsg(String s) throws IOException {
        s = s + "\r\n";
//        if (debug) {
//            logger.info("send msg: " + s);
//        }
        out.write(s);
        out.flush();
    }

    public void recMsg(String s) {
        if (debug) {
            logger.info("RemoteDevice: " + deviceConnection.sppClient.partnerName + " - " + s);
        }
        int ret = receiveMsg.inMsg(s, vcEmsDevice, connectWatchDog);
        connectWatchDog = ret;
        logger.info("receive " + s);
    }

    @FXML
    private void scanDevices() throws IOException {
        try {
            deviceConnection.scanDevices();
        } catch (Exception e) {
//            Alerts.showAlert("Error", "Parse error", e.getMessage(), AlertType.ERROR);
            logger.info("scanDevices: " + e);
        }
    }

    public void sendTxtAreaScan(String s) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        txtAreaScan.appendText(dtf.format(now) + ": " + s + "\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Constraints.setTextFieldNull(txtSend);
    }

    class streamPoller extends Thread {
        public void run() {
            boolean isRun = true;
            while (isRun) {
                try {
//                    logger.info("catch poller ");
                    if (in != null) {
                        String s = in.readLine();
//                        logger.info("catch poller 2");
                        if (s != null) {
                            Platform.runLater(() -> recMsg(s));
                        }
                        else {
                            isRun = false;
                        }
                    }
                } catch (IOException e) {
                    logger.info("catch poller " + e);
                }
            }
        }
    }

    private class subtimer extends TimerTask {
        //run method
        @Override
        public void run() {
            Platform.runLater(() -> {
                try {
                    {
                        if (btConnected) {
                            connectWatchDog++;
                            String s = ("co," + "0,0,1,#");
                            sendMsg(s);
                            logger.info("warchdog: "+connectWatchDog);
                            if (!vcEmsDevice.isEmpty()) {
//                                lblName.setText(vcEmsDevice.elementAt(0).getFriendlyName());
                                lblAdd.setText(vcEmsDevice.elementAt(0).getAddressDevice());
                                lblTemp.setText(String.format("%.2f", vcEmsDevice.elementAt(0).getTemperatureLevel()) + "°C");
                                lblBat.setText(String.format("%.2f", vcEmsDevice.elementAt(0).getBatteryLevel()) + "V");
                                lblCel1.setText(String.format("%.2f", vcEmsDevice.elementAt(0).getBatteryCel1Level()) + "V");
                                lblCel2.setText(String.format("%.2f", vcEmsDevice.elementAt(0).getBatteryCel2Level()) + "V");
                            }
                            if (vcEmsDevice.elementAt(0).getBatteryLevel() != null) {
                                if (vcEmsDevice.elementAt(0).getBatteryLevel() < 6.5) {
                                    lblBat.setTextFill(Color.ORANGERED);
                                } else {
                                    lblBat.setTextFill(Color.BLACK);
                                }
                            }
                            if (connectWatchDog>=30){
                                btConnected = false;
                                vcEmsDevice.elementAt(0).setConnectedDevice(false);
                                lblConn.setText("Disconnected");
                                RemoteDevice rd = vcEmsDevice.elementAt(0).getRmDevice();

                            }
                        }
                    }
                } catch (IOException e) {
                    logger.info("timer 6: " + e);
                }
            });
        }
    }

    @FXML
    private TextField txtSend;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextArea txtAreaScan;
    @FXML
    private Label lblConn;
    @FXML
    private Label lblTemp;
    @FXML
    private Label lblBat;
    @FXML
    private Label lblCel1;
    @FXML
    private Label lblCel2;
    @FXML
    private Label lblAdd;
    @FXML
    private Label lblName;
    @FXML
    private Button btnRun;
    @FXML
    private Button btnDiscon;
    @FXML
    private CheckBox chkCh_1;
    @FXML
    private CheckBox chkCh_2;
    @FXML
    private CheckBox chkCh_3;
    @FXML
    private CheckBox chkCh_4;
    @FXML
    private CheckBox chkCh_5;
    @FXML
    private CheckBox chkCh_6;
    @FXML
    private CheckBox chkCh_7;
    @FXML
    private CheckBox chkCh_8;
    @FXML
    private Button btnPlusCh_1;
    @FXML
    private Button btnPlusCh_2;
    @FXML
    private Button btnPlusCh_3;
    @FXML
    private Button btnPlusCh_4;
    @FXML
    private Button btnPlusCh_5;
    @FXML
    private Button btnPlusCh_6;
    @FXML
    private Button btnPlusCh_7;
    @FXML
    private Button btnPlusCh_8;
    @FXML
    private Button btnMinusCh_1;
    @FXML
    private Button btnMinusCh_2;
    @FXML
    private Button btnMinusCh_3;
    @FXML
    private Button btnMinusCh_4;
    @FXML
    private Button btnMinusCh_5;
    @FXML
    private Button btnMinusCh_6;
    @FXML
    private Button btnMinusCh_7;
    @FXML
    private Button btnMinusCh_8;
    @FXML
    private Label lblIntCh_1;
    @FXML
    private Label lblIntCh_2;
    @FXML
    private Label lblIntCh_3;
    @FXML
    private Label lblIntCh_4;
    @FXML
    private Label lblIntCh_5;
    @FXML
    private Label lblIntCh_6;
    @FXML
    private Label lblIntCh_7;
    @FXML
    private Label lblIntCh_8;
    @FXML
    private ImageView imgEnCh_1;
    @FXML
    private ImageView imgEnCh_2;
    @FXML
    private ImageView imgEnCh_3;
    @FXML
    private ImageView imgEnCh_4;
    @FXML
    private ImageView imgEnCh_5;
    @FXML
    private ImageView imgEnCh_6;
    @FXML
    private ImageView imgEnCh_7;
    @FXML
    private ImageView imgEnCh_8;

}