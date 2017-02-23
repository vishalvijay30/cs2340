package com.example.vishal.waterreports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private TextView textViewEmail;
    private EditText editTextName;
    private EditText editTextHomeAddress;
    private TextView textViewAccountType;

    private Button okButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        textViewEmail = (TextView) findViewById(R.id.TextViewEmail);
        databaseReference.child(user.getUid()).child("EMAIL").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewEmail.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        editTextName = (EditText) findViewById(R.id.EditTextName);
        databaseReference.child(user.getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextName.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        editTextHomeAddress = (EditText) findViewById(R.id.EditTextHomeAddress);
        databaseReference.child(user.getUid()).child("homeAddress").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextHomeAddress.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        textViewAccountType = (TextView) findViewById(R.id.TextViewAccountType);
        databaseReference.child(user.getUid()).child("accountType").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewAccountType.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        okButton = (Button) findViewById(R.id.profileEditOk);
        cancelButton = (Button) findViewById(R.id.profileEditCancel);

        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == okButton) {
            updateName(editTextName.getText().toString().trim());
            updateHomeAddress(editTextHomeAddress.getText().toString().trim());
            finish();
            startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
        }

        if (view == cancelButton) {
            finish();
            startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
        }
    }

    private void updateName(String newName) {
        databaseReference.child(user.getUid()).child("name").setValue(newName);
    }

    private void updateHomeAddress(String newAddress) {
        databaseReference.child(user.getUid()).child("homeAddress").setValue(newAddress);
    }
}
