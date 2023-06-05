package com.hishab.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.hishab.app.config.FeignClientConfig;
import com.hishab.app.model.Dice;

@FeignClient(value = "${client.dice.name}", 
			url = "${client.dice.baseUrl}", 
			configuration = FeignClientConfig.class, 
			fallback = DiceFeignClientFallback.class)
public interface DiceFeignClient {
	@GetMapping(value = "/api/v1/roll-dice")
	Dice rollDice();
}
