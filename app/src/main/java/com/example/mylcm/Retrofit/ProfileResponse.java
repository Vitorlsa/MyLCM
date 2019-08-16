package com.example.mylcm.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ProfileResponse {
    @SerializedName("id")
    private int Id;
    @SerializedName("sexo")
    private String Sex;
    @SerializedName("estado")
    private String State;
    @SerializedName("dataNascimento")
    private String Date;

    public ProfileResponse(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
