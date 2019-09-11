package com.example.mylcm.Utils;


public class Solicitacao {

    private int Id;
    private int BeneficiarioId;
    private int ContratanteId;
    private String NomeBeneficiario;
    private String NomeContratante;
    private String DataSolicitacao;

    public Solicitacao(int id, int beneficiarioId, int contratanteId, String nomeBeneficiario, String nomeContratante, String dataSolicitacao) {
        Id = id;
        BeneficiarioId = beneficiarioId;
        ContratanteId = contratanteId;
        NomeBeneficiario = nomeBeneficiario;
        NomeContratante = nomeContratante;
        DataSolicitacao = dataSolicitacao;
    }

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
