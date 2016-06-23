package com.example.jonathan.bluetooth_project_iot.chat.view;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jonathan.bluetooth_project_iot.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Jonathan on 22/06/2016.
 */
@EViewGroup(R.layout.dialog_list_devices_item_view)
public class ListDevicesDialogItemView extends RelativeLayout {

    @ViewById
    TextView infoDeviceText;

    @ViewById
    TextView address;

    @ViewById
    TextView state;

    public ListDevicesDialogItemView(Context context) {
        super(context);
    }

    public void bind(BluetoothDevice device) {

        String nome = "Nome: " + device.getName();
        String addressT = "Address: " + device.getAddress();
        infoDeviceText.setText(nome);
        address.setText(addressT);
    }

    public void getTextifDeviceisPaired(BluetoothDevice device) {
        if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
            state.setText("Estado: Pareado");
        } else {
            state.setText("Estado: Não Pareado");
        }
    }

}
