package com.example.jonathan.bluetooth_project_iot.connectMain.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.jonathan.bluetooth_project_iot.IBasePresenter;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.ConnectActivity;
import com.example.jonathan.bluetooth_project_iot.events.DiscoveredNewDeviceEvent;
import com.example.jonathan.bluetooth_project_iot.utils.BluetoothTreatmentConnection;
import com.example.jonathan.bluetooth_project_iot.utils.BroadcastReceiverNewDevices;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

@EBean
public class FoundDevicesPresenter implements IBasePresenter {

    @RootContext
    Context context;

    ConnectActivity activity;
    private ArrayList<BluetoothDevice> devices;
    private BroadcastReceiverNewDevices broadcastReceiver;

    private int REQUEST_ENABLE_BT = 100;

    @AfterInject
    public void afterInject() {
        devices = new ArrayList<>();
        startReceiver();
    }

    @Override
    public void setInstance(Object object) {
        activity = (ConnectActivity) object;
    }

    public void checkBluetoothIsEnable() {

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private void startReceiver() {
        IntentFilter filter = new IntentFilter();
        broadcastReceiver = new BroadcastReceiverNewDevices();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(broadcastReceiver, filter);
    }

    public void startDiscovered() {
        devices.clear();
        activity.setListAdapter(devices,false);
        activity.showProgress();
        searchNewDevices();
    }

    public void registerEvent() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEvent() {
        EventBus.getDefault().unregister(this);
    }

    @Background
    public void searchNewDevices() {
        BluetoothAdapter.getDefaultAdapter().startDiscovery();
    }

    public boolean isBluetoothSearchNewDevice() {
        return BluetoothAdapter.getDefaultAdapter().isDiscovering();
    }

    @Subscribe
    public void onNewDeviceFound(DiscoveredNewDeviceEvent discoveredNewDeviceEvent) {
        if (!discoveredNewDeviceEvent.isfinish())
            devices.add(discoveredNewDeviceEvent.getBluetoothDevice());
        else {
            activity.setListAdapter(devices,true);
        }
    }

    public void deviceToPairedSelected(int position) {
        BluetoothDevice bluetoothDevice = devices.get(position);
        new BluetoothTreatmentConnection(bluetoothDevice).startPairingDevice();
    }

}
