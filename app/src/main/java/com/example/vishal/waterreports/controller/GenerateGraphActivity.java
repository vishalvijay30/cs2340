package com.example.vishal.waterreports.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.vishal.waterreports.R;

import java.util.ArrayList;
import java.util.List;

public class GenerateGraphActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner ppmSpinner;
    private Spinner monthSpinner;
    private Button buttonGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_graph);

        ppmSpinner = (Spinner) findViewById(R.id.ppmSpinner);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);

        populatePPMSpinner();
        populateMonthSpinner();

        buttonGenerate = (Button) findViewById(R.id.buttonGen);
        buttonGenerate.setOnClickListener(this);
    }

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
            System.out.println("harambe "+dataType);
            String month = (String) monthSpinner.getSelectedItem();
            Intent myIntent = new Intent(GenerateGraphActivity.this, HistoricalGraphActivity.class);
            myIntent.putExtra("data", dataType);
            myIntent.putExtra("month", month);
            startActivity(myIntent);
        }
    }
}
