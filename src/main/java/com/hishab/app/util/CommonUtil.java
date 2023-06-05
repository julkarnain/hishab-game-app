package com.hishab.app.util;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	private static int topScore;

	public static int getTopScore() {
		return topScore;
	}

	public static void setTopScore(int topScore) {
		CommonUtil.topScore = topScore;
	}
}
