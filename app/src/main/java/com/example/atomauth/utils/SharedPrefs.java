package com.example.atomauth.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String myPrefs = "myPrefs";
    private static final String EmailKey = "emailId";
    private static final String FullNameKey = "fullName";
    private static final String TokenKey = "token";
    private static final String UserId = "userId";
    private static final String ExpiresOnKey = "expiresOn";
    private static final String isLoggedInKey = "isLoggedIn";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    public SharedPrefs(Context context) {
        sharedPreferences = context.getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //Save Email in sharedPref and get Email
    public void saveEmail(String email) {
        editor.putString(EmailKey, email);
        editor.commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(EmailKey, null);
    }


    //Save firstName and lastName as fullName and get fullName
    public void saveFullName(String firstName) {
        editor.putString(FullNameKey, firstName);
        editor.commit();
    }

    public String getfullName() {
        return sharedPreferences.getString(FullNameKey, null);
    }

    //Save Token and get Token when required
    public void saveToken(String token) {
        editor.putString(TokenKey, token);
        editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(TokenKey, null);
    }

    public void saveUserId(String userId) {
        editor.putString(UserId, userId);
        editor.commit();
    }


    public String getUserId() {
        return sharedPreferences.getString(UserId, null);
    }


    public void saveTokenExpiresOn(String ExpiresOn) {
        editor.putString(ExpiresOnKey, ExpiresOn);
        editor.commit();
    }


    public boolean getIsLoggedIn() {
        return sharedPreferences.getBoolean(isLoggedInKey, false);
    }

    public void saveIsLoggedIn(Boolean isLoggedIn){
        editor.putBoolean(isLoggedInKey, isLoggedIn);
        editor.commit();
    }

    public String getTokenExpiresOn() {
        return sharedPreferences.getString(ExpiresOnKey, null);
    }

    public void clearLogin() {
        editor.putString(EmailKey, null);
        editor.putString(FullNameKey, null);
        editor.putString(TokenKey, null);
        editor.putInt(UserId, 0);
        editor.commit();
    }
}
