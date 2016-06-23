package com.example.jonathan.bluetooth_project_iot.chat.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;

import com.example.jonathan.bluetooth_project_iot.chat.view.CustomDialogDiscoveredDevices;
import com.example.jonathan.bluetooth_project_iot.events.DiscoveredNewDeviceEvent;
import com.example.jonathan.bluetooth_project_iot.utils.BluetoothTreatmentConnection;
import com.example.jonathan.bluetooth_project_iot.utils.BroadcastReceiverNewDevices;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by Jonathan on 22/06/2016.
 */

@EBean
public class DialogPresenter implements IBasePresenter {

    private ArrayList<BluetoothDevice> devices;
    private BroadcastReceiverNewDevices broadcastReceiver;
    private Context context;

    private CustomDialogDiscoveredDevices customDialogDiscoveredDevices;

    public DialogPresenter(Context context) {
        this.context = context;
        devices = new ArrayList<>();
        startReceiver();
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
        customDialogDiscoveredDevices.setListAdapter(devices);
        customDialogDiscoveredDevices.showProgress();
        searchNewDevices();
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
            customDialogDiscoveredDevices.setListAdapter(devices);
        }
    }

    public void deviceToPairedSelected(int position) {
        BluetoothDevice bluetoothDevice = devices.get(position);
        new BluetoothTreatmentConnection(bluetoothDevice).startPairingDevice();
    }

    public void registerEvent() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEvent() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setInstance(Object object) {
        this.customDialogDiscoveredDevices = (CustomDialogDiscoveredDevices) object;
    }
}
