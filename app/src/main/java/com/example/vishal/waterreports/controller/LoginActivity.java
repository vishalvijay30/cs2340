package com.example.vishal.waterreports.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishal.waterreports.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;


    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRegister;
    private Button buttonCancel;
    private TextView textViewForgotPassword;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(LoginActivity.this);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        textViewRegister = (TextView) findViewById(R.id.textViewLoginRegister);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        textViewForgotPassword = (TextView) findViewById(R.id.textViewLoginForgotPassword);

        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
    }

    /**
     * Authenticates user credentials on firebase before logging in the user
     */
    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty, stop execution
            Toast.makeText(LoginActivity.this, "Please enter valid email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty, stop execution
            Toast.makeText(LoginActivity.this, "Please enter valid password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        } else {
                            progressDialog.hide();
                            Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin) {
            userLogin();
        }

        if (view == textViewRegister) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }

        if (view == buttonCancel) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        if (view == textViewForgotPassword) {
            finish();
            startActivity(new Intent(this, ForgotPasswordActivity.class));
        }
    }
}
