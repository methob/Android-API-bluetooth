package com.example.jonathan.bluetooth_project_iot.utils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.lang.reflect.Method;


public class BluetoothTreatmentConnection {

    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket mmSocket;

    public BluetoothTreatmentConnection(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public void startPairingDevice() {
        BluetoothSocket tmp = null;
        pairDevice(bluetoothDevice);
    }

    private void pairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
