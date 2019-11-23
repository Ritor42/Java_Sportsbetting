package com.example.sportsbetting.model;

public class LoginRequest {
    private String email;
    private String password;

    public Boolean validate() {
        return email != null && password != null && email != "" && password != "";
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
