package com.example.jonathan.bluetooth_project_iot.events;

import android.bluetooth.BluetoothDevice;


public class DiscoveredNewDeviceEvent {

    BluetoothDevice bluetoothDevice;
    boolean isfinish;

    public DiscoveredNewDeviceEvent(BluetoothDevice bluetoothDevice, boolean isfinish) {
        this.bluetoothDevice = bluetoothDevice;
        this.isfinish = isfinish;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public boolean isfinish() {
        return isfinish;
    }

    public void setIsfinish(boolean isfinish) {
        this.isfinish = isfinish;
    }
}
