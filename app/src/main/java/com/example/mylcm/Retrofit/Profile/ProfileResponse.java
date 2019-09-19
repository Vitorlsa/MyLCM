package com.example.mylcm.Retrofit.Profile;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProfileResponse implements Serializable{
    @SerializedName("id")
    private int Id;
    @SerializedName("login")
    private String Login;
    @SerializedName("senha")
    private String Password;
    @SerializedName("sexo")
    private int Sex;
    @SerializedName("estado")
    private String State;
    @SerializedName("dataNascimento")
    private String Date;
    @SerializedName("cpf")
    private String Cpf;
    @SerializedName("telefone")
    private String Tel;
    @SerializedName("cidade")
    private int City;
    @SerializedName("bairro")
    private String Nhood;
    @SerializedName("cep")
    private String Cep;
    @SerializedName("rua")
    private String Street;
    @SerializedName("numero")
    private String Number;
    @SerializedName("complemento")
    private String Complement;
    @SerializedName("comentario")
    private String Comment;
    @SerializedName("imagem")
    private String Image;
    @SerializedName("curriculo")
    private String Curriculum;
    @SerializedName("competencias")
    private ArrayList<Integer> Competencias;

    public ProfileResponse(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        this.Sex = sex;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        this.Tel = tel;
    }

    public int getCity() {
        return City;
    }

    public void setCity(int city) {
        this.City = city;
    }

    public String getNhood() {
        return Nhood;
    }

    public void setNhood(String nhood) {
        this.Nhood = nhood;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        this.Cep = cep;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        this.Street = street;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        this.Number = number;
    }

    public String getComplement() {
        return Complement;
    }

    public void setComplement(String complement) {
        this.Complement = complement;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getCurriculum() {
        return Curriculum;
    }

    public void setCurriculum(String curriculum) {
        Curriculum = curriculum;
    }

    public ArrayList<Integer> getCompetencias() {
        return Competencias;
    }

    public void setCompetencias(ArrayList<Integer> competencias) {
        Competencias = competencias;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
