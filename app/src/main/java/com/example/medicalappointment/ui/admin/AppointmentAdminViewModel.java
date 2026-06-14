package com.example.medicalappointment.ui.admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.medicalappointment.data.model.Appointment;
import com.example.medicalappointment.data.repository.AppointmentRepository;
import java.util.List;

public class AppointmentAdminViewModel extends ViewModel {

    private final AppointmentRepository repository;
    private final LiveData<List<Appointment>> allAppointments;

    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> operationSuccess = new MutableLiveData<>();

    public AppointmentAdminViewModel() {
        this.repository = new AppointmentRepository();
        this.allAppointments = repository.getAllAppointments();
    }

    public LiveData<List<Appointment>> getAllAppointments() {
        return allAppointments;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public LiveData<Boolean> getOperationSuccess() {
        return operationSuccess;
    }

    public void saveAppointment(Appointment appointment) {
        AppointmentRepository.Callback<String> createCallback = new AppointmentRepository.Callback<String>() {
            @Override
            public void onSuccess(String result) {
                toastMessage.setValue("Appointment saved successfully!");
                operationSuccess.setValue(true);
            }

            @Override
            public void onFailure(Exception e) {
                toastMessage.setValue("Error saving: " + e.getMessage());
            }
        };

        AppointmentRepository.Callback<Void> updateCallback = new AppointmentRepository.Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                toastMessage.setValue("Appointment updated successfully!");
                operationSuccess.setValue(true);
            }

            @Override
            public void onFailure(Exception e) {
                toastMessage.setValue("Error updating: " + e.getMessage());
            }
        };

        if (appointment.getId() == null || appointment.getId().isEmpty()) {
            repository.createAppointment(appointment, createCallback);
        } else {
            repository.updateAppointment(appointment, updateCallback);
        }
    }

    public void deleteAppointment(String appointmentId) {
        repository.deleteAppointment(appointmentId, new AppointmentRepository.Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                toastMessage.setValue("Appointment deleted successfully!");
            }

            @Override
            public void onFailure(Exception e) {
                toastMessage.setValue("Error deleting: " + e.getMessage());
            }
        });
    }
}