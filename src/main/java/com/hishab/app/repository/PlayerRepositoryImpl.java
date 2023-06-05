package com.hishab.app.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hishab.app.exceptionhandling.PlayerOverflowException;
import com.hishab.app.model.Player;
import com.hishab.app.persistence.PersistenceDB;

@Component
public class PlayerRepositoryImpl implements PlayerRepository {

	@Override
	public Player addPlayer(Player player) {

		Player newPlayer = new Player();

		if (PersistenceDB.playerList.size() <= 3) {
			newPlayer.setPlayerId(player.getPlayerId());
			newPlayer.setName(player.getName());
			newPlayer.setAge(player.getAge());
			PersistenceDB.playerList.add(newPlayer);
		} else {
			throw new PlayerOverflowException("Cross the Player quota. Please wait for next Game");
		}

		return newPlayer;

	}

	@Override
	public List<Player> getPlayers() {
		return PersistenceDB.playerList;
	}
}
