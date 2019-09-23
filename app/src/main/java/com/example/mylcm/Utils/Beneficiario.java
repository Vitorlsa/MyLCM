package com.example.mylcm.Utils;

import java.util.Date;

public class Beneficiario {

    private String NomeBeneficiario;
    private String NomeContratante;
    private int BenefId;

    public Beneficiario(String nomeBeneficiario, String nomeContratante, int benefId) {
        NomeBeneficiario = nomeBeneficiario;
        NomeContratante = nomeContratante;
        BenefId = benefId;
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

    public int getBenefId() {
        return BenefId;
    }

    public void setBenefId(int benefId) {
        BenefId = benefId;
    }
}
