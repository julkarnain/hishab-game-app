package com.hishab.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Player {
	private Integer playerId;
	private String name;
	private String age;
	private Integer score;
	private Boolean isEnrolled;
}
