package com.example.medicalappointment.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.example.medicalappointment.R;

public class SuccessFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_success, container, false);
        view.findViewById(R.id.btn_login_now).setOnClickListener(v -> requireActivity().finish());
        return view;
    }
}