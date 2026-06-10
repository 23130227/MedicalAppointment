package com.example.medicalappointment.data.firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class FirestoreHelper {
    private static FirebaseFirestore mFirestore;

    public static synchronized FirebaseFirestore getFirestoreInstance() {
        if (mFirestore == null) {
            mFirestore = FirebaseFirestore.getInstance();

            // Cấu hình nâng cao (Ví dụ: Bật tính năng lưu offline)
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true) // Giúp app vẫn xem được danh sách bác sĩ khi mất mạng
                    .build();
            mFirestore.setFirestoreSettings(settings);
        }
        return mFirestore;
    }
}