package com.example.mylcm.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    @SerializedName("id")
    private int Id;

    public ServerResponse(int id) {
        Id = id;
    }

    public int getID() {
        return Id;
    }

    public void setID(int Id) {
        this.Id = Id;
    }
}
