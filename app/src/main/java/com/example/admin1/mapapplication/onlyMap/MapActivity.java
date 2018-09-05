package com.example.admin1.mapapplication.onlyMap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin1.mapapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    MapView mapView;
    GoogleMap gMap;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle bundle = null;
        if(savedInstanceState != null){
            bundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map);
        mapView.onCreate(bundle);
        mapView.getMapAsync(this);
    }

    public  void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle myBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (myBundle == null) {
            myBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, myBundle);
        }
        mapView.onSaveInstanceState(myBundle);
    }

    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMinZoomPreference(12);
        LatLng ny = new LatLng(40.7143528, -74.0059731);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }
}
