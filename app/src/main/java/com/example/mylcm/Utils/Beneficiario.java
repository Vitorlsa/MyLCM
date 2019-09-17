package com.example.mylcm.Utils;

import java.util.Date;

public class Beneficiario {

    private String NomeBeneficiario;
    private String NomeContratante;

    public Beneficiario(String nomeBeneficiario, String nomeContratante) {
        NomeBeneficiario = nomeBeneficiario;
        NomeContratante = nomeContratante;
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
}
