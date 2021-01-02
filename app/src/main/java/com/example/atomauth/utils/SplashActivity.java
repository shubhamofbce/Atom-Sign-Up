package com.example.atomauth.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.atomauth.MainActivity;
import com.example.atomauth.R;
import com.example.atomauth.databinding.ActivitySplashBinding;
import com.example.atomauth.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final SharedPrefs sharedPrefs = new SharedPrefs(SplashActivity.this);
        final ImageView rotatingLogo = binding.logoImage;
        final Animation rotateAnimation  = AnimationUtils.loadAnimation(getBaseContext(),
                R.anim.push_left_in);
        final Animation fadeOutAnimation =
                AnimationUtils.loadAnimation(getBaseContext(), R.anim.push_left_in);

        rotatingLogo.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rotatingLogo.startAnimation(fadeOutAnimation);
                Boolean isUserLoggedIn = sharedPrefs.getIsLoggedIn();
                if (!isUserLoggedIn) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}