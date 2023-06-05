package com.hishab.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hishab.app.exceptionhandling.PlayerAlreadyExistsException;
import com.hishab.app.exceptionhandling.PlayerNotFoundException;
import com.hishab.app.model.Player;
import com.hishab.app.service.PlayerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RequestMapping("/api/1.0/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@PostMapping(value = "/add")
	public ResponseEntity<Player> addPlayer(@RequestBody Player player) throws PlayerAlreadyExistsException {

		log.info("Start to add new player : {}", player.getName());

		Player newPlayer = playerService.addPlayer(player);

		log.info("End to add new player : {}", player.getName());

		return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> getPlayers() throws PlayerNotFoundException {

		List<Player> players = playerService.getPlayers();

		return new ResponseEntity<>(players, HttpStatus.OK);

	}

}
