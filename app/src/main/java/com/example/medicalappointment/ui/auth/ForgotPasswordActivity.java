package com.example.medicalappointment.ui.auth;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medicalappointment.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new EmailFragment())
                    .commit();
        }
    }
}