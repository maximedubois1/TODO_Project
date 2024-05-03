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
    private float energy;
    private float hp;
    private float defence;
    private float attack;
    private float price;
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

    public static CardDTO fromJson(String json) {
        System.out.println("json: " + json);
        CardDTO cardDTO = new CardDTO();
        json = json.replace("{", "").replace("}", "");
        String[] parts = json.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            String key = keyValue[0].replace("\"", "");
            String value = keyValue[1].replace("\"", "");
            switch (key) {
                case "name":
                    cardDTO.setName(value);
                    break;
                case "description":
                    cardDTO.setDescription(value);
                    break;
                case "family":
                    cardDTO.setFamily(value);
                    break;
                case "affinity":
                    cardDTO.setAffinity(value);
                    break;
                case "imgUrl":
                    cardDTO.setImgUrl(value);
                    break;
                case "smallImgUrl":
                    cardDTO.setSmallImgUrl(value);
                    break;
                case "id":
                    cardDTO.setId(Integer.parseInt(value));
                    break;
                case "energy":
                    cardDTO.setEnergy(Float.parseFloat(value));
                    break;
                case "hp":
                    cardDTO.setHp(Float.parseFloat(value));
                    break;
                case "defence":
                    cardDTO.setDefence(Float.parseFloat(value));
                    break;
                case "attack":
                    cardDTO.setAttack(Float.parseFloat(value));
                    break;
                case "price":
                    cardDTO.setPrice(Float.parseFloat(value));
                    break;
                case "userId":
                    if (!value.equals("null"))
                        cardDTO.setUserId(Integer.parseInt(value));
                    break;
            }
        }
        return cardDTO;
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

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = defence;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
