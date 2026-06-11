package com.example.medicalappointment.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.medicalappointment.data.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

public class AuthViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<FirebaseUser> _userLiveData = new MutableLiveData<>();
    public LiveData<FirebaseUser> getUserLiveData() { return _userLiveData; }
    private final MutableLiveData<String> _resetStatus = new MutableLiveData<>();
    public LiveData<String> getResetStatus() { return _resetStatus; }
    private final MutableLiveData<String> _errorLiveData = new MutableLiveData<>();
    public LiveData<String> getErrorLiveData() { return _errorLiveData; }

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    private String email;
    public LiveData<Boolean> getIsLoading() { return _isLoading; }

    public AuthViewModel() {
        this.authRepository = new AuthRepository();
    }

    public void login(String email, String password) {
        _isLoading.setValue(true);
        authRepository.loginWithEmail(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                _isLoading.setValue(false);
                _userLiveData.setValue(user);
            }

            @Override
            public void onFailure(String errorMessage) {
                _isLoading.setValue(false);
                _errorLiveData.setValue(errorMessage);
            }
        });
    }

    public void register(String email, String password) {
        _isLoading.setValue(true);
        authRepository.registerWithEmail(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                _isLoading.setValue(false);
                _userLiveData.setValue(user);
            }

            @Override
            public void onFailure(String errorMessage) {
                _isLoading.setValue(false);
                _errorLiveData.setValue(errorMessage);
            }
        });
    }

    public void forgotPassword(String email) {
        authRepository.sendPasswordResetEmail(email, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                _resetStatus.setValue("Email khôi phục đã được gửi!");
            }

            @Override
            public void onFailure(String errorMessage) {
                _resetStatus.setValue("Lỗi: " + errorMessage);
            }
        });
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


}
