package com.sp.model.dto;

public class CardDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String family;
    private String affinity;
    private int hp;
    private int energy;
    private int attack;
    private int defense;
    private int price;
    private Long userId;

    public CardDTO() {
    }

    public CardDTO(Long id, String name, String description, String imageUrl, String family, String affinity, int hp, int energy, int attack, int defense, int price, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.family = family;
        this.affinity = affinity;
        this.hp = hp;
        this.energy = energy;
        this.attack = attack;
        this.defense = defense;
        this.price = price;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFamily() {
        return family;
    }

    public String getAffinity() {
        return affinity;
    }

    public int getHp() {
        return hp;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
