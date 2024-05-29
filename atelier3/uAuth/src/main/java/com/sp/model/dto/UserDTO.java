package com.sp.model.dto;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private int wallet;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String surname, int wallet) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
}
