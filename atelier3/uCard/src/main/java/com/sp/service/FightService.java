package com.sp.service;

import com.sp.model.dto.FightDTO;

public interface FightService {

    public Long fight(FightDTO fightDTO);

    public void regainEnergyOnAllCard();

}
