package com.hishab.app.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import com.hishab.app.model.Dice;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8001)
public class DiceFeignClientTest {

	@Autowired
	private DiceFeignClient diceFeignClient;

	@Test
	public void getDiceScore_whenValidClient_returnValidResponse() {

		Dice dice = diceFeignClient.rollDice();
		assertThat(Integer.parseInt(dice.getScore())).isPositive();
		assertThat(dice.getScore()).containsAnyOf(new String[] { "1", "2", "3", "4", "5", "6" });
	}
}
