package com.example.jonathan.bluetooth_project_iot.connectMain.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.jonathan.bluetooth_project_iot.R;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.AddNewEdisonMacFragment;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.AddNewEdisonMacFragment_;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.ButtonsChooseEdisonFragment;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.ButtonsChooseEdisonFragment_;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.SearchEdisonDevicesFragment;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments.SearchEdisonDevicesFragment_;
import com.example.jonathan.bluetooth_project_iot.utils.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_connect)
public class ChooseEdisonMainActivity extends BaseActivity {


    @AfterViews
    public void afterViews() {
        displayFragmentChooseEdisonButtons();
//        presenterFoundDevices.registerEvent();
//        presenterFoundDevices.checkAndStartOrderToStartBluetooth();
    }

//    @OnActivityResult(REQUEST_ENABLE_BT)
//    void onResult(int resultCode) {
//        if (resultCode == RESULT_OK)
//            presenterFoundDevices.startDiscovered();
//        else {
//            showMessageErroFindDevice();
//            showToast("Não foi possível detectar dispositivo.");
//        }
//    }
//
//    public void setListAdapter(ArrayList<BluetoothDevice> bluetoothDevices, boolean emptyList) {
//
//        if (emptyList) {
//            if (bluetoothDevices.isEmpty()) {
//                textNotFoundDevice.setVisibility(View.VISIBLE);
//            } else {
//                textNotFoundDevice.setVisibility(View.INVISIBLE);
//            }
//        }
//
//        loading.setVisibility(View.INVISIBLE);
//        adapterFoundDevices.setListAdapter(bluetoothDevices);
//        connectDeviceListview.setAdapter(adapterFoundDevices);
//    }
//
//    public void showMessageErroFindDevice() {
//        hideProgress();
//        textNotFoundDevice.setVisibility(View.VISIBLE);
//    }
//
//    public void showProgress() {
//        loading.setVisibility(View.VISIBLE);
//    }
//
//    public void hideProgress() {
//        loading.setVisibility(View.INVISIBLE);
//    }
//
//    public void hideTextNotFoundDevice() {
//        textNotFoundDevice.setVisibility(View.INVISIBLE);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenterFoundDevices.unregisterEvent();
//    }
//
//    @Click(R.id.search_new_device_button)
//    public void searchNewDevices() {
//
//        presenterFoundDevices.checkAndStartOrderToStartBluetooth();
//
//        if (presenterFoundDevices.isBluetoothEnable()) {
//            if (!presenterFoundDevices.isBluetoothSearchNewDevice()) {
//                showProgress();
//                presenterFoundDevices.startDiscovered();
//                textNotFoundDevice.setVisibility(View.INVISIBLE);
//            }
//        } else {
//            showMessageErroFindDevice();
//        }
//    }
//
//    @ItemClick(R.id.connect_device_listview)
//    public void deviceToPairedSelected(int position) {
//        presenterFoundDevices.deviceToPairedSelected(position);
//    }
//
//    public void callNextScreen() {
//
//    }
//
//    public void showToast(String msg) {
//
//        if (toastFeedbackBluetooth != null)
//            toastFeedbackBluetooth.cancel();
//
//        toastFeedbackBluetooth = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
//        toastFeedbackBluetooth.show();
//    }
//
//    public void notifyChangeInAdapter(ArrayList<BluetoothDevice> devices) {
//        adapterFoundDevices.setListAdapter(devices);
//        adapterFoundDevices.notifyDataSetChanged();
//    }

    public void displayFragmentAddNewEdisonMac() {
        AddNewEdisonMacFragment addNewEdisonMacFragment = new AddNewEdisonMacFragment_();
        displayFragments(addNewEdisonMacFragment,true);
    }

    private void displayFragments(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_fragments, fragment);

        if (addBackStack)
            transaction.addToBackStack(null);

        transaction.commit();
    }

    public void displayFragmentSearchEdisonWithMacs() {
        SearchEdisonDevicesFragment searchEdisonDevicesFragment = new SearchEdisonDevicesFragment_();
        displayFragments(searchEdisonDevicesFragment,true);
    }

    public void displayFragmentChooseEdisonButtons() {
        ButtonsChooseEdisonFragment buttonsChooseEdisonFragment = new ButtonsChooseEdisonFragment_();
        displayFragments(buttonsChooseEdisonFragment,false);
    }
}
