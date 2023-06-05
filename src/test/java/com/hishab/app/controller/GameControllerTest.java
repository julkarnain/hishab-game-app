package com.hishab.app.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.hishab.app.model.ScoreBoard;
import com.hishab.app.model.ScoreBoardTest;
import com.hishab.app.service.GameService;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	GameService gameService;
	
	@Test
	public void getPlayers_whenValidRequest_returnsValidResponse() throws Exception {
		
		List<ScoreBoard> scoreBoards = new ArrayList<ScoreBoard>();
		scoreBoards.add(ScoreBoardTest.scoreBoard());
		
		when(gameService.checkCurrentScoreBoard()).thenReturn(scoreBoards);
		
		mockMvc.perform(get("/api/1.0/game/current-scoreboard"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.[0].name", equalTo("Julkar Nain")))
		.andExpect(jsonPath("$.[0].score", equalTo(25)));
	}
	
	@Test
	public void checkScoreBoard_whenUnsupportedHttpVerb_returnsForbidden() throws Exception {
		mockMvc.perform(post("/api/1.0/game/current-scoreboard"))
		.andExpect(status().isMethodNotAllowed());
		//.andExpect(jsonPath("$.message", equalTo("Method Not Allowed")))
		//.andExpect(jsonPath("$.debugMessage", equalTo("Request method 'POST' not supported")));
	}
}
