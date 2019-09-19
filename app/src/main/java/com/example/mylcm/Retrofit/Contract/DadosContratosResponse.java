package com.example.mylcm.Retrofit.Contract;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DadosContratosResponse implements Serializable {

    @SerializedName("nomeBeneficiario")
    public String NomeBeneficiario;
    @SerializedName("nomeContratante")
    public String NomeContratante;
    @SerializedName("nomePrestadorDeServico")
    public String NomePrestadorDeServico;
    @SerializedName("dataSolicitacao")
    public String DataSolicitacao;
    @SerializedName("dataFim")
    public String DataFim;
    @SerializedName("comentario")
    public String Comentario;

    public DadosContratosResponse(String nomeBeneficiario, String nomeContratante, String nomePrestadorDeServico, String dataSolicitacao, String dataFim, String comentario) {
        NomeBeneficiario = nomeBeneficiario;
        NomeContratante = nomeContratante;
        NomePrestadorDeServico = nomePrestadorDeServico;
        DataSolicitacao = dataSolicitacao;
        DataFim = dataFim;
        Comentario = comentario;
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

    public String getNomePrestadorDeServico() {
        return NomePrestadorDeServico;
    }

    public void setNomePrestadorDeServico(String nomePrestadorDeServico) {
        NomePrestadorDeServico = nomePrestadorDeServico;
    }

    public String getDataSolicitacao() {
        return DataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        DataSolicitacao = dataSolicitacao;
    }

    public String getDataFim() {
        return DataFim;
    }

    public void setDataFim(String dataFim) {
        DataFim = dataFim;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

}
