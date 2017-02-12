package com.example.vishal.waterreports;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewEmail;
    private Button buttonLogout;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewEmail = (TextView) findViewById(R.id.textView4);
        textViewEmail.setText("Hello " + user.getEmail());
        buttonLogout = (Button) findViewById(R.id.profileButtonLogout);

        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        progressDialog.setMessage("Logging Out...");
        progressDialog.show();
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
    }
}
