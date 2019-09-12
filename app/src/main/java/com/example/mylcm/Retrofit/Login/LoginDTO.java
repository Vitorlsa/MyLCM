package com.example.mylcm.Retrofit.Login;

public class LoginDTO {

    public String Login;
    public String Senha;

    public LoginDTO(String login, String senha) {
        Login = login;
        Senha = senha;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }
}
