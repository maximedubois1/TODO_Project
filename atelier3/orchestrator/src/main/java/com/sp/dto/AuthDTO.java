package com.sp.dto;

public class AuthDTO {

    private String surname;
    private String password;

    public AuthDTO(String surname, String password) {
        this.surname = surname;
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
