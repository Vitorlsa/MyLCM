package com.example.mylcm.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SolicitacaoDTO implements Serializable {

    @SerializedName("idSolicitacao")
    private int Id;
    @SerializedName("aceitou")
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
