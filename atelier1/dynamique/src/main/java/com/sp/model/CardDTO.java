package com.sp.model;

import org.springframework.stereotype.Service;

@Service
public class CardDTO {
    private String name;
    private String description;
    private String family;
    private String affinity;
    private String imgUrl;
    private String smallImgUrl;
    private int id;
    private int energy;
    private int hp;
    private int defence;
    private int attack;
    private int price;
    private int userId;

    public CardDTO() {
        this.name = "";
        this.description = "";
        this.family = "";
        this.affinity = "";
        this.imgUrl = "";
        this.smallImgUrl = "";
        this.id = 0;
        this.energy = 0;
        this.hp = 0;
        this.defence = 0;
        this.attack = 0;
        this.price = 0;
        this.userId = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAffinity() {
        return affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String toJson() {
        return "{" +
                "\"name\":\"" + name + "\"," +
                "\"description\":\"" + description + "\"," +
                "\"family\":\"" + family + "\"," +
                "\"affinity\":\"" + affinity + "\"," +
                "\"imgUrl\":\"" + imgUrl + "\"," +
                "\"smallImgUrl\":\"" + smallImgUrl + "\"," +
                "\"energy\":" + energy + "," +
                "\"hp\":" + hp + "," +
                "\"defence\":" + defence + "," +
                "\"attack\":" + attack + "," +
                "\"price\":" + price + "," +
                "\"userId\":" + userId +
                "}";
    }
}
