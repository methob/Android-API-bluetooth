package com.example.jonathan.bluetooth_project_iot.chat.presenter;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import com.example.jonathan.bluetooth_project_iot.chat.view.MainActivity;

import org.androidannotations.annotations.EBean;

/**
 * Created by Jonathan on 21/06/2016.
 */

@EBean
public class MainPresenterImpl implements IBasePresenter {

    private MainActivity mainActivity;

    private int REQUEST_ENABLE_BT = 100;

    public void startDialog() {

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mainActivity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        mainActivity.createDialogDiscoveredDevices();
    }

    @Override
    public void setInstance(Object object) {
        this.mainActivity = (MainActivity) object;
    }
}
