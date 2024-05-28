package com.sp.service.impl;

import com.sp.model.dto.CardDTO;
import com.sp.model.dto.FightDTO;
import com.sp.service.CardService;
import com.sp.service.FightService;
import org.springframework.stereotype.Service;

@Service
public class FightServiceImpl implements FightService {

    private final CardService cardService;

    public FightServiceImpl(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public Long fight(FightDTO fightDTO) {
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
            return fightDTO.getOpponentID();
        } else if (opponentCard.getHp() <= 0) { //owner wins
            ownerCard.setHp(baseHPOwner);
            opponentCard.setHp(baseHPOpponent);
            ownerCard.setEnergy(ownerCard.getEnergy() - 5);
            opponentCard.setEnergy(opponentCard.getEnergy() - 15);

            this.cardService.update(ownerCard);
            this.cardService.update(opponentCard);
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
