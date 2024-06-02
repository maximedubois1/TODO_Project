package com.sp.service.impl;

import com.sp.model.Card;
import com.sp.model.dto.CardDTO;
import com.sp.model.dto.FightDTO;
import com.sp.service.CardService;
import com.sp.service.FightService;
import com.sp.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FightServiceImpl implements FightService {

    private final CardService cardService;

    @Value("${uauth.url}")
    private String uauthUrl;

    public FightServiceImpl(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public Long fight(FightDTO fightDTO) throws IOException {
        CardDTO ownerCard = this.cardService.getById(fightDTO.getOwnerCardID()).get();
        CardDTO opponentCard = this.cardService.getById(fightDTO.getOpponentCardID()).get();
        int baseHPOwner = ownerCard.getHp();
        int baseHPOpponent = opponentCard.getHp();

        while (ownerCard.getHp() > 0 && opponentCard.getHp() > 0) {
            ownerCard.setHp(ownerCard.getHp() - opponentCard.getAttack());
            opponentCard.setHp(opponentCard.getHp() - ownerCard.getAttack());
        }
        if (ownerCard.getHp() <= 0) { //opponent wins
            ownerCard.setHp(baseHPOwner);
            opponentCard.setHp(baseHPOpponent);
            ownerCard.setEnergy(ownerCard.getEnergy() - 15);
            opponentCard.setEnergy(opponentCard.getEnergy() - 5);

            this.cardService.update(ownerCard);
            this.cardService.update(opponentCard);
            deliverPrice(fightDTO.getOpponentID(), fightDTO.getOwnerID(), fightDTO.getBet());
            return fightDTO.getOpponentID();
        } else if (opponentCard.getHp() <= 0) { //owner wins
            ownerCard.setHp(baseHPOwner);
            opponentCard.setHp(baseHPOpponent);
            ownerCard.setEnergy(ownerCard.getEnergy() - 5);
            opponentCard.setEnergy(opponentCard.getEnergy() - 15);

            this.cardService.update(ownerCard);
            this.cardService.update(opponentCard);
            deliverPrice(fightDTO.getOwnerID(), fightDTO.getOpponentID(), fightDTO.getBet());
            return fightDTO.getOwnerID();
        }else {
            ownerCard.setHp(baseHPOwner);
            opponentCard.setHp(baseHPOpponent);

            ownerCard.setEnergy(ownerCard.getEnergy() - 10);
            opponentCard.setEnergy(opponentCard.getEnergy() - 10);
            this.cardService.update(ownerCard);
            this.cardService.update(opponentCard);
            return null;
        }
    }

    private void deliverPrice(Long winnerId, Long loserId, int betAmount) throws IOException {
        String response = HttpUtils.sendPostRequest(uauthUrl + "/api/v1/users/wallet/" + winnerId + "/add/" + betAmount, "");
        if (!response.equals("true")) {
            throw new IOException("Failed to deliver price to winner");
        } else {
            System.out.println("Price delivered successfully to winner");
        }
        String response2 = HttpUtils.sendPostRequest(uauthUrl + "/api/v1/users/wallet/" + loserId + "/sub/" + betAmount, "");
        if (!response2.equals("true")) {
            throw new IOException("Failed to deliver price to loser");
        } else {
            System.out.println("Price delivered successfully to loser");
        }
    }

    @Override
    public void regainEnergyOnAllCard() {
        this.cardService.getAll().forEach(cardDTO -> {
            cardDTO.setEnergy(cardDTO.getEnergy() + 5);
            if (cardDTO.getEnergy() > 100) {
                cardDTO.setEnergy(100);
            }
            this.cardService.update(cardDTO);
        });
    }
}
