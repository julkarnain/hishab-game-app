package com.hishab.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hishab.app.client.DiceFeignClient;
import com.hishab.app.model.Dice;

@Service
public class DiceServiceImpl implements DiceService {

	@Autowired
	private DiceFeignClient diceFeignClient;

	@Override
	public Integer getScoreValue() {
		Dice dice = diceFeignClient.rollDice();
		return Integer.parseInt(dice.getScore());
	}
}
