package com.example.medicalappointment;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.medicalappointment.data.repository.GenericMockRepository;
import com.example.medicalappointment.utils.MockDataSeeder;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class FirebaseDataSeederTest {
    private static final String TAG = "FirebaseDataSeederTest";

    @Test
    public void executeSystemDataSeeding() throws InterruptedException {
        // CountDownLatch giúp giữ cho luồng Test không bị tắt trước khi Firebase đẩy xong data
        CountDownLatch latch = new CountDownLatch(1);

        // Khởi tạo Repository từ package của bạn
        GenericMockRepository repository = new GenericMockRepository();

        Log.w(TAG, "==================================================");
        Log.w(TAG, "🚀 BẮT ĐẦU ĐẨY DATA LÊN FIREBASE...");
        Log.w(TAG, "==================================================");

        // Gọi hàm từ MockDataSeeder trong utils của bạn
        MockDataSeeder.startSeedingSystem(repository, new MockDataSeeder.SeederProgressCallback() {
            @Override
            public void onAllServicesSeeded() {
                Log.w(TAG, "🟢 SUCCESS: ĐÃ ĐẨY XONG TOÀN BỘ DỮ LIỆU LÊN FIREBASE!");
                latch.countDown(); // Mở khóa, báo hiệu test thành công
            }

            @Override
            public void onSeedingError(Exception e) {
                Log.e(TAG, "🔴 CRITICAL ERROR: LỖI ĐẨY DỮ LIỆU: " + e.getMessage());
                latch.countDown(); // Mở khóa, báo hiệu test kết thúc do lỗi
            }
        });

        // Chờ tối đa 30 giây cho mạng xử lý
        boolean isCompleted = latch.await(30, TimeUnit.SECONDS);
        if (!isCompleted) {
            Log.e(TAG, "⚠️ TIMEOUT: Quá 30 giây không nhận được phản hồi từ Firebase.");
        }
    }
}
