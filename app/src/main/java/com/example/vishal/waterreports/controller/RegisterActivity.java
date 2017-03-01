package com.example.vishal.waterreports.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishal.waterreports.R;
import com.example.vishal.waterreports.model.AccountType;
import com.example.vishal.waterreports.model.Actor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private TextView textViewLogin;
    private Spinner spinnerAccountType;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        spinnerAccountType = (Spinner) findViewById(R.id.spinnerAccountType);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextRegisterEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextRegisterPassword);
        textViewLogin = (TextView) findViewById(R.id.textViewRegisterLogin);
        progressDialog = new ProgressDialog(RegisterActivity.this);

        buttonRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);

        addItemsToSpinner();
    }

    /**
     * Populates the spinner with AccountType enum values
     */
    private void addItemsToSpinner() {
        List<AccountType> list = new ArrayList<>();
        list.add(AccountType.USER);
        list.add(AccountType.WORKER);
        list.add(AccountType.MANAGER);
        list.add(AccountType.ADMIN);
        ArrayAdapter<AccountType> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccountType.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }

        if (view == textViewLogin) {
            //open login activity
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    /**
     * Registers user by saving user authentication credentials on firebase
     */
    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final AccountType accountTypeSelected = (AccountType) spinnerAccountType.getSelectedItem();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            //name is empty, stop execution
            Toast.makeText(RegisterActivity.this, "Please enter valid name", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            //email is empty, stop execution
            Toast.makeText(RegisterActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty, stop execution
            Toast.makeText(RegisterActivity.this, "Please enter valid password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUserInformation(name, accountTypeSelected, email);
                            finish();
                            startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_LONG).show();
                            progressDialog.hide();
                        }
                    }
                });

    }

    /**
     * Saves user's personal information on firebase
     *
     * @param userName the user's name
     * @param userAccountType the user's account type
     * @param userEmail the user's email
     */
    private void saveUserInformation(String userName, AccountType userAccountType, String userEmail) {
        Actor actor = new Actor(userName, userEmail, "", userAccountType);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(actor);
    }
}
