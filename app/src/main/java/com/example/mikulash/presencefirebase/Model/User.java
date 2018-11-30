package com.example.mikulash.presencefirebase.Model;

public class User {

    private String email;
    private String status;

    public User(String email, String status) {
        this.email = email;
        this.status = status;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
