package com.example.admin1.mapapplication.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.admin1.mapapplication.R;
import com.example.admin1.mapapplication.Webservice.ApiConstants;
import com.example.admin1.mapapplication.Webservice.JsonResponse;
import com.example.admin1.mapapplication.Webservice.WebRequest;
import com.example.admin1.mapapplication.interfaces.ApiServiceCaller;
import com.example.admin1.mapapplication.utility.App;

import java.util.ArrayList;
import java.util.Map;

public class SpinnerActivity extends AppCompatActivity implements ApiServiceCaller {

    Spinner spinnerLocation;
    Context context;
    ArrayList<Map> spinnerList;
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        context = this;
        spinnerLocation = findViewById(R.id.spinner_location);

        getData();

    }

    public void getData() {
        try {
            JsonObjectRequest request = WebRequest.callPostMethod(context, "", null, Request.Method.GET, ApiConstants.GET_DETAILS,
                    ApiConstants.GET_DETAILS, this, "");
            App.getInstance().addToRequestQueue(request, ApiConstants.GET_DETAILS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAsyncSuccess(JsonResponse jsonResponse, String label) {
        if (jsonResponse != null){
            if (jsonResponse.result != null && jsonResponse.result.equals(JsonResponse.SUCCESS)){
                if (jsonResponse.locateus.size() != 0)
                    for (int i = 0; i < jsonResponse.locateus.size(); i++){
                        arrayList.add(jsonResponse.locateus.get(i).getName());
                    }

                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context ,R.layout.support_simple_spinner_dropdown_item, arrayList);
                spinnerLocation.setAdapter(spinnerAdapter);

            }
        }

    }



    @Override
    public void onAsyncFail(String message, String label, NetworkResponse response) {

        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAsyncCompletelyFail(String message, String label) {

        Toast.makeText(context, "Completely Fail", Toast.LENGTH_SHORT).show();

    }
}
