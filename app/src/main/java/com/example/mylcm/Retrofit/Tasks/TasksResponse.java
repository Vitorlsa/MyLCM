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
    @SerializedName("dataInicio")
    public String DataInicio;
    @SerializedName("dataFim")
    public String DataFim;
    @SerializedName("horaInicio")
    public String HoraInicio;
    @SerializedName("horaFim")
    public String HoraFim;
    @SerializedName("corHexa")
    public String CorHexa;
    @SerializedName("comentario")
    public String Comentario;

    public TasksResponse(int id, int contratoId, String titulo, String dataInicio, String dataFim, String horaInicio, String horaFim, String corHexa, String comentario) {
        Id = id;
        ContratoId = contratoId;
        Titulo = titulo;
        DataInicio = dataInicio;
        DataFim = dataFim;
        HoraInicio = horaInicio;
        HoraFim = horaFim;
        CorHexa = corHexa;
        Comentario = comentario;
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
}
