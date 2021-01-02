package com.example.atomauth.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.atomauth.MainActivity;
import com.example.atomauth.name.NameActivity;
import com.example.atomauth.R;
import com.example.atomauth.utils.SharedPrefs;
import com.example.atomauth.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private int RC_SIGN_IN = 13;
    private GoogleSignInClient mGoogleSignInClient;
    private ActivityLoginBinding mBinding;
    SharedPrefs sharedPrefs;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        sharedPrefs = new SharedPrefs(this);
        initGoogleSignInClient();

        mBinding.buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        mBinding.buttonSigninGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAsGuest();
            }
        });
    }

    private void signInAsGuest() {
        Intent intent = new Intent(LoginActivity.this, NameActivity.class);
        startActivity(intent);
        finish();
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    private void signIn() {
        showProgressBar();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
                if (googleSignInAccount != null) {
                    getGoogleAuthCredential(googleSignInAccount);
                }
            } catch (ApiException e) {
                Log.e("TAG", e.getMessage());
            }
        }
    }

    private void getGoogleAuthCredential(GoogleSignInAccount googleSignInAccount) {
        String googleTokenId = googleSignInAccount.getIdToken();
        AuthCredential googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null);
        signInWithGoogleAuthCredential(googleAuthCredential);
    }

    private void signInWithGoogleAuthCredential(AuthCredential googleAuthCredential) {
        loginViewModel.signInWithGoogle(googleAuthCredential);
        loginViewModel.userLiveData.observe(this, authenticatedUser -> {
            if (authenticatedUser != null) {
                goToMainActivity(authenticatedUser);
            }
        });
    }

    private void goToMainActivity(User authenticatedUser) {
        hideProgressBar();
        Intent intent;
        if(!authenticatedUser.isNewuser()) {
            intent = new Intent(LoginActivity.this, MainActivity.class);
        }
        else{
            intent = new Intent(LoginActivity.this, NameActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void hideProgressBar() {
        mBinding.progressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

}