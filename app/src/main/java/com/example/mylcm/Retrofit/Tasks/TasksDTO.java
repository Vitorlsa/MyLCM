package com.example.mylcm.Retrofit.Tasks;

import com.google.gson.annotations.SerializedName;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;

public class TasksDTO implements Serializable{

    @SerializedName("id")
    private int id;
    @SerializedName("data")
    private String dia;

    public TasksDTO(int id, String dia) {
        this.id = id;
        this.dia = dia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
