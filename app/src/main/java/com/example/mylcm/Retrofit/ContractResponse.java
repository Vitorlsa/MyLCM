package com.example.mylcm.Retrofit;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

public class ContractResponse implements Serializable {
    @SerializedName("SolicitacaoPendentePrestadorDTO")
    private ArrayList<> Contract;

    public ContractResponse(ArrayList<JSONArray> contract) {
        Contract = contract;
    }

    public ArrayList<JSONArray> getContract() {
        return Contract;
    }

    public void setContract(ArrayList<JSONArray> contract) {
        Contract = contract;
    }
}
