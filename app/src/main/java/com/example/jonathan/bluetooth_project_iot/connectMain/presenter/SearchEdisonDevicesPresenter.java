package com.example.jonathan.bluetooth_project_iot.connectMain.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;

import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.SearchEdisonDevicesFragment;
import com.example.jonathan.bluetooth_project_iot.events.DiscoveredNewDeviceEvent;
import com.example.jonathan.bluetooth_project_iot.events.UpdateDeviceEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class SearchEdisonDevicesPresenter {

    SearchEdisonDevicesFragment fragment;

    private ArrayList<BluetoothDevice> devices;
    private BroadcastReceiverNewDevices broadcastReceiver;

    public SearchEdisonDevicesPresenter(Fragment fragment) {
        this.fragment = (SearchEdisonDevicesFragment) fragment;
        devices = new ArrayList<>();
        startReceiver();
        startDiscovered();
    }

//    public void checkAndStartOrderToStartBluetooth() {
//
//        if (!isBluetoothEnable()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            activity.startActivityForResult(enableBtIntent, activity.REQUEST_ENABLE_BT);
//        } else {
//            startDiscovered();
//        }
//    }
//
//    public boolean isBluetoothEnable() {
//        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        return mBluetoothAdapter.isEnabled();
//    }
//
//
    private void startReceiver() {
        IntentFilter filter = new IntentFilter();
        broadcastReceiver = new BroadcastReceiverNewDevices();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        fragment.getActivity().registerReceiver(broadcastReceiver, filter);
    }
//
    public void startDiscovered() {

        fragment.hideTextNotFoundDevice();
        devices.clear();
        fragment.setListAdapter(devices, false);
        fragment.showProgress();
        searchNewDevices();
    }
//
    public void registerEvent() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEvent() {
        EventBus.getDefault().unregister(this);
    }

    public void searchNewDevices() {
        BluetoothAdapter.getDefaultAdapter().startDiscovery();
    }
//
//    public boolean isBluetoothSearchNewDevice() {
//        return BluetoothAdapter.getDefaultAdapter().isDiscovering();
//    }
//
    @Subscribe
    public void onNewDeviceFound(DiscoveredNewDeviceEvent discoveredNewDeviceEvent) {
        if (!discoveredNewDeviceEvent.isfinish())
            devices.add(discoveredNewDeviceEvent.getBluetoothDevice());
        else {
            fragment.setListAdapter(devices, true);
        }
    }

    @Subscribe
    public void onDeviceUpdate(UpdateDeviceEvent updateDeviceEvent) {

        for (BluetoothDevice device : devices) {
            if (device.getAddress().equals(updateDeviceEvent.getDevice().getAddress())) {
                devices.remove(device);
                devices.add(0, updateDeviceEvent.getDevice());
                fragment.notifyChangeInAdapter(devices);
            }
        }
    }
//
//    public void deviceToPairedSelected(int position) {
//
//        BluetoothDevice bluetoothDevice = devices.get(position);
//        BluetoothSocket socket = new BluetoothTreatmentConnection(bluetoothDevice).startPairingDevice();
//
//        if (socket != null) {
//            SocketSingleton.getInstance().setSocketInstance(socket);
//            activity.callNextScreen();
//        } else {
//            activity.showToast("Nao foi possivel parear com dispositivo.");
//        }
//    }
}
