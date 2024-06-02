package com.sp.model.dto;

public class FightDTO {

    private String roomName;
    private Long ownerID;
    private Long ownerCardID;
    private Long opponentID;
    private Long opponentCardID;
    private int bet;
    private Long winnerID;

    public Long getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(Long winnerID) {
        this.winnerID = winnerID;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
