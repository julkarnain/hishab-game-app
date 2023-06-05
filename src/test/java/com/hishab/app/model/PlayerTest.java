package com.hishab.app.model;

public class PlayerTest {

	public static Player player() {
		return Player.builder()
				.playerId(1)
				.name("Julkar Nain")
				.age("38")
				.score(0)
				.isEnrolled(false)
				.build();
	}
}
