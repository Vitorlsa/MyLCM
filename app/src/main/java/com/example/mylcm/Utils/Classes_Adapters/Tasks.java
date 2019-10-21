package com.example.mylcm.Utils.Classes_Adapters;

import java.sql.Time;

public class Tasks {

    private String Title;
    private Time TimeStart;
    private Time TimeEnd;
    private String TaskComment;
    private int MedQtd;
    private String MedName;

    public Tasks(String title, Time timeStart, Time timeEnd, String taskComment, int medQtd, String medName) {
        Title = title;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        TaskComment = taskComment;
        MedQtd = medQtd;
        MedName = medName;
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
}
