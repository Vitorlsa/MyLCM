package com.example.mylcm.Retrofit.Tasks;

import com.google.gson.annotations.SerializedName;

public class TasksDoneDTO {

    @SerializedName("tarefaId")
    private int TarefaId;
    @SerializedName("comentario")
    private String Comentario;
    @SerializedName("data")
    private String Data;
    @SerializedName("hora")
    private String Hora;
    @SerializedName("realizada")
    private boolean Realizada;
    @SerializedName("tarefaRealizadaId")
    private int TarefaRealizadaId;

    public TasksDoneDTO(int tarefaId, String comentario, String data, String hora, boolean realizada, int tarefaRealizadaId) {
        TarefaId = tarefaId;
        Comentario = comentario;
        Data = data;
        Hora = hora;
        Realizada = realizada;
        TarefaRealizadaId = tarefaRealizadaId;
    }

    public int getTarefaId() {
        return TarefaId;
    }

    public void setTarefaId(int tarefaId) {
        TarefaId = tarefaId;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public boolean isRealizada() {
        return Realizada;
    }

    public void setRealizada(boolean realizada) {
        Realizada = realizada;
    }

    public int getTarefaRealizadaId() {
        return TarefaRealizadaId;
    }

    public void setTarefaRealizadaId(int tarefaRealizadaId) {
        TarefaRealizadaId = tarefaRealizadaId;
    }
}
