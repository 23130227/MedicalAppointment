package com.example.medicalappointment.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.medicalappointment.data.firebase.Collections;
import com.example.medicalappointment.data.firebase.FirestoreHelper;
import com.example.medicalappointment.data.model.Appointment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private final FirebaseFirestore firestore;

    public AppointmentRepository() {
        this.firestore = FirestoreHelper.getFirestoreInstance();
    }

    public interface Callback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);
    }

    /**
     * CREATE operation. Saves a new appointment to Firestore.
     */
    public void createAppointment(Appointment appointment, Callback<String> callback) {
        if (appointment.getId() == null || appointment.getId().isEmpty()) {
            firestore.collection(Collections.APPOINTMENTS)
                    .add(appointment)
                    .addOnSuccessListener(documentReference -> {
                        String newId = documentReference.getId();
                        appointment.setId(newId);
                        // Save the assigned ID inside the document attributes as well
                        documentReference.update("id", newId)
                                .addOnSuccessListener(aVoid -> callback.onSuccess(newId))
                                .addOnFailureListener(callback::onFailure);
                    })
                    .addOnFailureListener(callback::onFailure);
        } else {
            firestore.collection(Collections.APPOINTMENTS)
                    .document(appointment.getId())
                    .set(appointment)
                    .addOnSuccessListener(aVoid -> callback.onSuccess(appointment.getId()))
                    .addOnFailureListener(callback::onFailure);
        }
    }

    /**
     * GET operation (Single Appointment by ID). Returns LiveData stream.
     */
    public LiveData<Appointment> getAppointment(String appointmentId) {
        MutableLiveData<Appointment> liveData = new MutableLiveData<>();
        firestore.collection(Collections.APPOINTMENTS)
                .document(appointmentId)
                .addSnapshotListener((snapshot, e) -> {
                    if (e != null) {
                        liveData.setValue(null);
                        return;
                    }
                    if (snapshot != null && snapshot.exists()) {
                        Appointment appointment = snapshot.toObject(Appointment.class);
                        liveData.setValue(appointment);
                    } else {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    /**
     * GET operation (List of Appointments by Patient ID). Returns LiveData stream.
     */
    public LiveData<List<Appointment>> getAppointmentsByPatient(String patientId) {
        MutableLiveData<List<Appointment>> liveData = new MutableLiveData<>();
        firestore.collection(Collections.APPOINTMENTS)
                .whereEqualTo("patientId", patientId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        liveData.setValue(new ArrayList<>());
                        return;
                    }
                    List<Appointment> list = new ArrayList<>();
                    if (value != null) {
                        for (QueryDocumentSnapshot doc : value) {
                            Appointment appt = doc.toObject(Appointment.class);
                            appt.setId(doc.getId());
                            list.add(appt);
                        }
                    }
                    liveData.setValue(list);
                });
        return liveData;
    }

    /**
     * UPDATE operation. Updates existing fields of an appointment.
     */
    public void updateAppointment(Appointment appointment, Callback<Void> callback) {
        if (appointment.getId() == null || appointment.getId().isEmpty()) {
            callback.onFailure(new IllegalArgumentException("Appointment ID cannot be null or empty for update operations"));
            return;
        }
        firestore.collection(Collections.APPOINTMENTS)
                .document(appointment.getId())
                .set(appointment)
                .addOnSuccessListener(aVoid -> callback.onSuccess(null))
                .addOnFailureListener(callback::onFailure);
    }
}
