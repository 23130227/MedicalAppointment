package com.example.medicalappointment.utils;

import com.example.medicalappointment.data.firebase.Collections;
import com.example.medicalappointment.data.model.Doctor;
import com.example.medicalappointment.data.model.DoctorSchedule;
import com.example.medicalappointment.data.model.Specialty;
import com.example.medicalappointment.data.model.Patient;
import com.example.medicalappointment.data.repository.GenericMockRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDataSeeder {

    public interface SeederProgressCallback {
        void onAllServicesSeeded();
        void onSeedingError(Exception e);
    }

    public static void startSeedingSystem(GenericMockRepository repo, SeederProgressCallback masterCallback) {

        // =========================================================================
        // KHỐI 1: ACCOUNTS (Tài khoản) - Tạo 6 TK Bác sĩ & 20 TK Bệnh nhân
        // =========================================================================
        List<Map<String, Object>> accounts = new ArrayList<>();
        List<String> accountIds = new ArrayList<>();

        // 1.1 Tạo cứng 6 Tài khoản Bác sĩ
        for (int i = 1; i <= 6; i++) {
            Map<String, Object> accDoc = new HashMap<>();
            String id = "acc_doc_0" + i;
            accDoc.put("id", id);
            accDoc.put("phone", "091100000" + i);
            accDoc.put("email", "doctor" + i + "@medical.com");
            accDoc.put("role", "doctor");
            accDoc.put("created_at", "2026-06-01T08:00:00Z");
            accounts.add(accDoc);
            accountIds.add(id);
        }

        // 1.2 Dùng vòng lặp tạo nhanh 20 Tài khoản Bệnh nhân
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> accPat = new HashMap<>();
            String id = (i < 10) ? "acc_pat_0" + i : "acc_pat_" + i;
            accPat.put("id", id);
            accPat.put("phone", "09880000" + (i < 10 ? "0"+i : i));
            accPat.put("email", "patient" + i + "@gmail.com");
            accPat.put("role", "patient");
            accPat.put("created_at", "2026-06-02T10:00:00Z");
            accounts.add(accPat);
            accountIds.add(id);
        }

        repo.seedCollection("accounts", accounts, accountIds, new GenericMockRepository.RepositoryCallback<String>() {
            @Override
            public void onSuccess(String result) {

                // =========================================================================
                // KHỐI 2: SPECIALTIES (6 Chuyên khoa cơ bản)
                // =========================================================================
                List<Specialty> specialties = new ArrayList<>();
                List<String> specialtyIds = new ArrayList<>();

                String[] specNames = {"Răng Hàm Mặt", "Nhi Khoa", "Tim Mạch", "Da Liễu", "Tiêu Hóa", "Thần Kinh"};
                for (int i = 0; i < specNames.length; i++) {
                    String id = "spec_0" + (i + 1);
                    specialties.add(new Specialty(id, specNames[i], "https://example.com/icon_" + id + ".png"));
                    specialtyIds.add(id);
                }

                repo.seedCollection(Collections.SPECIALTIES, specialties, specialtyIds, new GenericMockRepository.RepositoryCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        // =========================================================================
                        // KHỐI 3: DOCTORS (6 Bác sĩ gắn với 6 Chuyên khoa trên)
                        // =========================================================================
                        List<Doctor> doctors = new ArrayList<>();
                        List<String> doctorIds = new ArrayList<>();

                        String[] docNames = {"BS. Nguyễn Văn Khang", "BS. Lê Thị Mỹ An", "GS.TS Phạm Hoàng Nam", "BS. Trần Thanh Bình", "BS. Đặng Văn Ngọc", "BS. Vũ Thu Hà"};
                        int[] fees = {350000, 280000, 500000, 300000, 250000, 400000};

                        for (int i = 0; i < 6; i++) {
                            String id = "doc_0" + (i + 1);
                            doctors.add(new Doctor(id, "acc_doc_0" + (i + 1), "spec_0" + (i + 1), docNames[i], "Bác sĩ chuyên khoa cấp II với nhiều năm kinh nghiệm", fees[i]));
                            doctorIds.add(id);
                        }

                        repo.seedCollection(Collections.DOCTORS, doctors, doctorIds, new GenericMockRepository.RepositoryCallback<String>() {
                            @Override
                            public void onSuccess(String result) {

                                // =========================================================================
                                // KHỐI 4: PATIENTS (20 Bệnh nhân)
                                // =========================================================================
                                List<Patient> patients = new ArrayList<>();
                                List<String> patientIds = new ArrayList<>();

                                for (int i = 1; i <= 20; i++) {
                                    String id = (i < 10) ? "pat_0" + i : "pat_" + i;
                                    String accId = (i < 10) ? "acc_pat_0" + i : "acc_pat_" + i;
                                    String gender = (i % 2 == 0) ? "Nữ" : "Nam";
                                    patients.add(new Patient(id, accId, "Bệnh Nhân Test Số " + i, "199" + (i%10) + "-05-12", gender, "Chủ hộ", "020123" + i, "09880000" + (i < 10 ? "0"+i : i)));
                                    patientIds.add(id);
                                }

                                repo.seedCollection(Collections.PATIENTS, patients, patientIds, new GenericMockRepository.RepositoryCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {

                                        // =========================================================================
                                        // KHỐI 5: DOCTOR SCHEDULES (30 Lịch khám - Mỗi bác sĩ 5 ca)
                                        // =========================================================================
                                        List<DoctorSchedule> schedules = new ArrayList<>();
                                        List<String> scheduleIds = new ArrayList<>();
                                        String[] timeSlots = {"08:00 - 09:00", "09:30 - 10:30", "13:00 - 14:00", "14:30 - 15:30", "16:00 - 17:00"};

                                        int schedCounter = 1;
                                        for (int doc = 1; doc <= 6; doc++) {
                                            for (int slot = 0; slot < 5; slot++) {
                                                String id = (schedCounter < 10) ? "sched_0" + schedCounter : "sched_" + schedCounter;
                                                schedules.add(new DoctorSchedule(id, "doc_0" + doc, "2026-06-15", timeSlots[slot], 10, (slot % 3 == 0 ? 10 : 2), "active"));
                                                scheduleIds.add(id);
                                                schedCounter++;
                                            }
                                        }

                                        repo.seedCollection(Collections.DOCTOR_SCHEDULES, schedules, scheduleIds, new GenericMockRepository.RepositoryCallback<String>() {
                                            @Override
                                            public void onSuccess(String result) {

                                                // =========================================================================
                                                // KHỐI 6: APPOINTMENTS (15 Cuộc hẹn random)
                                                // =========================================================================
                                                List<Map<String, Object>> appointments = new ArrayList<>();
                                                List<String> appointmentIds = new ArrayList<>();

                                                for (int i = 1; i <= 15; i++) {
                                                    Map<String, Object> app = new HashMap<>();
                                                    String id = (i < 10) ? "app_0" + i : "app_" + i;
                                                    String patId = (i < 10) ? "pat_0" + i : "pat_" + i; // Lấy 15 bệnh nhân đầu
                                                    String schedId = (i < 10) ? "sched_0" + i : "sched_" + i; // Lấy 15 lịch đầu

                                                    app.put("id", id);
                                                    app.put("patient_id", patId);
                                                    app.put("doctor_schedule_id", schedId);
                                                    app.put("actual_price", 300000 + (i * 10000));
                                                    app.put("symptoms", "Triệu chứng test tự động số " + i);
                                                    app.put("status", (i % 3 == 0) ? "completed" : "confirmed");
                                                    app.put("payment_status", "paid");
                                                    app.put("created_at", "2026-06-10T14:20:00Z");
                                                    appointments.add(app);
                                                    appointmentIds.add(id);
                                                }

                                                repo.seedCollection("appointments", appointments, appointmentIds, new GenericMockRepository.RepositoryCallback<String>() {
                                                    @Override
                                                    public void onSuccess(String result) {

                                                        // =========================================================================
                                                        // KHỐI 7: REVIEWS (Đánh giá cho các ca đã completed)
                                                        // =========================================================================
                                                        List<Map<String, Object>> reviews = new ArrayList<>();
                                                        List<String> reviewIds = new ArrayList<>();

                                                        int revCounter = 1;
                                                        for (int i = 3; i <= 15; i += 3) { // Chỉ lấy các app có status completed
                                                            Map<String, Object> rev = new HashMap<>();
                                                            String id = "rev_0" + revCounter;
                                                            String appId = (i < 10) ? "app_0" + i : "app_" + i;

                                                            rev.put("id", id);
                                                            rev.put("appointment_id", appId);
                                                            rev.put("rating", (i % 2 == 0) ? 4 : 5);
                                                            rev.put("comment", "Phản hồi test số " + revCounter + ". Bác sĩ tận tình.");
                                                            rev.put("created_at", "2026-06-16T10:00:00Z");
                                                            reviews.add(rev);
                                                            reviewIds.add(id);
                                                            revCounter++;
                                                        }

                                                        repo.seedCollection("reviews", reviews, reviewIds, new GenericMockRepository.RepositoryCallback<String>() {
                                                            @Override
                                                            public void onSuccess(String result) {
                                                                masterCallback.onAllServicesSeeded();
                                                            }
                                                            @Override
                                                            public void onFailure(Exception e) { masterCallback.onSeedingError(e); }
                                                        });
                                                    }
                                                    @Override
                                                    public void onFailure(Exception e) { masterCallback.onSeedingError(e); }
                                                });
                                            }
                                            @Override
                                            public void onFailure(Exception e) { masterCallback.onSeedingError(e); }
                                        });
                                    }
                                    @Override
                                    public void onFailure(Exception e) { masterCallback.onSeedingError(e); }
                                });
                            }
                            @Override
                            public void onFailure(Exception e) { masterCallback.onSeedingError(e); }
                        });
                    }
                    @Override
                    public void onFailure(Exception e) { masterCallback.onSeedingError(e); }
                });
            }
            @Override
            public void onFailure(Exception e) { masterCallback.onSeedingError(e); }
        });
    }
}