package com.example.mylcm.Retrofit.Contract;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class BenefDTO implements Serializable {

    @SerializedName("soliciatacaoContratoId")
    public int SolicitacaoContratoId;
    @SerializedName("id")
    public int Id;
    @SerializedName("contratanteId")
    public int ContratanteId;
    @SerializedName("beneficiarioId")
    public int BeneficiarioId;
    @SerializedName("prestadorDeServicoId")
    public int PrestadorDeServicoId;
    @SerializedName("dataInicio")
    public String DataInicio;
    @SerializedName("dataFim")
    public String DataFim;
    @SerializedName("nomeContratante")
    public String NomeContratante;
    @SerializedName("nomeBeneficiario")
    public String NomeBeneficiario;
    @SerializedName("nomePrestadorDeServico")
    public String NomePrestadorDeServico;
    @SerializedName("dataSolicitacao")
    public String DataSolicitacao;
    @SerializedName("comentario")
    public String Comentario;

    public int getSolicitacaoContratoId() {
        return SolicitacaoContratoId;
    }

    public void setSolicitacaoContratoId(int solicitacaoContratoId) {
        SolicitacaoContratoId = solicitacaoContratoId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getContratanteId() {
        return ContratanteId;
    }

    public void setContratanteId(int contratanteId) {
        ContratanteId = contratanteId;
    }

    public int getBeneficiarioId() {
        return BeneficiarioId;
    }

    public void setBeneficiarioId(int beneficiarioId) {
        BeneficiarioId = beneficiarioId;
    }

    public int getPrestadorDeServicoId() {
        return PrestadorDeServicoId;
    }

    public void setPrestadorDeServicoId(int prestadorDeServicoId) {
        PrestadorDeServicoId = prestadorDeServicoId;
    }

    public String getDataInicio() {
        return DataInicio;
    }

    public void setDataInicio(String dataInicio) {
        DataInicio = dataInicio;
    }

    public String getDataFim() {
        return DataFim;
    }

    public void setDataFim(String dataFim) {
        DataFim = dataFim;
    }

    public String getNomeContratante() {
        return NomeContratante;
    }

    public void setNomeContratante(String nomeContratante) {
        NomeContratante = nomeContratante;
    }

    public String getNomeBeneficiario() {
        return NomeBeneficiario;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        NomeBeneficiario = nomeBeneficiario;
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

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }
}
