package com.example.mylcm.Utils.Classes_Adapters;

import java.sql.Time;

public class Tasks {

    private int TaskId;
    private String Title;
    private Time TimeStart;
    private Time TimeEnd;
    private String TaskComment;
    private int MedQtd;
    private String MedName;
    private String HexaColor;
    private String DataTask;
    private int TaskDoneId;
    private boolean TarefaRealizada;

    public Tasks(int taskId, String title, Time timeStart, Time timeEnd, String taskComment, int medQtd, String medName, String hexaColor, String dataTask, int taskDoneId, boolean tarefaRealizada) {
        TaskId = taskId;
        Title = title;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        TaskComment = taskComment;
        MedQtd = medQtd;
        MedName = medName;
        HexaColor = hexaColor;
        DataTask = dataTask;
        TaskDoneId = taskDoneId;
        TarefaRealizada = tarefaRealizada;
    }

    public int getTaskId() {
        return TaskId;
    }

    public void setTaskId(int taskId) {
        TaskId = taskId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Time getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(Time timeStart) {
        TimeStart = timeStart;
    }

    public Time getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        TimeEnd = timeEnd;
    }

    public String getTaskComment() {
        return TaskComment;
    }

    public void setTaskComment(String taskComment) {
        TaskComment = taskComment;
    }

    public int getMedQtd() {
        return MedQtd;
    }

    public void setMedQtd(int medQtd) {
        MedQtd = medQtd;
    }

    public String getMedName() {
        return MedName;
    }

    public void setMedName(String medName) {
        MedName = medName;
    }

    public String getHexaColor() {
        return HexaColor;
    }

    public void setHexaColor(String hexaColor) {
        HexaColor = hexaColor;
    }

    public String getDataTask() {
        return DataTask;
    }

    public void setDataTask(String dataTask) {
        DataTask = dataTask;
    }

    public int getTaskDoneId() {
        return TaskDoneId;
    }

    public void setTaskDoneId(int taskDoneId) {
        TaskDoneId = taskDoneId;
    }

    public boolean isTarefaRealizada() {
        return TarefaRealizada;
    }

    public void setTarefaRealizada(boolean tarefaRealizada) {
        TarefaRealizada = tarefaRealizada;
    }
}
