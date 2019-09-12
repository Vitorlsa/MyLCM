package com.example.mylcm.Retrofit.Profile;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EditDTO {
    @SerializedName("id")
    private int Id;
    @SerializedName("nome")
    private String Username;
    @SerializedName("login")
    private String Login;
    @SerializedName("senha")
    private String Password;
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
    @SerializedName("competencias")
    private ArrayList<Integer> Competencia;
    @SerializedName("comentario")
    private String Comment;
    @SerializedName("termos")
    private Boolean Termos;
    @SerializedName("imagem")
    private String Image;
    @SerializedName("pdf")
    private String Curriculum;

    public EditDTO(int id, String username, String login, String password, String email, int sex, String state, String date, String cpf, String tel, int city, String nhood, String cep, String street, String number, String complement, ArrayList<Integer> competencia, String comment, Boolean termos, String image, String curriculum) {
        Id = id;
        Username = username;
        Login = login;
        Password = password;
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
        Competencia = competencia;
        Comment = comment;
        Termos = termos;
        Image = image;
        Curriculum = curriculum;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

    public ArrayList<Integer> getCompetencia() {
        return Competencia;
    }

    public void setCompetencia(ArrayList<Integer> competencia) {
        Competencia = competencia;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Boolean getTermos() {
        return Termos;
    }

    public void setTermos(Boolean termos) {
        Termos = termos;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCurriculum() {
        return Curriculum;
    }

    public void setCurriculum(String curriculum) {
        Curriculum = curriculum;
    }
}
