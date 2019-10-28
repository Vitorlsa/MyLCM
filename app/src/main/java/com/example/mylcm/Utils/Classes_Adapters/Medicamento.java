package com.example.mylcm.Utils.Classes_Adapters;

public class Medicamento {

    private String NomeMedicamento;
    private String Posologia;
    private int Quantidade;
    private String UnidadeMedida;

    public Medicamento(String nomeMedicamento, String posologia, int quantidade, String unidadeMedida) {
        NomeMedicamento = nomeMedicamento;
        Posologia = posologia;
        Quantidade = quantidade;
        UnidadeMedida = unidadeMedida;
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

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return UnidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        UnidadeMedida = unidadeMedida;
    }
}
