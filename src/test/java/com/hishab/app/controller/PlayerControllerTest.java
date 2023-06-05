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

import com.hishab.app.model.Player;
import com.hishab.app.model.PlayerTest;
import com.hishab.app.service.PlayerService;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlayerService playerService;

	@Test
	public void getPlayers_whenValidRequest_returnsValidResponse() throws Exception {
		
		List<Player> players = new ArrayList<Player>();
		players.add(PlayerTest.player());
		
		when(playerService.getPlayers()).thenReturn(players);
		
		mockMvc.perform(get("/api/1.0/player/all"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.[0].playerId", equalTo(1)))
		.andExpect(jsonPath("$.[0].name", equalTo("Julkar Nain")))
		.andExpect(jsonPath("$.[0].age", equalTo("38")))
		.andExpect(jsonPath("$.[0].score", equalTo(0)))
		.andExpect(jsonPath("$.[0].isEnrolled", equalTo(false)));
	}

	@Test
	public void createPlayer_whenUnsupportedHttpVerb_returnsForbidden() throws Exception {
		mockMvc.perform(post("/api/1.0/player/all"))
		.andExpect(status().isMethodNotAllowed());
		//.andExpect(jsonPath("$.message", equalTo("Method Not Allowed")))
		//.andExpect(jsonPath("$.debugMessage", equalTo("Request method 'POST' not supported")));
	}
}
