package com.sp.service;

import com.sp.model.dto.FightDTO;

import java.io.IOException;

public interface FightService {

    public Long fight(FightDTO fightDTO) throws IOException;

    public void regainEnergyOnAllCard();

}
