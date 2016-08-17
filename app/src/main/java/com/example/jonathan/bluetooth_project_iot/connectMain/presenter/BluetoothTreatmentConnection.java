package com.example.jonathan.bluetooth_project_iot.connectMain.presenter;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;


public class BluetoothTreatmentConnection {

    private BluetoothDevice bluetoothDevice;
    private static final UUID MY_UUID = UUID.fromString("0000110E-0000-1000-8000-00805F9B34FB");

    public BluetoothTreatmentConnection(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public BluetoothSocket startPairingDevice() {
        try {
            boolean isPair = pairDevice(bluetoothDevice);

            if (isPair) {
                return connectDevice(bluetoothDevice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private BluetoothSocket connectDevice(BluetoothDevice device) throws IOException {
        BluetoothSocket mmSocket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
        mmSocket.connect();

        if (mmSocket.isConnected())
            return mmSocket;

        return null;
    }

    private boolean pairDevice(BluetoothDevice device) throws Exception {

        Class class1 = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = class1.getMethod("createBond");
        return (Boolean) createBondMethod.invoke(device);
    }
}
