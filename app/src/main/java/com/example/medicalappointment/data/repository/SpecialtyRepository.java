package com.example.medicalappointment.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.medicalappointment.data.firebase.Collections;
import com.example.medicalappointment.data.firebase.FirestoreHelper;
import com.example.medicalappointment.data.model.Specialty;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyRepository {

    private final FirebaseFirestore firestore;

    public SpecialtyRepository() {
        this.firestore = FirestoreHelper.getFirestoreInstance();
    }

    public LiveData<List<Specialty>> getSpecialties() {
        MutableLiveData<List<Specialty>> specialtiesLiveData = new MutableLiveData<>();

        // 1. Immediately set mock data as the initial/default value
        specialtiesLiveData.setValue(getMockSpecialties());

        // 2. Fetch asynchronously from Firestore in the background
        firestore.collection(Collections.SPECIALTIES)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && !task.getResult().isEmpty()) {
                        List<Specialty> list = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Specialty specialty = document.toObject(Specialty.class);
                            specialty.setId(document.getId());
                            list.add(specialty);
                        }
                        // Overwrite only if Firestore successfully returned real data
                        specialtiesLiveData.setValue(list);
                    }
                });

        return specialtiesLiveData;
    }

    private List<Specialty> getMockSpecialties() {
        List<Specialty> mocks = new ArrayList<>();
        mocks.add(new Specialty("1", "Nội tim mạch", ""));
        mocks.add(new Specialty("2", "Nhi khoa", ""));
        mocks.add(new Specialty("3", "Sản phụ khoa", ""));
        return mocks;
    }
}
