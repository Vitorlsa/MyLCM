package com.example.mylcm.Retrofit.Profile;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CityResponse implements Serializable {

    @SerializedName("")
    private String CityName;

    public CityResponse(String cityName) {
        CityName = cityName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}
