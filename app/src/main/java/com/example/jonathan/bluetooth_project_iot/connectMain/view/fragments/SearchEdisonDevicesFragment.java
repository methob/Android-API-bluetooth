package com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments;


import android.bluetooth.BluetoothDevice;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jonathan.bluetooth_project_iot.R;
import com.example.jonathan.bluetooth_project_iot.connectMain.presenter.SearchEdisonDevicesPresenter;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.adapter.SearchEdisonDevicesAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;


@EFragment(R.layout.fragment_search_edison_devices)
public class SearchEdisonDevicesFragment extends Fragment {

    @ViewById
    ListView listviewEdisonDevices;

    @ViewById
    ProgressBar loading;

    @ViewById
    TextView feedbackCouldNotFindEdison;

    @Bean
    SearchEdisonDevicesAdapter adapter;

    SearchEdisonDevicesPresenter presenterFoundDevices;

    @AfterViews
    public void afterViews() {
        presenterFoundDevices = new SearchEdisonDevicesPresenter(this);
        presenterFoundDevices.registerEvent();
    }

    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        loading.setVisibility(View.INVISIBLE);
    }

    public void hideTextNotFoundDevice() {
        feedbackCouldNotFindEdison.setVisibility(View.INVISIBLE);
    }

    public void notifyChangeInAdapter(ArrayList<BluetoothDevice> devices) {
        adapter.setListAdapter(devices);
        adapter.notifyDataSetChanged();
    }

    public void setListAdapter(ArrayList<BluetoothDevice> bluetoothDevices, boolean emptyList) {

        if (emptyList) {
            if (bluetoothDevices.isEmpty()) {
                feedbackCouldNotFindEdison.setVisibility(View.VISIBLE);
            } else {
                feedbackCouldNotFindEdison.setVisibility(View.INVISIBLE);
            }
        }

        loading.setVisibility(View.INVISIBLE);
        adapter.setListAdapter(bluetoothDevices);
        listviewEdisonDevices.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterFoundDevices.unregisterEvent();
    }
}
