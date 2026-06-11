package com.example.medicalappointment.data.repository;

import  android.util.Log;
import com.example.medicalappointment.data.firebase.FirestoreHelper;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import java.util.List;

public class GenericMockRepository {
    private static final String TAG = "GenericMockRepo";
    private final FirebaseFirestore db;

    public GenericMockRepository() {
        this.db = FirestoreHelper.getFirestoreInstance();
    }

    // Callback chuẩn để đồng bộ hóa trạng thái bất đồng bộ
    public interface RepositoryCallback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);
    }

    /**
     * HÀM SEED DATA TỔNG QUÁT (BATCH UPLOAD)
     * @param collectionName Tên của Collection trên Firestore (lấy từ file Collections.java)
     * @param dataList Danh sách các Object dữ liệu mẫu (Kiểu T tổng quát)
     * @param documentIds Danh sách ID tương ứng cho từng document (Nếu null, Firebase tự sinh ID)
     */
    public <T> void seedCollection(String collectionName, List<T> dataList, List<String> documentIds, RepositoryCallback<String> callback) {
        if (dataList == null || dataList.isEmpty()) {
            callback.onFailure(new Exception("Danh sách dữ liệu mẫu trống (Null hoặc Empty)!"));
            return;
        }

        // Firestore giới hạn tối đa 500 hành động trong 1 Batch
        WriteBatch batch = db.batch();

        for (int i = 0; i < dataList.size(); i++) {
            T item = dataList.get(i);
            String docId = (documentIds != null && documentIds.size() > i) ? documentIds.get(i) : null;

            if (docId != null && !docId.trim().isEmpty()) {
                // Sử dụng ID cố định do bạn tự đặt (để làm khóa ngoại liên kết giữa các bảng)
                batch.set(db.collection(collectionName).document(docId), item);
            } else {
                // Tự sinh ID ngẫu nhiên nếu không truyền vào
                batch.set(db.collection(collectionName).document(), item);
            }
        }

        // Thực thi gửi gói dữ liệu lên Cloud
        batch.commit()
                .addOnSuccessListener(aVoid -> {
                    Log.i(TAG, " Seed thành công " + dataList.size() + " tài liệu vào kho: [" + collectionName + "]");
                    callback.onSuccess(collectionName);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, " Thất bại khi seed dữ liệu vào: [" + collectionName + "]", e);
                    callback.onFailure(e);
                });
    }
}