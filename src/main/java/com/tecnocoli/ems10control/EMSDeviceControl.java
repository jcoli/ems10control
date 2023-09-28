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
import java.util.Vector;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief EMSDeviceControl
 * @package com.tecnocoli.ems10control
 * @date 8/25/23 - 15:39
 */
public class EMSDeviceControl {

    Vector<EMSDevice> vcEmsDevice = new Vector<>(5);
    private EMSDevice emsDevice = new EMSDevice();

    public EMSDeviceControl() {
    }

    public EMSDevice AddEMSDevice(EMSDevice ems){
        return ems;
    }

    public EMSDevice AddEMSDevice(RemoteDevice rmDevice, String partnerName, String AddressDevice, String friendlyName){
        EMSDevice ems = new EMSDevice(rmDevice, partnerName, AddressDevice, friendlyName);
        vcEmsDevice.add(ems);
        return ems;
    }
    public void enableEMSDeviceChannel(int id_ch, int ems_device, boolean ch_actived){
        logger.info("ENABLEeEMSDeviceChannel " + id_ch + " - "+ ems_device + " - "+ ch_actived);
        List<Channel> channels = vcEmsDevice.elementAt(ems_device).getChannels();
        channels.get(id_ch).setActived(ch_actived);
    }
    public void increaseEMSDeviceChannel(int id_ch, int ems_device){
        logger.info("increaseEMSDeviceChannel " + id_ch + " - "+ ems_device);
        List<Channel> channels = vcEmsDevice.elementAt(ems_device).getChannels();
        int intensity = channels.get(id_ch).getIntensity();
        intensity++;
        if (intensity>25){
            intensity = 25;
        }
        channels.get(id_ch).setIntensity(intensity);
        vcEmsDevice.elementAt(ems_device).setChannels(channels);

        logger.info("increaseEMSDeviceChannel " + id_ch);

    }
    public void decreaseEMSDeviceChannel(int id_ch, int ems_device){
        logger.info("decreaseEMSDeviceChannel " + id_ch + " - "+ ems_device);

        List<Channel> channels = vcEmsDevice.elementAt(ems_device).getChannels();
        int intensity = channels.get(id_ch).getIntensity();
        intensity--;
        if (intensity<0){
            intensity = 0;
        }
        channels.get(id_ch).setIntensity(intensity);
        vcEmsDevice.elementAt(ems_device).setChannels(channels);

        logger.info("decreaseEMSDeviceChannel " + id_ch);

    }
}
