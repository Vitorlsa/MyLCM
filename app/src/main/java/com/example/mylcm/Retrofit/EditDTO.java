package com.example.mylcm.Retrofit;

import com.google.gson.annotations.SerializedName;

public class EditDTO {
    @SerializedName("id")
    private int Id;
    @SerializedName("email")
    private String Email;
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

    public EditDTO(int id, String email, int sex, String state, String date, String cpf, String tel, int city, String nhood, String cep, String street, String number, String complement) {
        Id = id;
        Email = email;
        Sex = sex;
        State = state;
        Date = date;
        Cpf = cpf;
        Tel = tel;
        City = city;
        Nhood = nhood;
        Cep = cep;
        Street = street;
        Number = number;
        Complement = complement;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public int getCity() {
        return City;
    }

    public void setCity(int city) {
        City = city;
    }

    public String getNhood() {
        return Nhood;
    }

    public void setNhood(String nhood) {
        Nhood = nhood;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getComplement() {
        return Complement;
    }

    public void setComplement(String complement) {
        Complement = complement;
    }
}
