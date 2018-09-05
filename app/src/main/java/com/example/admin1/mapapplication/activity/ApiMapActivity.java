package com.example.admin1.mapapplication.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.admin1.mapapplication.R;
import com.example.admin1.mapapplication.Webservice.ApiConstants;
import com.example.admin1.mapapplication.Webservice.CommonUtils;
import com.example.admin1.mapapplication.Webservice.JsonResponse;
import com.example.admin1.mapapplication.Webservice.WebRequest;
import com.example.admin1.mapapplication.interfaces.ApiServiceCaller;
import com.example.admin1.mapapplication.model.Map;
import com.example.admin1.mapapplication.utility.App;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ApiMapActivity extends AppCompatActivity implements ApiServiceCaller {

    MapView mapView;
    GoogleMap googleMap;
    public Context context;
    Double lat, lon;
    ArrayList<String> arrayList;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_map);
        context = this;
        Bundle bundle = null;
        if (savedInstanceState != null) {
            bundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.api_map);
        arrayList = new ArrayList<>();
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        try {
            MapsInitializer.initialize(context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mapView) {
                googleMap = mapView;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);
            }

        });

        getLocation();

    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle myBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (myBundle == null) {
            myBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, myBundle);
        }
        mapView.onSaveInstanceState(myBundle);
    }

    @Override
    public void onAsyncSuccess(JsonResponse jsonResponse, String label) {

        switch (label) {
            case ApiConstants.GET_DETAILS: {
                if (jsonResponse != null) {
                    if (jsonResponse.result != null && jsonResponse.result.equals(JsonResponse.SUCCESS)) {
                        if (jsonResponse.locateus.size() != 0)
                            for (int i = 0; i < jsonResponse.locateus.size(); i++) {
                                try {
                                    lat = Double.parseDouble(jsonResponse.locateus.get(i).locate_us_latitude.toString());
                                    lon = Double.parseDouble(jsonResponse.locateus.get(i).locate_us_longitude.toString());
                                } catch (NumberFormatException e) {
                                    lat = 0.0;
                                    lon = 0.0;
                                    e.printStackTrace();
                                }

                                arrayList.add(jsonResponse.locateus.get(i).getName());
                                LatLng latLng = new LatLng(lat, lon);
                                Log.i("cccccc", latLng.toString());
                                googleMap.addMarker(new MarkerOptions().position(latLng).title(jsonResponse.locateus.get(i).name).snippet(jsonResponse.locateus.get(i).address).draggable(false));
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
                            }
                    }
                } else
                    Toast.makeText(context, "abhvjvh", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getLocation() {
        try {
            JsonObjectRequest request = WebRequest.callPostMethod(context, "", null, Request.Method.GET, ApiConstants.GET_DETAILS,
                    ApiConstants.GET_DETAILS, this, "");
            App.getInstance().addToRequestQueue(request, ApiConstants.GET_DETAILS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }


    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onAsyncFail(String message, String label, NetworkResponse response) {
        Toast.makeText(context, "fail1", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onAsyncCompletelyFail(String message, String label) {
        Toast.makeText(context, "fail2", Toast.LENGTH_SHORT).show();


    }
}
