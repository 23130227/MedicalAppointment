package com.example.medicalappointment.data.firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthHelper {
    private static FirebaseAuth mAuth;

    public static synchronized FirebaseAuth getAuthInstance() {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }

    // Kiểm tra nhanh xem user hiện tại đã đăng nhập chưa
    public static boolean isUserLoggedIn() {
        return getAuthInstance().getCurrentUser() != null;
    }

    // Lấy nhanh UID của user hiện tại (Sẽ dùng rất nhiều cho patient_id khi đặt lịch)
    public static String getCurrentUserId() {
        FirebaseUser user = getAuthInstance().getCurrentUser();
        return user != null ? user.getUid() : null;
    }

    // Đăng xuất xóa session
    public static void logout() {
        getAuthInstance().signOut();
    }
}