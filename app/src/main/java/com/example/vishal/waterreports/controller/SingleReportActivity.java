package com.example.vishal.waterreports.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vishal.waterreports.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressWarnings("ALL")
public class SingleReportActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private TextView textViewReportTitle;
    private TextView textViewReportInfo;
    private Button cancelButton;

    private Integer reportNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_report);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        textViewReportTitle = (TextView) findViewById(R.id.textViewReportTitle);
        textViewReportInfo = (TextView) findViewById(R.id.textViewReportInfo);

        Intent i = getIntent();
        reportNum = i.getIntExtra("position", 0);

        textViewReportTitle.setText("Report "+reportNum);
        displayIndividualReportInfo();
    }

    private void displayIndividualReportInfo() {

        databaseReference.child("Reports").child(reportNum.toString()).child("LOCATION").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReportInfo.setText(dataSnapshot.getValue().toString() + " has ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Reports").child(reportNum.toString()).child("WATER_TYPE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReportInfo.setText(textViewReportInfo.getText().toString() + dataSnapshot.getValue().toString() + " water that is ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Reports").child(reportNum.toString()).child("WATER_CONDITION").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReportInfo.setText(textViewReportInfo.getText().toString() + dataSnapshot.getValue().toString() + " (Report #");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Reports").child(reportNum.toString()).child("REP_NUMBER").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReportInfo.setText(textViewReportInfo.getText().toString() + dataSnapshot.getValue().toString() + " submitted by ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Reports").child(reportNum.toString()).child("REPORTER_NAME").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReportInfo.setText(textViewReportInfo.getText().toString() + dataSnapshot.getValue().toString() + " at ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Reports").child(reportNum.toString()).child("REPORT_TIME").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReportInfo.setText(textViewReportInfo.getText().toString() + dataSnapshot.getValue().toString() + " on ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Reports").child(reportNum.toString()).child("REPORT_DATE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReportInfo.setText(textViewReportInfo.getText().toString() + dataSnapshot.getValue().toString() + ")");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cancelButton = (Button) findViewById(R.id.backButton);
        cancelButton.setText("Back");
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == cancelButton) {
            finish();
            startActivity(new Intent(SingleReportActivity.this, ProfileActivity.class));
        }
    }
}