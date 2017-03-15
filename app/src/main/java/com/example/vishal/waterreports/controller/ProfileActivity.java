package com.example.vishal.waterreports.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishal.waterreports.R;
import com.example.vishal.waterreports.model.WaterSourceReport;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private TextView textViewEmail;
    private Button editProfile;
    private Button submitReportButton;
    private Button buttonLogout;
    private Button buttonViewAllReports;
    private Button buttonMap;
    private Button submitWaterQualityReportButton;
    private Button buttonViewAllQualReports;

    private ProgressDialog progressDialog;

    private int numReports;
    private int numQualReports;

    private WaterSourceReport[] waterSourceReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        editProfile = (Button) findViewById(R.id.profileButton);

        submitReportButton = (Button) findViewById(R.id.submitWaterReportButton);

        textViewEmail = (TextView) findViewById(R.id.textView4);
        databaseReference.child(user.getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewEmail.setText("Hello " + dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("uniqueNumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numReports = dataSnapshot.getValue(Integer.class);
                System.out.println("number"+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("uniqueNumberQual").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numQualReports = dataSnapshot.getValue(Integer.class);
                System.out.println("number"+dataSnapshot.getValue());
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
        buttonViewAllReports = (Button) findViewById(R.id.viewWaterReportsButton);
        buttonMap = (Button) findViewById(R.id.viewWaterReportsButtonMap);
        submitWaterQualityReportButton = (Button) findViewById(R.id.submitWaterQualityReportButton);
        buttonViewAllQualReports = (Button) findViewById(R.id.viewWaterPurityReportsButton);

        editProfile.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
        submitReportButton.setOnClickListener(this);
        buttonViewAllReports.setOnClickListener(this);
        buttonMap.setOnClickListener(this);
        submitWaterQualityReportButton.setOnClickListener(this);
        buttonViewAllQualReports.setOnClickListener(this);
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

        if (view == submitReportButton) {
            finish();
            startActivity(new Intent(ProfileActivity.this, SubmitWaterSourceReportActivity.class));
        }

        if (view == buttonViewAllReports) {
            finish();
            Intent myIntent = new Intent(ProfileActivity.this, AllReportsActivity.class);
            myIntent.putExtra("number", numReports);
            startActivity(myIntent);
        }

        if (view == buttonViewAllQualReports) {
            finish();
            Intent myIntent = new Intent(ProfileActivity.this, AllQualReportsActivity.class);
            myIntent.putExtra("number", numQualReports);
            startActivity(myIntent);
        }

        if (view == buttonMap) {
            finish();
            Intent myIntent = new Intent(ProfileActivity.this, MapsActivity.class);
            myIntent.putExtra("num", numReports);
            startActivity(myIntent);
        }

        if (view == submitWaterQualityReportButton) {
            final String[] accountType = new String[1];
            databaseReference.child(user.getUid()).child("accountType").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    accountType[0] = dataSnapshot.getValue(String.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (accountType[0].equals("USER")) {
                                Toast.makeText(getApplicationContext(),
                                        "Not authorized to submit Quality Report", Toast.LENGTH_LONG).show();
                            } else {
                                finish();
                                startActivity(new Intent(ProfileActivity.this,
                                        SubmitWaterQualityReportActivity.class));
                            }
                        }
                    });
                }
            }, 1000);
        }
    }

}
