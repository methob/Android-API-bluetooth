package com.example.jonathan.bluetooth_project_iot.connectMain.view;

import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jonathan.bluetooth_project_iot.R;
import com.example.jonathan.bluetooth_project_iot.connectMain.presenter.FoundDevicesPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_connect)
public class ConnectActivity extends AppCompatActivity {

    @ViewById
    ListView connectDeviceListview;

    @ViewById
    ProgressBar loading;

    @ViewById
    TextView textNotFoundDevice;

    @Bean
    FoundDevicesPresenter presenterFoundDevices;

    @Bean
    FoundDevicesAdapter adapterFoundDevices;


    @AfterViews
    public void afterViews() {
        presenterFoundDevices.setInstance(this);
        presenterFoundDevices.checkBluetoothIsEnable();
        presenterFoundDevices.registerEvent();
        presenterFoundDevices.startDiscovered();
    }

    public void setListAdapter(ArrayList<BluetoothDevice> bluetoothDevices, boolean emptyList) {

        if (emptyList) {
            if (bluetoothDevices.isEmpty()) {
                textNotFoundDevice.setVisibility(View.VISIBLE);
            } else {
                textNotFoundDevice.setVisibility(View.INVISIBLE);
            }
        }

        loading.setVisibility(View.INVISIBLE);
        adapterFoundDevices.setListAdapter(bluetoothDevices);
        connectDeviceListview.setAdapter(adapterFoundDevices);
    }

    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterFoundDevices.unregisterEvent();
    }

    @Click(R.id.search_new_device_button)
    public void searchNewDevices() {
        if (!presenterFoundDevices.isBluetoothSearchNewDevice()) {
            showProgress();
            presenterFoundDevices.startDiscovered();
            textNotFoundDevice.setVisibility(View.INVISIBLE);
        }
    }

    @ItemClick(R.id.connect_device_listview)
    public void deviceToPairedSelected(int position) {
        presenterFoundDevices.deviceToPairedSelected(position);
    }
}
