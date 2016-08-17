package com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.jonathan.bluetooth_project_iot.R;
import com.example.jonathan.bluetooth_project_iot.connectMain.presenter.ButtonsChooseEdisonPresenter;
import com.example.jonathan.bluetooth_project_iot.connectMain.view.ChooseEdisonMainActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;

@EFragment(R.layout.fragment_buttons_choose_edison)
public class ButtonsChooseEdisonFragment extends Fragment {

    private ChooseEdisonMainActivity activity;
    private ButtonsChooseEdisonPresenter presenter;

    public final int REQUEST_ENABLE_BT = 100;

    Toast toastFeedbackBluetooth;

    @AfterViews
    public void afterViews() {
        activity = (ChooseEdisonMainActivity) getActivity();
        presenter = new ButtonsChooseEdisonPresenter(this);
    }

    @Click(R.id.add_new_mac_button)
    public void addNewMacEdison() {
        activity.displayFragmentAddNewEdisonMac();
    }

    @Click(R.id.search_between_existing_macs)
    public void searchBetweenExistingMacs() {
        if (presenter.isBluetoothEnable())
            activity.displayFragmentSearchEdisonWithMacs();
        else
            presenter.requestEnableBluetooth();
    }

    @OnActivityResult(REQUEST_ENABLE_BT)
    void onResult(int resultCode) {
        if (resultCode == getActivity().RESULT_OK)
            activity.displayFragmentSearchEdisonWithMacs();
        else {
            showToast("Não foi possível detectar dispositivo.");
        }
    }


    public void showToast(String msg) {

        if (toastFeedbackBluetooth != null)
            toastFeedbackBluetooth.cancel();

        toastFeedbackBluetooth = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        toastFeedbackBluetooth.show();
    }

}
