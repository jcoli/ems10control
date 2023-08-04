/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief RemoteDeviceInfo
 * @package com.tecnocoli.ems10control
 * @date 8/3/23 - 10:06
 */
public class RemoteDeviceInfo {
    public final SimpleStringProperty deviceName = new SimpleStringProperty("");
    public final SimpleStringProperty deviceAddress = new SimpleStringProperty("");
    public String getDeviceAddress() {
        return deviceAddress.get();
    }
    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress.set(deviceAddress);
    }
    public String getDeviceName() {
        return deviceName.get();
    }
    public void setDeviceName(String deviceName) {
        this.deviceName.set(deviceName);
    }
    public RemoteDeviceInfo(String name,String address){
        setDeviceName(name);
        setDeviceAddress(address);
    }
    public RemoteDeviceInfo(){
        this("","");
    }
}
