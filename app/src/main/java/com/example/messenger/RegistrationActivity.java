package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private Button buttonRegistration;
    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);

        viewModel.getIsCreated().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isCreated) {
                if(isCreated) {
                    Toast.makeText(
                            RegistrationActivity.this,
                            "You have been registered!",
                            Toast.LENGTH_SHORT
                    ).show();
                    goToUsersActivity();
                } else {
                    Toast.makeText(
                            RegistrationActivity.this,
                            "Something was wrong",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getTrimmedValue(editTextEmail);
                String password = getTrimmedValue(editTextPassword);
                String name = getTrimmedValue(editTextName);
                String lastName = getTrimmedValue(editTextLastName);
                int age = Integer.parseInt(getTrimmedValue(editTextAge));

                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                            RegistrationActivity.this,
                            "Fill in email & password",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    viewModel.createUser(email, password);
                }
            }
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonRegistration = findViewById(R.id.buttonRegistration);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }

    private void goToUsersActivity() {
        Intent intent = UsersActivity.newIntent(RegistrationActivity.this);
        startActivity(intent);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }
}