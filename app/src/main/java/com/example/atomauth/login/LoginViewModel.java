package com.example.atomauth.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    LiveData<User> userLiveData;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository(application);
    }
    void signInWithGoogle(AuthCredential googleAuthCredential) {
        userLiveData = loginRepository.firebaseSignInWithGoogle(googleAuthCredential);
    }

}
