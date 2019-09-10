package com.example.mylcm.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ContractResponse implements Serializable {
    @SerializedName("SolicitacaoPendentePrestadorDTO")
    private ArrayList<SolicitacaoPendentePrestadorDTO> Contract;

    public ContractResponse(ArrayList<SolicitacaoPendentePrestadorDTO> contract) {
        Contract = contract;
    }

    public ArrayList<SolicitacaoPendentePrestadorDTO> getContract() {
        return Contract;
    }

    public void setContract(ArrayList<SolicitacaoPendentePrestadorDTO> contract) {
        Contract = contract;
    }
}
