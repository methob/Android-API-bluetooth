package com.example.jonathan.bluetooth_project_iot.connectMain.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathan.bluetooth_project_iot.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_add_new_edison_mac)
public class AddNewEdisonMacFragment extends Fragment {

    public AddNewEdisonMacFragment() {
        // Required empty public constructor
    }

    @Click(R.id.back_button)
    public void backScreen() {
        getActivity().onBackPressed();
    }

}
