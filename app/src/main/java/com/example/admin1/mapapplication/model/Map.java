package com.example.admin1.mapapplication.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Map implements Serializable{

    @SerializedName("locate_us_address")
    public String address;

    @SerializedName("locate_us_latitude")
    public String locate_us_latitude;

    @SerializedName("locate_us_start_time")
    public String startTime;

    @SerializedName("locate_us_center_name")
    public String name;

    @SerializedName("locate_us_longitude")
    public String locate_us_longitude;

    @SerializedName("locate_us_city")
    public String city;

    @SerializedName("locate_us_contact_no")
    public String contactNo;

    @SerializedName("locate_us_end_time")
    public String endTime;

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return locate_us_latitude;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getName() {
        return name;
    }

    public String getLongitude() {
        return locate_us_longitude;
    }

    public String getCity() {
        return city;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEndTime() {
        return endTime;
    }
}
