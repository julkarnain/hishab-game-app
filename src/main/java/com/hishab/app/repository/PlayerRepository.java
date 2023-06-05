package com.hishab.app.repository;

import java.util.List;

import com.hishab.app.model.Player;

public interface PlayerRepository {

	Player addPlayer(Player player);
	List<Player> getPlayers();
}
