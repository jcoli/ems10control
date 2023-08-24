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
    private Float batteryCel1Level;
    private Float batteryCel2Level;
    private Float temperatureLevel;
    private String typeLastUsed;
    private Boolean connected;
    private LocalDateTime lastDateUsed;
    private LocalDateTime strongLastDateUsed;
    private ReceiveMsg receiveMsg;
    private Integer channel1;
    private Integer channel2;
    private Integer channel3;
    private Integer channel4;
    private Integer channel5;
    private Integer channel6;
    private Integer channel7;

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
    public Float getBatteryCel1Level() { return batteryCel1Level; }
    public void setBatteryCel1Level(Float batteryCel1Level) { this.batteryCel1Level = batteryCel1Level;}
    public Float getBatteryCel2Level() { return batteryCel2Level;}

    public void setBatteryCel2Level(Float batteryCel2Level) { this.batteryCel2Level = batteryCel2Level; }

    public ReceiveMsg getReceiveMsg() { return receiveMsg; }

    public void setReceiveMsg(ReceiveMsg receiveMsg) { this.receiveMsg = receiveMsg; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannel1() {
        return channel1;
    }

    public void setChannel1(Integer channel1) {
        this.channel1 = channel1;
    }

    public Integer getChannel2() {
        return channel2;
    }

    public void setChannel2(Integer channel2) {
        this.channel2 = channel2;
    }

    public Integer getChannel3() {
        return channel3;
    }

    public void setChannel3(Integer channel3) {
        this.channel3 = channel3;
    }

    public Integer getChannel4() {
        return channel4;
    }

    public void setChannel4(Integer channel4) {
        this.channel4 = channel4;
    }

    public Integer getChannel5() {
        return channel5;
    }

    public void setChannel5(Integer channel5) {
        this.channel5 = channel5;
    }

    public Integer getChannel6() {
        return channel6;
    }

    public void setChannel6(Integer channel6) {
        this.channel6 = channel6;
    }

    public Integer getChannel7() {
        return channel7;
    }

    public void setChannel7(Integer channel7) {
        this.channel7 = channel7;
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

