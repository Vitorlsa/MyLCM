package com.example.mylcm.Utils.Classes_Adapters;

public class MedicosBenef {

    private String NomeMedico;
    private int TelMedico;

    public MedicosBenef(String nomeMedico, int telMedico) {
        NomeMedico = nomeMedico;
        TelMedico = telMedico;
    }

    public String getNomeMedico() {
        return NomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        NomeMedico = nomeMedico;
    }

    public int getTelMedico() {
        return TelMedico;
    }

    public void setTelMedico(int telMedico) {
        TelMedico = telMedico;
    }
}
