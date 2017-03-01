package com.example.vishal.waterreports.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vishal.waterreports.R;
import com.example.vishal.waterreports.model.ConditionOfWater;
import com.example.vishal.waterreports.model.TypeOfWater;
import com.example.vishal.waterreports.model.WaterSourceReport;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubmitWaterSourceReportActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private EditText editTextDate;
    private EditText editTextTime;
    private TextView textViewReportNumber;
    private TextView textViewReporterName;
    private EditText editTextWaterLocation;
    private Button buttonSubmit;
    private Button buttonCancel;

    private Spinner waterTypeSpinner;
    private Spinner waterConditionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_water_source_report);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        editTextDate = (EditText) findViewById(R.id.DateTextView);
        editTextTime = (EditText) findViewById(R.id.TimeTextView);
        textViewReportNumber = (TextView) findViewById(R.id.ReportNumberTextView);
        textViewReporterName = (TextView) findViewById(R.id.ReporterNameTextView);
        editTextWaterLocation = (EditText) findViewById(R.id.LocationEditText);
        buttonSubmit = (Button) findViewById(R.id.SubmitButton);
        buttonCancel = (Button) findViewById(R.id.CancelButton);

        waterTypeSpinner = (Spinner) findViewById(R.id.WaterTypeSpinner);
        waterConditionSpinner = (Spinner) findViewById(R.id.WaterConditionSpinner);

        buttonSubmit.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        //textViewReportNumber.setText("Report Number: " + Integer.toString(++WaterSourceReport.REPORT_NUMBER));

        databaseReference.child("uniqueNumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReportNumber.setText("Report Number: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child(user.getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewReporterName.setText("Reporter Name: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        populateWaterTypeSpinner();
        populateWaterConditionSpinner();
    }

    /**
     * Populates Water Type spinner with WaterType enum values
     */
    private void populateWaterTypeSpinner() {
        List<TypeOfWater> list = new ArrayList<>();
        list.add(TypeOfWater.BOTTLED);
        list.add(TypeOfWater.WELL);
        list.add(TypeOfWater.STREAM);
        list.add(TypeOfWater.LAKE);
        list.add(TypeOfWater.SPRING);
        list.add(TypeOfWater.OTHER);
        ArrayAdapter<TypeOfWater> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterTypeSpinner.setAdapter(dataAdapter);
    }

    /**
     * Populates Water Condition spinner with WaterCondition enum values
     */
    private void populateWaterConditionSpinner() {
        List<ConditionOfWater> list = new ArrayList<>();
        list.add(ConditionOfWater.WASTE);
        list.add(ConditionOfWater.TREATABLECLEAR);
        list.add(ConditionOfWater.TREATABLEMUDDY);
        list.add(ConditionOfWater.POTABLE);
        ArrayAdapter<ConditionOfWater> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterConditionSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSubmit) {
            addWaterReport();
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }

        if (view == buttonCancel) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    /**
     * Creates new Water Report from provided information and adds the report to Firebase
     */
    private void addWaterReport() {
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        String reporterName = textViewReporterName.getText().toString().trim().substring(15);
        String location = editTextWaterLocation.getText().toString().trim();
        TypeOfWater waterType = (TypeOfWater) waterTypeSpinner.getSelectedItem();
        ConditionOfWater waterCondition = (ConditionOfWater) waterConditionSpinner.getSelectedItem();

        int repNum = Integer.parseInt(textViewReportNumber.getText().toString().substring(textViewReportNumber.length()-1));
        databaseReference.child("uniqueNumber").setValue(repNum + 1);

        WaterSourceReport report = new WaterSourceReport(date, time, repNum,
                reporterName, location, waterType, waterCondition);

        databaseReference.child("Reports").child(Integer.toString(repNum)).setValue(report);
    }
}
