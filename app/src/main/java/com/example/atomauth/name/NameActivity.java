package com.example.atomauth.name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.atomauth.MainActivity;
import com.example.atomauth.R;
import com.example.atomauth.utils.SharedPrefs;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NameActivity extends AppCompatActivity {
    TextInputEditText textInputEditText;
    Button SubmitButton;
    NameViewModel nameViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        textInputEditText = (TextInputEditText)findViewById(R.id.name_edit_text);
        SubmitButton = (Button)findViewById(R.id.button_submit_name);
        nameViewModel = new ViewModelProvider(this).get(NameViewModel.class);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textInputEditText.getText().toString().trim();
                if(name.length()>0){
                    nameViewModel.saveName(name);
                    Intent intent = new Intent(NameActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    textInputEditText.setError("Please fill your name");
                }
            }
        });
    }
}