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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private TextView textViewEmail;
    private Button editProfile;
    private Button buttonLogout;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        editProfile = (Button) findViewById(R.id.profileButton);

        textViewEmail = (TextView) findViewById(R.id.textView4);
        databaseReference.child(user.getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewEmail.setText("Hello "+dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        buttonLogout = (Button) findViewById(R.id.profileButtonLogout);

        editProfile.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            progressDialog.setMessage("Logging Out...");
            progressDialog.show();
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        }

        if (view == editProfile) {
            finish();
            startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
        }
    }
}
