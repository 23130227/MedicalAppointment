package com.example.medicalappointment.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import com.example.medicalappointment.data.model.Appointment;
import com.example.medicalappointment.data.model.Specialty;
import com.example.medicalappointment.data.repository.AppointmentRepository;
import com.example.medicalappointment.data.repository.SpecialtyRepository;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final SpecialtyRepository specialtyRepository;
    private final AppointmentRepository appointmentRepository;

    private final MediatorLiveData<List<Specialty>> specialties = new MediatorLiveData<>();
    private final MediatorLiveData<List<Appointment>> appointments = new MediatorLiveData<>();

    public HomeViewModel() {
        this.specialtyRepository = new SpecialtyRepository();
        this.appointmentRepository = new AppointmentRepository();
        loadSpecialties();
    }

    public LiveData<List<Specialty>> getData() {
        return specialties;
    }

    public void loadSpecialties() {
        LiveData<List<Specialty>> source = specialtyRepository.getSpecialties();
        specialties.addSource(source, list -> {
            specialties.setValue(list);
            specialties.removeSource(source);
        });
    }

    // --- Appointments Logic ---

    public LiveData<List<Appointment>> getAppointmentsByPatient(String patientId) {
        LiveData<List<Appointment>> source = appointmentRepository.getAppointmentsByPatient(patientId);
        appointments.addSource(source, list -> {
            appointments.setValue(list);
            appointments.removeSource(source);
        });
        return appointments;
    }

    public LiveData<Appointment> getAppointmentDetail(String appointmentId) {
        return appointmentRepository.getAppointment(appointmentId);
    }

    public void createAppointment(Appointment appointment, AppointmentRepository.Callback<String> callback) {
        appointmentRepository.createAppointment(appointment, callback);
    }

    public void updateAppointment(Appointment appointment, AppointmentRepository.Callback<Void> callback) {
        appointmentRepository.updateAppointment(appointment, callback);
    }
}
