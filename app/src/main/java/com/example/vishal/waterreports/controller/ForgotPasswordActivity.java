package com.example.vishal.waterreports;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailText;
    private Button sendEmailButton;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(ForgotPasswordActivity.this);

        emailText = (EditText) findViewById(R.id.editTextForgotPasswordEmail);
        sendEmailButton = (Button) findViewById(R.id.buttonReset);

        sendEmailButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == sendEmailButton) {
            resetPassword();
        }
    }

    /**
     * Method provides email password recovery functionality
     * in the event that they have forgotten their password
     */
    private void resetPassword() {
        String email = emailText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty, stop execution
            Toast.makeText(ForgotPasswordActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Sending email...");
        progressDialog.show();

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.hide();
                            Toast.makeText(ForgotPasswordActivity.this, "Email Sent!", Toast.LENGTH_LONG).show();
                        } else {
                            progressDialog.hide();
                            Toast.makeText(ForgotPasswordActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
