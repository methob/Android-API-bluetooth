package com.example.jonathan.bluetooth_project_iot.chat.view;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;


@EBean
public class ListDevicesDialogAdapter extends BaseAdapter {

    @RootContext
    Context context;

    List<BluetoothDevice> devices;

    public void setListAdapter(List<BluetoothDevice> devices) {
        this.devices = devices;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListDevicesDialogItemView listDevicesDialogItemView;

        if (convertView == null) {
            listDevicesDialogItemView = ListDevicesDialogItemView_.build(context);
        } else {
            listDevicesDialogItemView = (ListDevicesDialogItemView) convertView;
        }

        listDevicesDialogItemView.bind(devices.get(position));

        return listDevicesDialogItemView;
    }
}
