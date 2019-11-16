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
    @SerializedName("ratingUsuario")
    private double RatingUsuario;

    public ServerResponse(int id, String nome, String email, String imagem, double ratingUsuario) {
        Id = id;
        Nome = nome;
        Email = email;
        Imagem = imagem;
        RatingUsuario = ratingUsuario;
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

    public double getRatingUsuario() {
        return RatingUsuario;
    }

    public void setRatingUsuario(double ratingUsuario) {
        RatingUsuario = ratingUsuario;
    }
}
