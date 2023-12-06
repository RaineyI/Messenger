package com.example.messenger;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordViewModel extends AndroidViewModel {
    private FirebaseAuth auth;



    public ResetPasswordViewModel(@NonNull Application application) {
        super(application);
    }


    public void sendPasswordResetEmail(String email) {
        auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email);
    }



}
