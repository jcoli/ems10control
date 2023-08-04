/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import java.io.Serializable;
import java.util.Objects;
import javax.bluetooth.RemoteDevice;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief EMSDevice
 * @package com.tecnocoli.ems10control
 * @date 8/3/23 - 10:00
 */
public class EMSDevice implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private RemoteDevice rmDevice;
    private BufferedReader in;
    private PrintWriter out;
    private String partnerName;
    private String addressDevice;
    private String friendlyName;
    private Boolean connectedDevice;
    private Float batteryLevel;
    private Float temperatureLevel;
    private String typeLastUsed;
    private Boolean connected;
    private LocalDateTime lastDateUsed;
    private LocalDateTime strongLastDateUsed;
    private ReceiveMsg receiveMsg;

    public EMSDevice() {
    }

    public EMSDevice(String AddressDevice) {
        this.addressDevice = AddressDevice;
    }

    public EMSDevice(Integer id, RemoteDevice rmDevice, String partnerName, String Address, String friendlyName) {
        this.id = id;
        this.rmDevice = rmDevice;
        this.partnerName = partnerName;
        this.addressDevice = Address;
        this.friendlyName = friendlyName;
    }

    public EMSDevice(RemoteDevice rmDevice, String partnerName, String AddressDevice, String friendlyName) {
        this.rmDevice = rmDevice;
        this.partnerName = partnerName;
        this.addressDevice = AddressDevice;
        this.friendlyName = friendlyName;
    }
    public RemoteDevice getRmDevice() {
        return rmDevice;
    }
    public void setRmDevice(RemoteDevice rmDevice) {
        this.rmDevice = rmDevice;
    }
    public String getPartnerName() {
        return partnerName;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    public String getAddressDevice() {
        return addressDevice;
    }
    public void setAddressDevice(String Address) {
        this.addressDevice = Address;
    }
    public String getFriendlyName() {
        return friendlyName;
    }
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }
    public LocalDateTime getLastDateUsed() {
        return lastDateUsed;
    }
    public void setLastDateUsed(LocalDateTime lastDateUsed) {
        this.lastDateUsed = lastDateUsed;
    }
    public String getTypeLastUsed() {
        return typeLastUsed;
    }
    public void setTypeLastUsed(String typeLastUsed) {
        this.typeLastUsed = typeLastUsed;
    }
    public LocalDateTime getStrongLastDateUsed() {
        return strongLastDateUsed;
    }
    public void setStrongLastDateUsed(LocalDateTime StrongLastDateUsed) {
        this.strongLastDateUsed = StrongLastDateUsed;
    }
    public Boolean getConnectedDevice() {
        return connectedDevice;
    }
    public void setConnectedDevice(Boolean connectedDevice) {
        this.connectedDevice = connectedDevice;
    }
    public Float getBatteryLevel() {
        return batteryLevel;
    }
    public void setBatteryLevel(Float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
    public Float getTemperatureLevel() {
        return temperatureLevel;
    }
    public void setTemperatureLevel(Float temperatureLevel) {
        this.temperatureLevel = temperatureLevel;
    }
    public Boolean getConnected() {
        return connected;
    }
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }
    public BufferedReader getIn() {
        return in;
    }
    public void setIn(BufferedReader in) {
        this.in = in;
    }
    public PrintWriter getOut() {
        return out;
    }
    public void setOut(PrintWriter out) {
        this.out = out;
    }
    @Override
    public String toString() {
        return "EMSDevice{" + "rmDevice=" + rmDevice + ", partnerName=" + partnerName + ", Address=" + addressDevice + ", friendlyName=" + friendlyName + '}';
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.addressDevice);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EMSDevice other = (EMSDevice) obj;
        return Objects.equals(this.addressDevice, other.addressDevice);
    }

}

