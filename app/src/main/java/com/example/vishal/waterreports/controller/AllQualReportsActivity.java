package com.example.vishal.waterreports.controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vishal.waterreports.R;

@SuppressWarnings("FieldCanBeLocal")
public class AllQualReportsActivity extends ListActivity implements View.OnClickListener {

    private TextView numSysReports;

    private ListView lv;
    private Button cancelButton;
    private int numReps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_qual_reports);

        numSysReports = (TextView) findViewById(R.id.numReports);
        Intent i = getIntent();
        numReps = i.getIntExtra("number", 0);
        numSysReports.setText("Number of Reports in System: " + numReps);

        int sizeNum = numReps;
        String[] list = new String[sizeNum];
        for (int j = 0; j < sizeNum; j++) {
            list[j] = "Purity Report: "+Integer.toString(j);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.activity_list_item, android.R.id.text1, list);
        this.setListAdapter(adapter);
        lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent reportIntent = new Intent(AllQualReportsActivity.this, SingleQualReportActivity.class);
                reportIntent.putExtra("position",i);
                finish();
                startActivity(reportIntent);
            }
        });

        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setText("Back");
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == cancelButton) {
            finish();
            startActivity(new Intent(AllQualReportsActivity.this, ProfileActivity.class));
        }
    }
}
