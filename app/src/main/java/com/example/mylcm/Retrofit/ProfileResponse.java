package com.example.mylcm.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ProfileResponse {
    @SerializedName("id")
    private int Id;
    @SerializedName("sexo")
    private String Sex;
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

    public ProfileResponse(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
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
}
