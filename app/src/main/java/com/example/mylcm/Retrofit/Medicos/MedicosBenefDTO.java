package com.example.mylcm.Retrofit.Medicos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MedicosBenefDTO implements Serializable {

    @SerializedName("id")
    public int Id;
    @SerializedName("beneficiarioId")
    public int BeneficiarioId;
    @SerializedName("nome")
    public String Nome;
    @SerializedName("telefoneConsultorio")
    public int TelefoneConsultorio;
    @SerializedName("celular")
    public int Celular;
    @SerializedName("especialidadeMedicoId")
    public int EspecialidadeMedicoId;
    @SerializedName("convenio")
    public boolean Convenio;
    @SerializedName("cep")
    public String Cep;
    @SerializedName("bairro")
    public String Bairro;
    @SerializedName("rua")
    public String Rua;
    @SerializedName("cidadeId")
    public int CidadeId;
    @SerializedName("estadoId")
    public int EstadoId;
    @SerializedName("numero")
    public String Numero;
    @SerializedName("complemento")
    public String Complemento;
    @SerializedName("estadoUf")
    public String EstadoUf;
    @SerializedName("especialidadeNome")
    public String EspecialidadeNome;

    public MedicosBenefDTO(int id, int beneficiarioId, String nome, int telefoneConsultorio, int celular, int especialidadeMedicoId, boolean convenio, String cep, String bairro, String rua, int cidadeId, int estadoId, String numero, String complemento, String estadoUf, String especialidadeNome) {
        Id = id;
        BeneficiarioId = beneficiarioId;
        Nome = nome;
        TelefoneConsultorio = telefoneConsultorio;
        Celular = celular;
        EspecialidadeMedicoId = especialidadeMedicoId;
        Convenio = convenio;
        Cep = cep;
        Bairro = bairro;
        Rua = rua;
        CidadeId = cidadeId;
        EstadoId = estadoId;
        Numero = numero;
        Complemento = complemento;
        EstadoUf = estadoUf;
        EspecialidadeNome = especialidadeNome;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getBeneficiarioId() {
        return BeneficiarioId;
    }

    public void setBeneficiarioId(int beneficiarioId) {
        BeneficiarioId = beneficiarioId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getTelefoneConsultorio() {
        return TelefoneConsultorio;
    }

    public void setTelefoneConsultorio(int telefoneConsultorio) {
        TelefoneConsultorio = telefoneConsultorio;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int celular) {
        Celular = celular;
    }

    public int getEspecialidadeMedicoId() {
        return EspecialidadeMedicoId;
    }

    public void setEspecialidadeMedicoId(int especialidadeMedicoId) {
        EspecialidadeMedicoId = especialidadeMedicoId;
    }

    public boolean isConvenio() {
        return Convenio;
    }

    public void setConvenio(boolean convenio) {
        Convenio = convenio;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public int getCidadeId() {
        return CidadeId;
    }

    public void setCidadeId(int cidadeId) {
        CidadeId = cidadeId;
    }

    public int getEstadoId() {
        return EstadoId;
    }

    public void setEstadoId(int estadoId) {
        EstadoId = estadoId;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }

    public String getEstadoUf() {
        return EstadoUf;
    }

    public void setEstadoUf(String estadoUf) {
        EstadoUf = estadoUf;
    }

    public String getEspecialidadeNome() {
        return EspecialidadeNome;
    }

    public void setEspecialidadeNome(String especialidadeNome) {
        EspecialidadeNome = especialidadeNome;
    }
}
