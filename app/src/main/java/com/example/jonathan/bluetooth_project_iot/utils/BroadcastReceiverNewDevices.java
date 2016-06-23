package com.example.jonathan.bluetooth_project_iot.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jonathan.bluetooth_project_iot.events.DiscoveredNewDeviceEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Jonathan on 22/06/2016.
 */
public class BroadcastReceiverNewDevices extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            EventBus.getDefault().post(new DiscoveredNewDeviceEvent(device,false));
        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            EventBus.getDefault().post(new DiscoveredNewDeviceEvent(null,true));
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        }
    }
}
