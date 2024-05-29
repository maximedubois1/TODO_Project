package com.sp.dtos;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private List<CardDTO> cards;
    private int wallet;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String surname,List<CardDTO> cards, int wallet) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
}
