package com.example.jonathan.bluetooth_project_iot.connectMain.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jonathan.bluetooth_project_iot.events.DiscoveredNewDeviceEvent;
import com.example.jonathan.bluetooth_project_iot.events.UpdateDeviceEvent;

import org.greenrobot.eventbus.EventBus;


public class BroadcastReceiverNewDevices extends BroadcastReceiver {

    private final String ACL_CONNECTED = BluetoothDevice.ACTION_ACL_CONNECTED;
    private final String ACL_DISCONNECTED = BluetoothDevice.ACTION_ACL_DISCONNECTED;


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            EventBus.getDefault().post(new DiscoveredNewDeviceEvent(device, false));
        } else if (ACL_CONNECTED.equals(action) || ACL_DISCONNECTED.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            EventBus.getDefault().post(new UpdateDeviceEvent(device));
        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            EventBus.getDefault().post(new DiscoveredNewDeviceEvent(null, true));
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        }
    }
}
