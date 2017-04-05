package com.example.vishal.waterreports.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vishal.waterreports.R;

import java.util.ArrayList;
import java.util.List;

public class GenerateGraphActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText locationEditText;
    private Spinner ppmSpinner;
    private Spinner monthSpinner;
    private Button buttonGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_graph);

        locationEditText = (EditText) findViewById(R.id.locationEditText);
        ppmSpinner = (Spinner) findViewById(R.id.ppmSpinner);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);


        populatePPMSpinner();
        populateMonthSpinner();

        buttonGenerate = (Button) findViewById(R.id.buttonGen);
        buttonGenerate.setOnClickListener(this);
    }

//    /**
//     * Populate location spinner with locations for which water reports exist
//     */
//    private void populateLocationSpinner() {
//        final List<String> list = new ArrayList<>();
//        //list.add("Atlanta, GA");
//        final int[] num = new int[1];
//        databaseReference.child("uniqueNumberQual").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                num[0] = dataSnapshot.getValue(Integer.class);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < num[0]; i++) {
//                            databaseReference.child("QualityReports").child(Integer.toString(i)).addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    if (!list.contains(dataSnapshot.child("LOCATION").getValue(String.class))) {
//                                        list.add(dataSnapshot.child("LOCATION").getValue(String.class));
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });
//                        }
//                    }
//                });
//            }
//        }, 100);
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        locationSpinner.setAdapter(dataAdapter);
//    }

    /**
     * Populate PPM type spinner with data values
     */
    private void populatePPMSpinner() {
        List<String> list = new ArrayList<>();
        list.add("VirusPPM");
        list.add("ContaminantPPM");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ppmSpinner.setAdapter(dataAdapter);
    }

    /**
     * Populate spinner with months of the year
     */
    private void populateMonthSpinner() {
        List<String> list = new ArrayList<>();
        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("August");
        list.add("September");
        list.add("October");
        list.add("November");
        list.add("December");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonGenerate) {
            String dataType = (String) ppmSpinner.getSelectedItem();
            String month = (String) monthSpinner.getSelectedItem();
            String location = locationEditText.getText().toString().trim();
            Intent myIntent = new Intent(GenerateGraphActivity.this, HistoricalGraphActivity.class);
            myIntent.putExtra("data", dataType);
            myIntent.putExtra("month", month);
            myIntent.putExtra("location", location);
            startActivity(myIntent);
        }
    }
}
