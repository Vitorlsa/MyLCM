package com.example.mylcm.Utils.Classes_Adapters;

import java.util.Date;

public class Beneficiario {

    private String NomeBeneficiario;
    private String NomeContratante;
    private int BenefId;
    private int ContratoId;

    public Beneficiario(String nomeBeneficiario, String nomeContratante, int benefId, int contratoId) {
        NomeBeneficiario = nomeBeneficiario;
        NomeContratante = nomeContratante;
        BenefId = benefId;
        ContratoId = contratoId;
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

    public int getContratoId() {
        return ContratoId;
    }

    public void setContratoId(int contratoId) {
        ContratoId = contratoId;
    }
}
