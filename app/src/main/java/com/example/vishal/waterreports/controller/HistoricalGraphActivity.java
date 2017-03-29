package com.example.vishal.waterreports.controller;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.vishal.waterreports.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HistoricalGraphActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String type;
    private String month;

    private Button backButton;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_graph);

        Intent i = getIntent();
        type = i.getStringExtra("data");
        month = i.getStringExtra("month");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        backButton = (Button) findViewById(R.id.backButton);
        graph = (GraphView) findViewById(R.id.graph);
        populateGraph();
        backButton.setOnClickListener(this);




    }

    /**
     * Populates the graph with data points from the model
     */
    private void populateGraph() {
        final ArrayList<Point> list = getDataPoints();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DataPoint[] arr = new DataPoint[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            arr[i] = new DataPoint(list.get(i).x, list.get(i).y);
                        }
                        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(arr);

                        graph.getViewport().setYAxisBoundsManual(true);
                        graph.getViewport().setMinY(-10);
                        graph.getViewport().setMaxY(3000);

                        graph.getViewport().setXAxisBoundsManual(true);
                        graph.getViewport().setMinX(-2);
                        graph.getViewport().setMaxX(32);

                        graph.addSeries(series);
                    }
                });
            }
        }, 10000);
    }


    private ArrayList<Point> getDataPoints() {
        final ArrayList<Point> data = new ArrayList<>();
        final int[] numReports = new int[1];
        databaseReference.child("uniqueNumberQual").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numReports[0] = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (type.equals("VirusPPM")) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < numReports[0]; i++) {
                                final String[] date = new String[2];
                                databaseReference.child("QualityReports").child(Integer.toString(i)).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        date[0] = dataSnapshot.child("REPORT_DATE").getValue(String.class);
                                        date[1] = dataSnapshot.child("VIRUS").getValue().toString();
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
                                                if (date[0].substring(0, 2).equals("03")) {
                                                    data.add(new Point(Integer.parseInt(date[0].substring(3, 5)), Integer.parseInt(date[1])));
                                                }
                                            }
                                        });
                                    }
                                }, 1000);
                            }
                        }
                    });
                }
            }, 1000);
        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < numReports[0]; i++) {
                                final String[] date = new String[2];
                                databaseReference.child("QualityReports").child(Integer.toString(i)).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        date[0] = dataSnapshot.child("REPORT_DATE").getValue(String.class);
                                        date[1] = dataSnapshot.child("CONTAMINANT").getValue().toString();
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
                                                if (date[0].substring(0, 2).equals("03")) {
                                                    data.add(new Point(Integer.parseInt(date[0].substring(3, 5)), Integer.parseInt(date[1])));
                                                }
                                            }
                                        });
                                    }
                                }, 1000);
                            }
                        }
                    });
                }
            }, 1000);
        }

        return data;
    }

    @Override
    public void onClick(View view) {
        if (view == backButton) {
            finish();
            startActivity(new Intent(HistoricalGraphActivity.this, ProfileActivity.class));
        }
    }
}
