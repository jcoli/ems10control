/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import static com.tecnocoli.ems10control.App.logger;

import javax.bluetooth.RemoteDevice;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief EMSDeviceControl
 * @package com.tecnocoli.ems10control
 * @date 8/25/23 - 15:39
 */
public class EMSDeviceControl {

    private List<EMSDevice> emsDevices = new ArrayList<>();
    private EMSDevice emsDevice = new EMSDevice();

    public EMSDeviceControl() {
    }

    public EMSDevice AddEMSDevice(EMSDevice ems){
        return ems;
    }

    public EMSDevice AddEMSDevice(RemoteDevice rmDevice, String partnerName, String AddressDevice, String friendlyName){
        EMSDevice ems = new EMSDevice(rmDevice, partnerName, AddressDevice, friendlyName);
        return ems;
    }
    public void enableEMSDeviceChannel(String id){
        logger.info("enableEMSDeviceChannel " + id);
    }
    public void increaseEMSDeviceChannel(String id){
        logger.info("increaseEMSDeviceChannel " + id);
    }
    public void decreaseEMSDeviceChannel(String id){
        logger.info("decreaseEMSDeviceChannel " + id);
    }
}
