package com.example.mylcm.Retrofit.Login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    @SerializedName("id")
    private int Id;
    @SerializedName("nome")
    private String Nome;
    @SerializedName("email")
    private String Email;
    @SerializedName("imagem")
    private String Imagem;

    public ServerResponse(int id, String nome, String email, String imagem) {
        Id = id;
        Nome = nome;
        Email = email;
        Imagem = imagem;
    }

    public int getID() {
        return Id;
    }

    public void setID(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        this.Imagem = imagem;
    }
}
