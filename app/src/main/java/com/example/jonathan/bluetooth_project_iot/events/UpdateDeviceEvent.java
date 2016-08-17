package com.example.jonathan.bluetooth_project_iot.events;


import android.bluetooth.BluetoothDevice;

public class UpdateDeviceEvent {

    boolean isRemove;
    BluetoothDevice device;

    public UpdateDeviceEvent(BluetoothDevice device) {
        this.device = device;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public void setRemove(boolean remove) {
        isRemove = remove;
    }

    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }
}
