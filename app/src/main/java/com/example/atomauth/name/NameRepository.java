package com.example.atomauth.name;

import android.app.Application;
import android.util.Log;

import com.example.atomauth.utils.SharedPrefs;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NameRepository {
    Application application;
    SharedPrefs sharedPrefs;
    private FirebaseFirestore db;
    Map<String, Object> user;

    public NameRepository(Application application) {
        this.application = application;
        this.sharedPrefs = new SharedPrefs(application);
        db = FirebaseFirestore.getInstance();
        user = new HashMap<>();
    }

    public void saveName(String name) {
        String UserId = sharedPrefs.getUserId();
        saveNametoFireStore(name, UserId);
    }
    private void saveNametoFireStore(String name, String userId) {
        sharedPrefs.saveIsLoggedIn(true);
        sharedPrefs.saveFullName(name);
        user.put("userName", name);
        if(userId==null){
            userId="anonymous";
        }
        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.v("TAG", "User data saved to firestore");
                    }
                });
    }
}
