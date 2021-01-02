package com.example.atomauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.atomauth.databinding.ActivityMainBinding;
import com.example.atomauth.utils.SharedPrefs;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private SharedPrefs sharedPrefs;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        sharedPrefs = new SharedPrefs(this);
        String name = sharedPrefs.getfullName();
        mBinding.status.setText("Welcome "+name);
    }
}