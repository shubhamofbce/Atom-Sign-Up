package com.example.atomauth.name;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.atomauth.login.LoginRepository;
import com.example.atomauth.login.User;
import com.google.firebase.auth.AuthCredential;

public class NameViewModel extends AndroidViewModel {
    private NameRepository nameRepository;
    LiveData<User> userLiveData;
    public NameViewModel(@NonNull Application application) {
        super(application);
        nameRepository = new NameRepository(application);
    }
    public void saveName(String name){
        nameRepository.saveName(name);
    }

}
