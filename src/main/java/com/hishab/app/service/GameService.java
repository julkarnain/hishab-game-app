package com.hishab.app.service;

import java.util.List;

import com.hishab.app.model.ScoreBoard;

public interface GameService {

	void setUpPlayers();
	List<ScoreBoard> checkCurrentScoreBoard();
	
}
