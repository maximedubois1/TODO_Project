package com.sp.model.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private List<CardDTO> cards;
    private int wallet;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String surname, String password, List<CardDTO> cards, int wallet) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.cards = cards;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public int getWallet() {
        return wallet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
}
