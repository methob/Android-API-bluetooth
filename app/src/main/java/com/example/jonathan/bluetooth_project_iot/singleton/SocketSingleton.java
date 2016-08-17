package com.example.jonathan.bluetooth_project_iot.singleton;

import android.bluetooth.BluetoothSocket;


public class SocketSingleton {

    private BluetoothSocket mmSocket;

    private static SocketSingleton ourInstance = new SocketSingleton();

    public static SocketSingleton getInstance() {
        return ourInstance;
    }

    private SocketSingleton() {
    }

    public void setSocketInstance(BluetoothSocket mmSocket) {
        this.mmSocket = mmSocket;
    }
}
