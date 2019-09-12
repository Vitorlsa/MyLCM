package com.example.mylcm.Retrofit.Contract;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SolicitacaoPendentePrestadorDTO implements Serializable {

    @SerializedName("id")
    public int Id;
    @SerializedName("beneficiarioId")
    public int BeneficiarioId;
    @SerializedName("contratanteId")
    public int ContratanteId;
    @SerializedName("nomeBeneficiario")
    public String NomeBeneficiario;
    @SerializedName("nomeContratante")
    public String NomeContratante;
    @SerializedName("dataSolicitacao")
    public String DataSolicitacao;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getBeneficiarioId() {
        return BeneficiarioId;
    }

    public void setBeneficiarioId(int beneficiarioId) {
        BeneficiarioId = beneficiarioId;
    }

    public int getContratanteId() {
        return ContratanteId;
    }

    public void setContratanteId(int contratanteId) {
        ContratanteId = contratanteId;
    }

    public String getNomeBeneficiario() {
        return NomeBeneficiario;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        NomeBeneficiario = nomeBeneficiario;
    }

    public String getNomeContratante() {
        return NomeContratante;
    }

    public void setNomeContratante(String nomeContratante) {
        NomeContratante = nomeContratante;
    }

    public String getDataSolicitacao() {
        return DataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        DataSolicitacao = dataSolicitacao;
    }
}
