package com.example.vishal.waterreports.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.vishal.waterreports.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class HistoricalGraphActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backButton;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_graph);

        backButton = (Button) findViewById(R.id.backButton);
        graph = (GraphView) findViewById(R.id.graph);
        populateGraph();
        backButton.setOnClickListener(this);
    }

    /**
     * Populates the graph with data points from the model
     */
    private void populateGraph() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }

    @Override
    public void onClick(View view) {
        if (view == backButton) {
            finish();
            startActivity(new Intent(HistoricalGraphActivity.this, ProfileActivity.class));
        }
    }
}
