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
        logger.info(s);
        String add = inMsgSplit[0];
        String fun = inMsgSplit[1];
        Float inBat = 0.0F;
        Float inTemp = 0.0F;

        for (EMSDevice dev : vcEmsDevice){
            logger.info(dev.getAddressDevice());
        }

        logger.info("btAddress: "+inMsgSplit[0]);
        if (inMsgSplit[1].contains("te") ){
            logger.info("Temp: "+inMsgSplit[4]);
            inTemp = Float.valueOf(inMsgSplit[4]);
            vcEmsDevice.elementAt(0).setTemperatureLevel(inTemp);
            this.setTemp(inMsgSplit[4]);
        }
        if (inMsgSplit[1].contains("ba") ){
            logger.info("Bat: "+inMsgSplit[4]);
            inBat = Float.valueOf(inMsgSplit[4])/1000;
            vcEmsDevice.elementAt(0).setBatteryLevel(inBat);
            String strBat = String.format("%.2f", inBat);
            this.setBat(strBat);

        }
        if (inMsgSplit[1].contains("co") ){
            logger.info("Con: "+inMsgSplit[4]);
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
}

