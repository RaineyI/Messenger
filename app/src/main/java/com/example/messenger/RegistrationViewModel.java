package com.example.messenger;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationViewModel extends ViewModel {
    private final FirebaseAuth auth;
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<FirebaseUser> user = new MutableLiveData<>();
    private final MutableLiveData<Boolean> sent = new MutableLiveData<>();
    public RegistrationViewModel() {
        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    FirebaseUser fUser = auth.getCurrentUser();
                    if (fUser.isEmailVerified()) {
                        user.setValue(firebaseAuth.getCurrentUser());
                        sent.setValue(false);
                    } else {
                        assert fUser != null;
                        fUser.sendEmailVerification();
                        sent.setValue(true);
                    }
                }
            }
        });
    }

            public LiveData<String> getError() {
                return error;
            }

            public LiveData<FirebaseUser> getUser() {
                return user;
            }

            public LiveData<Boolean> wasSent() {
                return sent;
            }

            public void singUp(
                    String email,
                    String password,
                    String name,
                    String lastName,
                    int age
            ) {
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                error.setValue(e.getMessage());
                            }
                        });
            }
}
