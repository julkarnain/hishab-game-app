package com.hishab.app.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.hishab.app.model.Player;

@Component
public class PersistenceDB {

	public static List<Player> playerList = new ArrayList<Player>();
	public static Map<Integer, Player> playerMap = new ConcurrentHashMap<Integer, Player>();

	public static List<Player> getPlayerList() {
		return playerList;
	}

	public static void setPlayerList(List<Player> playerList) {
		PersistenceDB.playerList = playerList;
	}

	public static Map<Integer, Player> getPlayerMap() {
		return playerMap;
	}

	public static void setPlayerMap(Map<Integer, Player> playerMap) {
		PersistenceDB.playerMap = playerMap;
	}
}
