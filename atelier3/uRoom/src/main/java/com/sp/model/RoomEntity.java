package com.sp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "room")
@Entity
public class RoomEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "bet")
    private int bet;

    @Column(name = "owner_id")
    private Long ownerID;

    @Column(name = "owner_card_id")
    private Long ownerCardID;

    @Column(name = "opponent_id")
    private Long opponentID;

    @Column(name = "opponent_card_id")
    private Long opponentCardID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public Long getOwnerCardID() {
        return ownerCardID;
    }

    public void setOwnerCardID(Long ownerCardID) {
        this.ownerCardID = ownerCardID;
    }

    public Long getOpponentID() {
        return opponentID;
    }

    public void setOpponentID(Long opponentID) {
        this.opponentID = opponentID;
    }

    public Long getOpponentCardID() {
        return opponentCardID;
    }

    public void setOpponentCardID(Long opponentCardID) {
        this.opponentCardID = opponentCardID;
    }
}
