package com.hishab.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hishab.app.model.ScoreBoard;
import com.hishab.app.model.ScoreBoardTest;
import com.hishab.app.repository.GameRepository;

@SpringBootTest
public class GameServiceTest {

	@Autowired
	private GameService gameService;

	@MockBean
	private GameRepository gameRepository;

	@Test
	public void getPlayers_whenValidRequest_returnsValidData() throws Exception {
		List<ScoreBoard> mockScoresBoard = new ArrayList<ScoreBoard>();
		mockScoresBoard.add(ScoreBoardTest.scoreBoard());

		when(gameRepository.checkCurrentScoreBoard()).thenReturn(mockScoresBoard);

		List<ScoreBoard> scoresBoard = gameService.checkCurrentScoreBoard();

		assertThat(scoresBoard.get(0).getName()).isEqualTo("Julkar Nain");
		assertThat(scoresBoard.get(0).getScore()).isEqualTo(25);
	}

}
