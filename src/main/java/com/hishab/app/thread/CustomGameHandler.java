package com.hishab.app.thread;

import com.hishab.app.constants.ApplicationConstants;
import com.hishab.app.model.Player;
import com.hishab.app.persistence.PersistenceDB;
import com.hishab.app.service.DiceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomGameHandler implements Runnable {

	private final DiceService diceService;
	private final String gameId;

	//@Value("${game.winning.score}")
	//private String winningScore;

	@Override
	public void run() {
		
		ApplicationConstants.GAME_RUNNING_STATUS = true;
		ApplicationConstants.GAME_RUNNING_FlAG = true;
		int playerId = 1;
		
		try {
			PersistenceDB.playerList.get(playerId);
			while (ApplicationConstants.GAME_RUNNING_FlAG) {
				if (playerId > PersistenceDB.playerMap.size()) {
					playerId = 1;
				}
				Player runningPlayer = PersistenceDB.playerMap.get(playerId);
				Integer scoreValue = diceService.getScoreValue();
				if (!runningPlayer.getIsEnrolled() && scoreValue == 6) {
					Integer repeatScoreValue = diceService.getScoreValue();
					runningPlayer.setIsEnrolled(true);
					runningPlayer.setScore(repeatScoreValue);
					log.info("Player Name : {}, Score : {}, Repeat Score :{}, Total Score : {} ", runningPlayer.getName(), scoreValue, repeatScoreValue, runningPlayer.getScore());
				} else if (runningPlayer.getIsEnrolled() && scoreValue == 6) {
					Integer repeatScoreValue = diceService.getScoreValue();
					runningPlayer.setScore(runningPlayer.getScore() + scoreValue + repeatScoreValue);
					log.info("Player Name : {}, Score : {}, Repeat Score :{}, Total Score : {} ", runningPlayer.getName(), scoreValue, repeatScoreValue, runningPlayer.getScore());
				} 
				else if (runningPlayer.getIsEnrolled() && scoreValue == 4) {
					runningPlayer.setScore(runningPlayer.getScore() - scoreValue);
					log.info("Player Name : {}, Score : {}, Total Score : {} ", runningPlayer.getName(), scoreValue, runningPlayer.getScore());
				} else if (runningPlayer.getIsEnrolled()) {
					runningPlayer.setScore(runningPlayer.getScore() + scoreValue);
					log.info("Player Name : {}, Score : {}, Total Score : {} ", runningPlayer.getName(), scoreValue, runningPlayer.getScore());
				}
				if (runningPlayer.getScore() >= ApplicationConstants.GAME_WINNING_SCORE) {
					log.info("Highest Score #{} is achieved by {} for the Game # {} ", runningPlayer.getScore(), runningPlayer.getName(), gameId);
					ApplicationConstants.GAME_RUNNING_FlAG = false;
					ApplicationConstants.GAME_RUNNING_STATUS = false;
					log.info("Game # {} is finished. Please start again.........", gameId);
				}
				PersistenceDB.playerMap.put(playerId, runningPlayer);
				playerId++;
			}
			// log.info("Score : " + scoreMap.get(name));
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch(NumberFormatException ex) {
			throw ex;
		}

		// log.info("Ended thread " + name);
	}

}
