package com.example.medicalappointment.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medicalappointment.R;
import com.example.medicalappointment.data.model.Appointment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AppointmentListActivity extends AppCompatActivity {

    private AppointmentAdminViewModel viewModel;
    private AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_appointment_list);

        viewModel = new ViewModelProvider(this).get(AppointmentAdminViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rvAppointments);
        FloatingActionButton fabAdd = findViewById(R.id.fabAddAppointment);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AppointmentAdapter(new AppointmentAdapter.OnAppointmentClickListener() {
            @Override
            public void onItemClick(Appointment appointment) {
                // Read/Update context
                Intent intent = new Intent(AppointmentListActivity.this, AppointmentDetailActivity.class);
                intent.putExtra("appointment_id", appointment.getId());
                intent.putExtra("patient_id", appointment.getPatientId());
                intent.putExtra("schedule_id", appointment.getDoctorScheduleId());
                intent.putExtra("price", appointment.getActualPrice());
                intent.putExtra("symptoms", appointment.getSymptoms());
                intent.putExtra("status", appointment.getStatus());
                intent.putExtra("payment", appointment.getPaymentStatus());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(String id) {
                viewModel.deleteAppointment(id);
            }
        });
        recyclerView.setAdapter(adapter);

        viewModel.getAllAppointments().observe(this, list -> adapter.setAppointments(list));
        viewModel.getToastMessage().observe(this, msg -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());

        fabAdd.setOnClickListener(v -> startActivity(new Intent(this, AppointmentDetailActivity.class)));
    }
}