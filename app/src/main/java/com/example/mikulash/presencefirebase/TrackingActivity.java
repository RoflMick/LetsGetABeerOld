package com.example.mikulash.presencefirebase;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.mikulash.presencefirebase.Model.Tracking;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.function.DoubleUnaryOperator;

public class TrackingActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    private String email;

    DatabaseReference locations;

    Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locations = FirebaseDatabase.getInstance().getReference("Locations");

        if (getIntent() != null){
            email = getIntent().getStringExtra("email");
            latitude = getIntent().getDoubleExtra("latitude", 0);
            longitude = getIntent().getDoubleExtra("longitude", 0);
        }

        if (!TextUtils.isEmpty(email)){
            loadLocationForUser(email);
        }
    }

    private void loadLocationForUser(String email) {
        Query user_location = locations.orderByChild("email").equalTo(email);

        user_location.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Tracking tracking = snapshot.getValue(Tracking.class);

                    //Marker for friend
                    LatLng friendLatLng = new LatLng(Double.parseDouble(tracking.getLatitude()), Double.parseDouble(tracking.getLongitude()));

                    Location currentUser = new Location("");
                    currentUser.setLatitude(latitude);
                    currentUser.setLongitude(longitude);

                    Location friend = new Location("");
                    friend.setLatitude(Double.parseDouble(tracking.getLatitude()));
                    friend.setLongitude(Double.parseDouble(tracking.getLongitude()));

                    map.clear();

                    map.addMarker(new MarkerOptions().position(friendLatLng).title(tracking.getEmail())
                            .snippet("User is " + new DecimalFormat("#.#").format((currentUser.distanceTo(friend) / 1000)) + " km far away.")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 12.0f));

                    LatLng currentLatLng = new LatLng(latitude, longitude);
                    map.addMarker(new MarkerOptions().position(currentLatLng).title(FirebaseAuth.getInstance().getCurrentUser().getEmail() + " (me)"));

//                    calculateDistance(currentUser, friend);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private Location calculateDistance(Location currentUser, Location friend) {
//        double theta = currentUser.getLongitude() - friend.getLongitude();
//        double distance = Math.sin(degToRad(currentUser.getLatitude()));
//    }

//    private double degToRad(double degrees) {
//        return (degrees * Math.PI / 180);
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }
}
