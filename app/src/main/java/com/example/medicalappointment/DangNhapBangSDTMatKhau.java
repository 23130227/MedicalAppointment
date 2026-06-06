package com.example.medicalappointment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DangNhapBangSDTMatKhau extends AppCompatActivity {

    // Khai báo các thành phần giao diện (UI Components)
    private EditText edtPhone;
    private EditText edtPassword;
    private Button btnLogin;
    private LinearLayout btnGoogleLogin;
    private TextView tvForgotPassword;
    private TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_bang_s_d_t_mat_khau);

        // Ánh xạ các View từ XML sang Java theo ID mới đã refactor
        initViews();

        // Xử lý sự kiện khi nhấn nút Đăng Nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtPhone.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(DangNhapBangSDTMatKhau.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    // Thực hiện logic kiểm tra đăng nhập tại đây
                    Toast.makeText(DangNhapBangSDTMatKhau.this, "Đang xử lý đăng nhập...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện nhấn nút Đăng nhập bằng Google
        btnGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DangNhapBangSDTMatKhau.this, "Đăng nhập với Google", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý sự kiện nhấn Quên mật khẩu
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DangNhapBangSDTMatKhau.this, "Chuyển sang màn hình Quên mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý sự kiện nhấn Đăng ký ngay
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DangNhapBangSDTMatKhau.this, "Chuyển sang màn hình Đăng ký", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Hàm dùng để khởi tạo và ánh xạ toàn bộ các View từ file XML
     */
    private void initViews() {
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoogleLogin = findViewById(R.id.btnGoogleLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);
    }
}