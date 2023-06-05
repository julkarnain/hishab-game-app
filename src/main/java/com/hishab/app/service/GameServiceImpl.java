package com.hishab.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hishab.app.model.ScoreBoard;
import com.hishab.app.repository.GameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

	private final GameRepository gameRepository;

	@Override
	public void setUpPlayers() {

		gameRepository.setUpPlayers();
	}

	@Override
	public List<ScoreBoard> checkCurrentScoreBoard() {

		return gameRepository.checkCurrentScoreBoard();
	}

}
