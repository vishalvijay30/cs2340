package com.example.vishal.waterreports.controller;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vishal.waterreports.R;
import com.example.vishal.waterreports.model.ConditionOfWater;
import com.example.vishal.waterreports.model.TypeOfWater;
import com.example.vishal.waterreports.model.WaterSourceReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private int numReports;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Intent i = getIntent();
        numReports = i.getIntExtra("num", 0);
        System.out.println("Hello " +numReports);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final ArrayList<WaterSourceReport> reports = loadInfo();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateMap(reports);
                    }
                });
            }
        }, 10000);
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    private ArrayList<WaterSourceReport> loadInfo() {
        final ArrayList<WaterSourceReport> reports = new ArrayList<>(numReports);
        for (int i = 0; i < numReports; i++) {
            //System.out.println("position: "+pos[0]);
            databaseReference.child("Reports").child(Integer.toString(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    reports.add(new WaterSourceReport(
                            dataSnapshot.child("REPORT_DATE").getValue().toString(),
                            dataSnapshot.child("REPORT_TIME").getValue().toString(),
                            Integer.parseInt(dataSnapshot.child("REP_NUMBER").getValue().toString()),
                            dataSnapshot.child("REPORTER_NAME").getValue().toString(),
                            dataSnapshot.child("LOCATION").getValue().toString(),
                            determineWaterType(dataSnapshot.child("WATER_TYPE").getValue().toString()),
                            determineWaterCondition(dataSnapshot.child("WATER_CONDITION").getValue().toString())
                    ));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return reports;
    }

    private TypeOfWater determineWaterType(String type) {
        if (type.equals("BOTTLED")) {
            return TypeOfWater.BOTTLED;
        } else if (type.equals("WELL")) {
            return TypeOfWater.WELL;
        } else if (type.equals("STREAM")) {
            return TypeOfWater.STREAM;
        } else if (type.equals("LAKE")) {
            return TypeOfWater.LAKE;
        } else if (type.equals("SPRING")) {
            return TypeOfWater.SPRING;
        } else {
            return TypeOfWater.OTHER;
        }
    }

    private ConditionOfWater determineWaterCondition(String condition) {
        if (condition.equals("WASTE")) {
            return ConditionOfWater.WASTE;
        } else if (condition.equals("TREATABLECLEAR")) {
            return ConditionOfWater.TREATABLECLEAR;
        } else if (condition.equals("TREATABLEMUDDY")) {
            return ConditionOfWater.TREATABLEMUDDY;
        } else {
            return ConditionOfWater.POTABLE;
        }
    }

    private void populateMap(ArrayList<WaterSourceReport> reports) {
        for (WaterSourceReport report : reports) {
            System.out.println("reached");
            List<Address> addressList = null;
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(report.LOCATION, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng loc = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).title("Report #"+report.REP_NUMBER).snippet(report.toString()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            System.out.println("monkey: "+report);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonBack) {
            finish();
            startActivity(new Intent(MapsActivity.this, ProfileActivity.class));
        }
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View myContentsView;

        CustomInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
