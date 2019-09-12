package com.example.mylcm.Retrofit.Contract;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SolicitacaoDTO implements Serializable {

    @SerializedName("Id")
    private int Id;
    @SerializedName("Aceitou")
    private boolean Accept;

    public SolicitacaoDTO(int id, boolean accept) {
        Id = id;
        Accept = accept;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean getAccept() {
        return Accept;
    }

    public void setAccept(boolean accept) {
        Accept = accept;
    }
}
