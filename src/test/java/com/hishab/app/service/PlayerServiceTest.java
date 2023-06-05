package com.hishab.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hishab.app.model.Player;
import com.hishab.app.model.PlayerTest;
import com.hishab.app.repository.PlayerRepository;

@SpringBootTest
public class PlayerServiceTest {

	@Autowired
	private PlayerService playerService;

	@MockBean
	private PlayerRepository playerRepository;

	@Test
	public void getPlayers_whenValidRequest_returnsValidData() throws Exception {
		List<Player> mockPlayers = new ArrayList<Player>();
		mockPlayers.add(PlayerTest.player());

		when(playerRepository.getPlayers()).thenReturn(mockPlayers);

		List<Player> players = playerService.getPlayers();

		assertThat(players.get(0).getPlayerId()).isEqualTo(1);
		assertThat(players.get(0).getName()).isEqualTo("Julkar Nain");
		assertThat(players.get(0).getAge()).isEqualTo("38");
		assertThat(players.get(0).getScore()).isEqualTo(0);
		assertThat(players.get(0).getIsEnrolled()).isEqualTo(false);

	}

}
