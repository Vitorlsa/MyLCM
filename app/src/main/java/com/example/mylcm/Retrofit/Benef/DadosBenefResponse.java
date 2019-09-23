package com.example.mylcm.Retrofit.Benef;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DadosBenefResponse implements Serializable {

    @SerializedName("id")
    public int Id;
    @SerializedName("contratanteId")
    public int ContratanteId;
    @SerializedName("nome")
    public String Nome;
    @SerializedName("dataNascimento")
    public String DataNascimento;
    @SerializedName("sexo")
    public int Sexo;
    @SerializedName("telefone")
    public String Telefone;
    @SerializedName("cidade")
    public int Cidade;
    @SerializedName("estado")
    public String Estado;
    @SerializedName("rua")
    public String Rua;
    @SerializedName("bairro")
    public String Bairro;
    @SerializedName("cep")
    public String Cep;
    @SerializedName("complemento")
    public String Complemento;
    @SerializedName("condicoesClinicas")
    public ArrayList<Integer> CondicoesClinicas;
    @SerializedName("termoDeResponsabilidade")
    public boolean Responsabilidade;
    @SerializedName("numero")
    public String Numero;

    public DadosBenefResponse(int id, int contratanteId, String nome, String dataNascimento, int sexo, String telefone, int cidade, String estado, String rua, String bairro, String cep, String complemento, ArrayList<Integer> condicoesClinicas, boolean responsabilidade, String numero) {
        Id = id;
        ContratanteId = contratanteId;
        Nome = nome;
        DataNascimento = dataNascimento;
        Sexo = sexo;
        Telefone = telefone;
        Cidade = cidade;
        Estado = estado;
        Rua = rua;
        Bairro = bairro;
        Cep = cep;
        Complemento = complemento;
        CondicoesClinicas = condicoesClinicas;
        Responsabilidade = responsabilidade;
        Numero = numero;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getContratanteId() {
        return ContratanteId;
    }

    public void setContratanteId(int contratanteId) {
        ContratanteId = contratanteId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public int getSexo() {
        return Sexo;
    }

    public void setSexo(int sexo) {
        Sexo = sexo;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public int getCidade() {
        return Cidade;
    }

    public void setCidade(int cidade) {
        Cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }

    public ArrayList<Integer> getCondicoesClinicas() {
        return CondicoesClinicas;
    }

    public void setCondicoesClinicas(ArrayList<Integer> condicoesClinicas) {
        CondicoesClinicas = condicoesClinicas;
    }

    public boolean isResponsabilidade() {
        return Responsabilidade;
    }

    public void setResponsabilidade(boolean responsabilidade) {
        Responsabilidade = responsabilidade;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }
}
