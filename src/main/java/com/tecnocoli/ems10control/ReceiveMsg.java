/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import static com.tecnocoli.ems10control.App.logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief ReceiveMsg
 * @package com.tecnocoli.ems10control
 * @date 8/3/23 - 10:05
 */
public class ReceiveMsg {
    private String temp;
    private String bat;
    private String cel1;
    private String cel2;
    private EMSDevice emsDevice;

    public ReceiveMsg() {
        this.setBat("0");
        this.setTemp("0");
    }

    public String[] splitMesg(String s){
        String[] sMsg = s.split(",", 6);
//        for (String x : sMsg){
//            logger.info("split: "+x);
//        }
        return sMsg;
    }

    public Integer inMsg(String s, Vector<EMSDevice> vcEmsDevice, Integer connectWatchDog){
        String[] inMsgSplit = splitMesg(s);
//        for (String x : inMsgSplit){
//            logger.info("split: "+x);
//        }
//        logger.info(s);
        String add = inMsgSplit[0];
        String fun = inMsgSplit[1];
        Float inBat = 0.0F;
        Float inCel1 = 0.0F;
        Float inCel2 = 0.0F;
        Float inTemp = 0.0F;

//        for (EMSDevice dev : vcEmsDevice){
//            logger.info(dev.getAddressDevice());
//        }
//        logger.info("btAddress: "+inMsgSplit[0]);
        if (inMsgSplit[1].contains("te") ){
//            logger.info("Temp: "+inMsgSplit[4]);
            inTemp = Float.valueOf(inMsgSplit[4]);
            vcEmsDevice.elementAt(0).setTemperatureLevel(inTemp);
            this.setTemp(inMsgSplit[4]);
        }
        if (inMsgSplit[1].contains("ba") ){
//            logger.info("Bat: "+inMsgSplit[4]);
            inBat = Float.valueOf(inMsgSplit[4])/1000;
            vcEmsDevice.elementAt(0).setBatteryLevel(inBat);
            String strBat = String.format("%.2f", inBat);
            this.setBat(strBat);
        }
        if (inMsgSplit[1].contains("c1") ){
//            logger.info("Cel1: "+inMsgSplit[4]);
            inCel1 = Float.valueOf(inMsgSplit[4])/1000;
            vcEmsDevice.elementAt(0).setBatteryCel1Level(inCel1);
            String strCel1 = String.format("%.2f", inCel1);
            this.setCel1(strCel1);
        }
        if (inMsgSplit[1].contains("c2") ){
//            logger.info("Cel2: "+inMsgSplit[4]);
            inCel2 = Float.valueOf(inMsgSplit[4])/1000;
            vcEmsDevice.elementAt(0).setBatteryCel2Level(inCel2);
            String strCel2 = String.format("%.2f", inCel2);
            this.setCel2(strCel2);
        }
        if (inMsgSplit[1].contains("co") ){
//            logger.info("Con: "+inMsgSplit[4]);
            connectWatchDog = 0;
        }

        return connectWatchDog;

    }

    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getBat() {
        return bat;
    }
    public void setBat(String bat) {
        this.bat = bat;
    }
    public String getCel1() {
        return cel1;
    }
    public void setCel1(String cel1) {
        this.cel1 = cel1;
    }
    public String getCel2() {
        return cel2;
    }
    public void setCel2(String cel2) {
        this.cel2 = cel2;
    }
}

