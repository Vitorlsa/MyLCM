package com.example.mylcm.Retrofit.Tasks;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TasksResponse implements Serializable {

    @SerializedName("id")
    public int Id;
    @SerializedName("contratoId")
    public int ContratoId;
    @SerializedName("titulo")
    public String Titulo;
    @SerializedName("data")
    public String Data;
    @SerializedName("horaInicio")
    public String HoraInicio;
    @SerializedName("horaFim")
    public String HoraFim;
    @SerializedName("corHexa")
    public String CorHexa;
    @SerializedName("comentario")
    public String Comentario;
    @SerializedName("dataString")
    public String DataString;
    @SerializedName("tarefaRealizada")
    public boolean TarefaRealizada;
    @SerializedName("quantidadeMedicamento")
    public int QuantidadeMedicamento;
    @SerializedName("nomeMedicamento")
    public String NomeMedicamento;
    @SerializedName("beneficiarioMendicamentoId")
    public int BeneficiarioMedicamentoId;

    public TasksResponse(int id, int contratoId, String titulo, String data, String horaInicio, String horaFim, String corHexa, String comentario, String dataString, boolean tarefaRealizada, int quantidadeMedicamento, String nomeMedicamento, int beneficiarioMedicamentoId) {
        Id = id;
        ContratoId = contratoId;
        Titulo = titulo;
        Data = data;
        HoraInicio = horaInicio;
        HoraFim = horaFim;
        CorHexa = corHexa;
        Comentario = comentario;
        DataString = dataString;
        TarefaRealizada = tarefaRealizada;
        QuantidadeMedicamento = quantidadeMedicamento;
        NomeMedicamento = nomeMedicamento;
        BeneficiarioMedicamentoId = beneficiarioMedicamentoId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getContratoId() {
        return ContratoId;
    }

    public void setContratoId(int contratoId) {
        ContratoId = contratoId;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getDataString() {
        return DataString;
    }

    public void setDataString(String dataString) {
        DataString = dataString;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getHoraFim() {
        return HoraFim;
    }

    public void setHoraFim(String horaFim) {
        HoraFim = horaFim;
    }

    public String getCorHexa() {
        return CorHexa;
    }

    public void setCorHexa(String corHexa) {
        CorHexa = corHexa;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public boolean isTarefaRealizada() {
        return TarefaRealizada;
    }

    public void setTarefaRealizada(boolean tarefaRealizada) {
        TarefaRealizada = tarefaRealizada;
    }

    public int getQuantidadeMedicamento() {
        return QuantidadeMedicamento;
    }

    public void setQuantidadeMedicamento(int quantidadeMedicamento) {
        QuantidadeMedicamento = quantidadeMedicamento;
    }

    public String getNomeMedicamento() {
        return NomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        NomeMedicamento = nomeMedicamento;
    }

    public int getBeneficiarioMedicamentoId() {
        return BeneficiarioMedicamentoId;
    }

    public void setBeneficiarioMedicamentoId(int beneficiarioMedicamentoId) {
        BeneficiarioMedicamentoId = beneficiarioMedicamentoId;
    }
}
