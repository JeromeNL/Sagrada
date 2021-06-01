package model;

import controller.DatabaseController;
import controller.MainController;
import javafx.application.Platform;

public class Refresh extends Thread {
	
	private MainController mainController;
	private DatabaseController dbController;
	
	private Game game;
	private int gameID;
	private int lastPlayerID;
	private int lastRoundID;
	
	public Refresh(Game game, MainController mainController, DatabaseController dbController) {
		this.game = game;
		this.mainController = mainController;
		this.dbController = dbController;
		this.setDaemon(true);
		
		if (game != null) {
			gameID = game.getGameID();
			lastPlayerID = dbController.getCurrentPlayerID(gameID);
			lastRoundID = dbController.getRoundID(gameID);
		}
	}
	
	public void setGame(Game game) {
		this.game = game;
		gameID = game.getGameID();
		lastPlayerID = dbController.getCurrentPlayerID(gameID);
		lastRoundID = dbController.getRoundID(gameID);
	}
	
	public void run() {
		    
		while(true) {
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (game != null && !dbController.isClosed()) {
				int newPlayerID = dbController.getCurrentPlayerID(gameID);
				int newRoundID = dbController.getRoundID(gameID);
				
				if ((lastPlayerID != newPlayerID) || (lastRoundID != newRoundID)) {
					Platform.runLater(new Runnable( ) {

						@Override
						public void run() {
							lastPlayerID = newPlayerID;
							lastRoundID = newRoundID;
							
							mainController.loadGame(gameID);
							mainController.showGameLoggedInPlayer();
						}
					});					
				}		
				
			}

		}
	}
}
