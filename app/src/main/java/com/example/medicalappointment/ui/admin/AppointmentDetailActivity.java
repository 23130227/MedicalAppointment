package com.example.medicalappointment.ui.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.medicalappointment.R;
import com.example.medicalappointment.data.model.Appointment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppointmentDetailActivity extends AppCompatActivity {

    private AppointmentAdminViewModel viewModel;
    private EditText etPatientId, etDocId, etPrice, etSymptoms, etStatus, etPayment;
    private String currentId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_appointment_detail);

        viewModel = new ViewModelProvider(this).get(AppointmentAdminViewModel.class);

        TextView formTitle = findViewById(R.id.formTitle);
        etPatientId = findViewById(R.id.etPatientId);
        etDocId = findViewById(R.id.etDoctorScheduleId);
        etPrice = findViewById(R.id.etPrice);
        etSymptoms = findViewById(R.id.etSymptoms);
        etStatus = findViewById(R.id.etStatus);
        etPayment = findViewById(R.id.etPayment);
        Button btnSave = findViewById(R.id.btnSave);

        // Check if updating existing item
        if (getIntent().hasExtra("appointment_id")) {
            currentId = getIntent().getStringExtra("appointment_id");
            formTitle.setText("Edit Appointment");
            etPatientId.setText(getIntent().getStringExtra("patient_id"));
            etDocId.setText(getIntent().getStringExtra("schedule_id"));
            etPrice.setText(String.valueOf(getIntent().getIntExtra("price", 0)));
            etSymptoms.setText(getIntent().getStringExtra("symptoms"));
            etStatus.setText(getIntent().getStringExtra("status"));
            etPayment.setText(getIntent().getStringExtra("payment"));
        } else {
            formTitle.setText("New Appointment");
        }

        btnSave.setOnClickListener(v -> saveForm());
        viewModel.getOperationSuccess().observe(this, success -> { if(success) finish(); });
    }

    private void saveForm() {
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        Appointment appointment = new Appointment();
        appointment.setId(currentId);
        appointment.setPatientId(etPatientId.getText().toString());
        appointment.setDoctorScheduleId(etDocId.getText().toString());
        appointment.setActualPrice(Integer.parseInt(etPrice.getText().toString().trim().isEmpty() ? "0" : etPrice.getText().toString().trim()));
        appointment.setSymptoms(etSymptoms.getText().toString());
        appointment.setStatus(etStatus.getText().toString());
        appointment.setPaymentStatus(etPayment.getText().toString());
        appointment.setUpdatedAt(currentTime);

        if (currentId == null) {
            appointment.setCreatedAt(currentTime);
        }

        viewModel.saveAppointment(appointment);
    }
}