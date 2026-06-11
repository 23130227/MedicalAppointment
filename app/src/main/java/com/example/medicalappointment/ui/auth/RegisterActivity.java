package com.example.medicalappointment.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.medicalappointment.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFullname, etEmail, etPhone, etPassword, etConfirmPassword;
    private Button btnRegister, btnRegisterGoogle;
    private TextView tvGoToLogin;

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        initViews();
        handleEvents();
        setupObservers();
    }

    private void initViews() {
        etFullname = findViewById(R.id.et_register_fullname);
        etEmail = findViewById(R.id.et_register_email);
        etPhone = findViewById(R.id.et_register_phone);
        etPassword = findViewById(R.id.et_register_password);
        etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        btnRegisterGoogle = findViewById(R.id.btn_register_google);
        tvGoToLogin = findViewById(R.id.tv_go_to_login);
    }

    private void handleEvents() {
        btnRegister.setOnClickListener(v -> {
            String fullname = etFullname.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if (fullname.isEmpty()) {
                etFullname.setError("Vui lòng điền Họ và tên!");
                etFullname.requestFocus();
                return;
            }
            if (email.isEmpty()) {
                etEmail.setError("Vui lòng điền Email!");
                etEmail.requestFocus();
                return;
            }
            if (phone.isEmpty()) {
                etPhone.setError("Vui lòng điền Số điện thoại!");
                etPhone.requestFocus();
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Mật khẩu phải từ 6 ký tự trở lên!");
                etPassword.requestFocus();
                return;
            }
            if (!password.equals(confirmPassword)) {
                etConfirmPassword.setError("Mật khẩu xác nhận không trùng khớp!");
                etConfirmPassword.requestFocus();
                return;
            }

            authViewModel.register(email, password);
        });

        tvGoToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        btnRegisterGoogle.setOnClickListener(v -> {
            Toast.makeText(this, "Chức năng đăng ký bằng Google", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupObservers() {
        authViewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Toast.makeText(this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        authViewModel.getErrorLiveData().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, "Đăng ký thất bại: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });

        authViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                btnRegister.setEnabled(!isLoading);
                if (isLoading) {
                    btnRegister.setText("Đang xử lý đăng ký...");
                } else {
                    btnRegister.setText(R.string.btn_register_text);
                }
            }
        });
    }
}