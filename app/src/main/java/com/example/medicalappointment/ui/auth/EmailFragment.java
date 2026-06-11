package com.example.medicalappointment.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medicalappointment.R;

public class EmailFragment extends Fragment {
    private AuthViewModel viewModel;
    private EditText etEmail;
    private Button btnGui;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        etEmail = view.findViewById(R.id.et_email);
        btnGui = view.findViewById(R.id.btn_gui_otp);

        btnGui.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Vui lòng nhập đúng định dạng email!");
                return;
            }

            viewModel.setEmail(email);
            viewModel.forgotPassword(email);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getResetStatus().observe(getViewLifecycleOwner(), status -> {
            if (status != null) {
                if (status.contains("đã được gửi")) {
                    replaceFragment(new SuccessFragment());
                } else {
                    Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}