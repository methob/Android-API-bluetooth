package com.example.jonathan.bluetooth_project_iot.connectMain.presenter;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.ButtonsChooseEdisonFragment;

/**
 * Created by unifor on 17/08/16.
 */
public class ButtonsChooseEdisonPresenter {

    ButtonsChooseEdisonFragment fragment;

    public ButtonsChooseEdisonPresenter(ButtonsChooseEdisonFragment buttonsChooseEdisonFragment) {
        this.fragment = buttonsChooseEdisonFragment;
    }

    public boolean isBluetoothEnable() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter.isEnabled();
    }

    public void requestEnableBluetooth() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        fragment.startActivityForResult(enableBtIntent, fragment.REQUEST_ENABLE_BT);
    }
}
