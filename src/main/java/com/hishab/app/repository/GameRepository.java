package com.hishab.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hishab.app.exceptionhandling.PlayerNotFoundException;
import com.hishab.app.model.Player;
import com.hishab.app.model.ScoreBoard;
import com.hishab.app.persistence.PersistenceDB;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GameRepository {

	public void setUpPlayers() {
		log.info("Player setup is initializing ........");
		List<Player> players = PersistenceDB.getPlayerList();
		players.forEach(player -> {
			player.setScore(0);
			player.setIsEnrolled(false);
			PersistenceDB.getPlayerMap().put(player.getPlayerId(), player);
		});
		log.info("Player setup is finished ........");
	}

	public List<ScoreBoard> checkCurrentScoreBoard() {
		Map<Integer, Player> scoreMap = PersistenceDB.getPlayerMap();
		List<ScoreBoard> scoresBoard = new ArrayList<ScoreBoard>();
		if (!scoreMap.isEmpty()) {
			scoreMap.values().forEach(v -> {
				ScoreBoard scoreBoard = new ScoreBoard();
				scoreBoard.setName(v.getName());
				scoreBoard.setScore(v.getScore());
				scoresBoard.add(scoreBoard);
			});
		} else {
			throw new PlayerNotFoundException("ScoreBoard Is Empty. No Game is Running");
		}
		return scoresBoard;
	}
}
