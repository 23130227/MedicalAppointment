package com.example.medicalappointment.ui.main;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medicalappointment.R;
import com.example.medicalappointment.ui.adapter.SpecialtyAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private HomeViewModel viewModel;
    private RecyclerView rvSpecialties;
    private SpecialtyAdapter specialtyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        setupViewModel();
        setupClickListeners();
    }

    private void initViews() {
        // Bind views from XML
        rvSpecialties = findViewById(R.id.rv_specialties);
    }

    private void setupRecyclerView() {
        // Setup RecyclerView with an empty list initially
        specialtyAdapter = new SpecialtyAdapter(new ArrayList<>());
        rvSpecialties.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSpecialties.setAdapter(specialtyAdapter);

        // Bind item click callback
        specialtyAdapter.setOnItemClickListener(specialty -> 
            Toast.makeText(MainActivity.this, specialty.getName(), Toast.LENGTH_SHORT).show()
        );
    }

    private void setupViewModel() {
        // Instantiates ViewModel
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Observe LiveData stream
        viewModel.getData().observe(this, list -> {
            if (list != null) {
                specialtyAdapter.updateData(list);
            }
        });
    }

    private void setupClickListeners() {
        // Setup clicks for booking and record buttons
        findViewById(R.id.btn_booking).setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, getString(R.string.booking_title), Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.btn_health_records).setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, getString(R.string.health_records_title), Toast.LENGTH_SHORT).show()
        );

        // Setup clicks for custom bottom navigation items
        findViewById(R.id.btn_nav_home).setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, getString(R.string.nav_home), Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.btn_nav_appointments).setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, getString(R.string.nav_appointments), Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.btn_nav_notifications).setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, getString(R.string.nav_notifications), Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.btn_nav_profile).setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, getString(R.string.nav_profile), Toast.LENGTH_SHORT).show()
        );
    }
}
