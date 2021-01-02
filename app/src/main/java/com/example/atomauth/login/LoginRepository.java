package com.example.atomauth.login;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.atomauth.utils.SharedPrefs;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginRepository {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    SharedPrefs sharedPrefs;

    public LoginRepository(Application application) {
        sharedPrefs = new SharedPrefs(application);
    }

    MutableLiveData<User> firebaseSignInWithGoogle(AuthCredential googleAuthCredential) {
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();
        firebaseAuth.signInWithCredential(googleAuthCredential).addOnCompleteListener(authTask -> {
            if (authTask.isSuccessful()) {
                boolean isNewUser = authTask.getResult().getAdditionalUserInfo().isNewUser();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    User user = new User(firebaseUser, isNewUser);
                    authenticatedUserMutableLiveData.setValue(user);
                    saveData(firebaseUser);
                }
            } else {
                Log.e("TAG", "Error in Repository sign in");
            }
        });
        return authenticatedUserMutableLiveData;
    }

    private void saveData(FirebaseUser authenticatedUser) {
        sharedPrefs.saveEmail(authenticatedUser.getEmail());
        sharedPrefs.saveFullName(authenticatedUser.getDisplayName());
        sharedPrefs.saveUserId(authenticatedUser.getUid());
        sharedPrefs.saveIsLoggedIn(true);
    }

}
