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
import android.widget.Toast;

import com.example.vishal.waterreports.R;
import com.example.vishal.waterreports.model.OverallWaterCondition;
import com.example.vishal.waterreports.model.WaterPurityReport;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubmitWaterQualityReportActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference databaseReference;

    private EditText editTextDate;
    private EditText editTextTime;
    private TextView textViewReportNumber;
    private TextView textViewReporterName;
    private EditText editTextLocation;
    private Spinner overallConditionSpinner;
    private EditText editTextVirusPPM;
    private EditText editTextContaminantPPM;

    private Button submit;
    private Button cancel;

    private boolean didAddQualityReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_report);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        didAddQualityReport = true;

        editTextDate = (EditText) findViewById(R.id.DateTextView);
        editTextTime = (EditText) findViewById(R.id.TimeTextView);
        textViewReportNumber = (TextView) findViewById(R.id.ReportNumberTextView);
        textViewReporterName = (TextView) findViewById(R.id.ReporterNameTextView);
        editTextLocation = (EditText) findViewById(R.id.LocationEditText);
        overallConditionSpinner = (Spinner) findViewById(R.id.OverallConditionSpinner);
        editTextVirusPPM = (EditText) findViewById(R.id.VirusPPMEditText);
        editTextContaminantPPM = (EditText) findViewById(R.id.ContaminantPPMEditText);

        submit = (Button) findViewById(R.id.SubmitButton);
        cancel = (Button) findViewById(R.id.CancelButton);

        databaseReference.child("uniqueNumberQual").addValueEventListener(new ValueEventListener() {
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

        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);

        populateOverallConditionSpinner();
    }

    /**
     * Populates Overall Water Condition spinner with OverallWaterCondition enum values
     */
    private void populateOverallConditionSpinner() {
        List<OverallWaterCondition> list = new ArrayList<>();
        list.add(OverallWaterCondition.SAFE);
        list.add(OverallWaterCondition.TREATABLE);
        list.add(OverallWaterCondition.UNSAFE);

        ArrayAdapter<OverallWaterCondition> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overallConditionSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == submit) {
            addQualityReport();
            if (didAddQualityReport) {
                finish();
                startActivity(new Intent(SubmitWaterQualityReportActivity.this, ProfileActivity.class));
            }
        }

        if (view == cancel) {
            finish();
            startActivity(new Intent(SubmitWaterQualityReportActivity.this, ProfileActivity.class));
        }
    }

    /**
     * Creates new Water Quality Report from provided information and adds the report to Firebase
     */
    private void addQualityReport() {
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        String reporterName = textViewReporterName.getText().toString().trim().substring(15);
        String location = editTextLocation.getText().toString().trim();
        OverallWaterCondition condition = (OverallWaterCondition) overallConditionSpinner.getSelectedItem();
        if (date.isEmpty() || time.isEmpty() || reporterName.isEmpty() || location.isEmpty() ||
                condition == null || editTextVirusPPM.getText().toString().isEmpty() ||
                editTextContaminantPPM.getText().toString().isEmpty()) {
            Toast.makeText(SubmitWaterQualityReportActivity.this, "One or more fields is empty", Toast.LENGTH_LONG).show();
            didAddQualityReport = false;
            return;
        }
        int virus = Integer.parseInt(editTextVirusPPM.getText().toString().trim());
        int contam = Integer.parseInt(editTextContaminantPPM.getText().toString().trim());


        int repNum = Integer.parseInt(textViewReportNumber.getText().toString().substring(textViewReportNumber.length()-1));
        databaseReference.child("uniqueNumberQual").setValue(repNum + 1);

        WaterPurityReport report = new WaterPurityReport(date, time, repNum, reporterName, location, condition, virus, contam);

        databaseReference.child("QualityReports").child(Integer.toString(repNum)).setValue(report);
    }
}
