package com.example.jonathan.bluetooth_project_iot.chat.view;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.jonathan.bluetooth_project_iot.R;
import com.example.jonathan.bluetooth_project_iot.chat.presenter.DialogPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Jonathan on 21/06/2016.
 */

@EViewGroup(R.layout.dialog_discovered_devices)
public class CustomDialogDiscoveredDevices extends RelativeLayout {

    @ViewById
    ListView listDiscoveredDevices;

    @Bean
    ListDevicesDialogAdapter adapter;

    @Bean
    DialogPresenter dialogPresenter;

    @ViewById
    ProgressBar loading;

    public CustomDialogDiscoveredDevices(Context context) {
        super(context);
    }

    @AfterViews
    public void afterViews() {
        dialogPresenter.setInstance(this);
        dialogPresenter.registerEvent();
        dialogPresenter.startDiscovered();
    }

    @Click(R.id.search_new_devices_button)
    public void SearchNewDevices() {
        if (!dialogPresenter.isBluetoothSearchNewDevice()) {
            showProgress();
            dialogPresenter.startDiscovered();
        }
    }

    public void setListAdapter(ArrayList<BluetoothDevice> bluetoothDevices) {
        loading.setVisibility(INVISIBLE);
        adapter.setListAdapter(bluetoothDevices);
        listDiscoveredDevices.setAdapter(adapter);
    }

    public void showProgress() {
        loading.setVisibility(VISIBLE);
        Log.d("","dsds");
    }

    @ItemClick(R.id.list_discovered_devices)
    public void deviceToPairedSelected(int position) {
        dialogPresenter.deviceToPairedSelected(position);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dialogPresenter.unregisterEvent();
    }
}
