package com.hishab.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishab.app.constants.ApplicationConstants;
import com.hishab.app.model.ResponseStatus;
import com.hishab.app.model.ScoreBoard;
import com.hishab.app.persistence.PersistenceDB;
import com.hishab.app.service.DiceService;
import com.hishab.app.service.GameService;
import com.hishab.app.thread.CustomGameHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/1.0/game")
public class GameController {

	@Autowired
	private GameService gameService;

	private final DiceService diceService;

	@GetMapping(value = "/start/{gameId}")
	public ResponseEntity<ResponseStatus> startGame(@PathVariable String gameId) {

		ResponseStatus responseStatus = new ResponseStatus();

		try {
			
			if (PersistenceDB.playerList.size() >= 2 && PersistenceDB.playerList.size() <= 4) {
				
				if (!ApplicationConstants.GAME_RUNNING_STATUS) {
					
					log.info("Game is starting.........#{}",gameId);

					gameService.setUpPlayers();

					CustomGameHandler customGameHandler = new CustomGameHandler(diceService,gameId);
					Thread thread = new Thread(customGameHandler);
					thread.start();

					responseStatus.setStatusType("Normal");
					responseStatus.setStatusCode("N001");
					responseStatus.setStatusMessage("Game is started successfully");
					
				} else {
					responseStatus.setStatusType("Normal");
					responseStatus.setStatusCode("N002");
					responseStatus.setStatusMessage("One Game is already running. Please wait until the Game is Over");
					log.info("One Game #{} is already running.........",gameId);
				}

			} else {
				responseStatus.setStatusType("Normal");
				responseStatus.setStatusCode("E001");
				responseStatus.setStatusMessage("Please fill-up/reduce player quota");
			}
		} catch (Exception ex) {
			throw ex;
		}

		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/current-scoreboard")
	public List<ScoreBoard> checkCurrentScoreBoard() {
		return gameService.checkCurrentScoreBoard();
	}

}
