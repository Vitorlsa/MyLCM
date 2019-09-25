package com.example.mylcm.Retrofit.Benef;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BenefMedDTO implements Serializable {

    @SerializedName("id")
    public int Id;
    @SerializedName("quantidade")
    public int Quantidade;
    @SerializedName("nomeMedicamento")
    public String NomeMedicamento;
    @SerializedName("posologia")
    public String Posologia;

    public BenefMedDTO(int id, int quantidade, String nomeMedicamento, String posologia) {
        Id = id;
        Quantidade = quantidade;
        NomeMedicamento = nomeMedicamento;
        Posologia = posologia;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public String getNomeMedicamento() {
        return NomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        NomeMedicamento = nomeMedicamento;
    }

    public String getPosologia() {
        return Posologia;
    }

    public void setPosologia(String posologia) {
        Posologia = posologia;
    }
}
