package com.example.jonathan.bluetooth_project_iot.chat.view;

import android.app.Dialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.jonathan.bluetooth_project_iot.R;
import com.example.jonathan.bluetooth_project_iot.chat.presenter.MainPresenterImpl;
import com.example.jonathan.bluetooth_project_iot.utils.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById
    Button buttonSendMessage;

    @ViewById
    EditText frameSendMessage;

    @ViewById
    EditText frameContainerMessage;

    @Bean
    MainPresenterImpl mainPresenter;

    @AfterViews
    public void afterViews() {
        mainPresenter.setInstance(this);
        mainPresenter.startDialog();
    }

    public void createDialogDiscoveredDevices() {
        Dialog dialog = new Dialog(this);
        CustomDialogDiscoveredDevices customDialogDiscoveredDevices = CustomDialogDiscoveredDevices_.build(this);
        dialog.setContentView(customDialogDiscoveredDevices);
        dialog.setCancelable(false);
        dialog.setTitle("Discovered Devices");
        dialog.show();
    }

    @Click(R.id.button_send_message)
    public void sendMessage() {

    }

}
