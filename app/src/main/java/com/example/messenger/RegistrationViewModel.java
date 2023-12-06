package com.example.messenger;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isCreated = new MutableLiveData<>();

    private FirebaseAuth auth;
    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getIsCreated() {
        return isCreated;
    }

    public void createUser(String email, String password) {
        auth = FirebaseAuth.getInstance();
        //FirebaseUser user = auth.getCurrentUser();
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                isCreated.setValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isCreated.setValue(false);
            }
        });
    }
}
