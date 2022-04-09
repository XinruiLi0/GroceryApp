package com.example.groceryapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;


public class Map extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap map;
    private LocationManager locationManager;
    ImageButton back;
    private String storeID;
    private String storeName;
    private ArrayList<String> storeLatLng;

    private List<LatLng> list = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Extract store name from local
        Intent intent = getIntent();
        storeID = intent.getStringExtra("storeID");
        storeName = intent.getStringExtra("storeName");

        // Request store position from db
        ArrayList<ArrayList<String>> result = DBUtil.Query("select latitude, longitude from Retailers where id = "+storeID);
        storeLatLng = result.get(0);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);

        Button myLocation = (Button) findViewById(R.id.Mapshowlocation);
        myLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                LatLng curLocation = list.get(1);
                map.addMarker(new MarkerOptions()
                        .position(curLocation)
                        .title("Your location is here"));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocation, 12));
                PolylineOptions polyline_options = new PolylineOptions()
                        .addAll(list).color(Color.BLUE).width(10);

                Polyline polyline = map.addPolyline(polyline_options);

            }
        });
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go back
                Intent intent = new Intent(Map.this, GroceryStores.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        // mapFragment.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    @Override
    protected void onPause() {
        //mMapView.onPause();
        super.onPause();
        locationManager.removeUpdates(this);
    }

    //Call map and enable my location functionality
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        LatLng store = new LatLng(Double.parseDouble(storeLatLng.get(0)), Double.parseDouble(storeLatLng.get(1)));
        list.add(0,store);
        map.addMarker(new MarkerOptions().position(store).title("Our store is here!"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(store,12));
        map.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onLocationChanged(Location newLoc) {
        //  LatLng latLng = new LatLng(newLoc.getLatitude(), location.getLongitude());
        // CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        //map.animateCamera(cameraUpdate);

        LatLng curLocation = new LatLng(newLoc.getLatitude(), newLoc.getLongitude());
        list.add(1,curLocation);
        locationManager.removeUpdates(this);


    }



}

