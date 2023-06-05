package com.hishab.app.service;

import java.util.List;

import com.hishab.app.model.Player;

public interface PlayerService {

	Player addPlayer(Player player);
	List<Player> getPlayers();
}
