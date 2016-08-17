package com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.listItem;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jonathan.bluetooth_project_iot.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.item_view_list_found_devices)
public class SearchEdisonDevicesItemView extends RelativeLayout {

    @ViewById
    TextView infoDeviceText;

    @ViewById
    TextView address;

    @ViewById
    TextView state;

    public SearchEdisonDevicesItemView(Context context) {
        super(context);
    }

    public void bind(BluetoothDevice device) {

        String nome = "Nome: " + device.getName();
        String addressT = "Address: " + device.getAddress();
        infoDeviceText.setText(nome);
        address.setText(addressT);

        checkStateOfDevice(device);
    }

    public void checkStateOfDevice(BluetoothDevice device) {

        String status = "Status: ";

        if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
            state.setText(status+"Pareado");
        } else if (device.getBondState() == BluetoothDevice.BOND_NONE) {
            state.setText(status+"Não pareado");
        }
    }
}
