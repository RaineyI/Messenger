package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";
    private EditText editTextEmail;
    private Button buttonToResetPassword;
    private ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initViews();
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        editTextEmail.setText(email);

        viewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        buttonToResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(
                            ResetPasswordActivity.this,
                            "Fill in your Email",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    viewModel.sendPasswordResetEmail(email);
                    Toast.makeText(
                            ResetPasswordActivity.this,
                            "A letter to change your password has been sent to your email address",
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent intent = LoginActivity.newIntent(ResetPasswordActivity.this);
                    startActivity(intent);
                }
            }
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonToResetPassword = findViewById(R.id.buttonToResetPassword);
    }

    public static Intent newIntent(Context context, String email) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}