package com.example.atomauth.login;

import com.google.firebase.auth.FirebaseUser;

public class User {
    private FirebaseUser user;
    private boolean isNewuser;

    public User(FirebaseUser user, boolean isNewuser) {
        this.user = user;
        this.isNewuser = isNewuser;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public boolean isNewuser() {
        return isNewuser;
    }

    public void setNewuser(boolean newuser) {
        isNewuser = newuser;
    }
}
