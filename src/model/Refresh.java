package model;

import controller.DatabaseController;
import controller.MainController;
import javafx.application.Platform;

public class Refresh extends Thread {
	
	private Game game;
	private MainController mainController;
	private DatabaseController dbController;
	
	public Refresh(Game game, MainController mainController, DatabaseController dbController) {
		this.game = game;
		this.mainController = mainController;
		this.dbController = dbController;
		this.setDaemon(true);
	}
	
	public void run() {
		while(true) {
			int gameID = game.getIdGame();
			int currentPlayerID = dbController.getCurrentPlayerID(gameID);
			int currentRoundID = dbController.getRoundID(gameID);
			
			try {
				Thread.sleep(5 * 1000); // refresh game every 5 seconds
				
				int newPlayerID = dbController.getCurrentPlayerID(gameID);
				int newRoundID = dbController.getRoundID(gameID);
				
				if ((currentPlayerID != newPlayerID) || (currentRoundID != newRoundID)) {
					Platform.runLater(new Runnable( ) {

						@Override
						public void run() {
							mainController.loadGame(gameID);
							mainController.showGameLoggedInPlayer();
						}
					});					
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
