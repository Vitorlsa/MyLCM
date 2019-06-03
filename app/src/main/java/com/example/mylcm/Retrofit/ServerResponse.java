package com.example.mylcm.Retrofit;

public class ServerResponse {

    private String result;
    private boolean valid;
    private String login;
    private String senha;

    public ServerResponse(){}

    public String getResult() {
        return result;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
