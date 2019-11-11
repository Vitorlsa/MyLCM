package com.example.mylcm.Retrofit.Contract;

public class CancelContractDTO {

    public int Id;
    public String Comentario;
    public double Rating;

    public CancelContractDTO(int id, String comentario, double rating) {
        Id = id;
        Comentario = comentario;
        Rating = rating;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }
}
