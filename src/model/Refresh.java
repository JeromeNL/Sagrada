package model;

import controller.DatabaseController;
import controller.GameController;
import controller.MainController;
import javafx.application.Platform;

public class Refresh extends Thread {

	private MainController mainController;
	private DatabaseController dbController;
	private GameController gameController;

	private Game game;
	private int gameID;
	private int lastPlayerID;
	private int lastRoundID;

	public Refresh(Game game, MainController mainController, DatabaseController dbController) {
		this.game = game;
		this.mainController = mainController;
		this.dbController = dbController;
		this.setDaemon(true);

		gameController = new GameController(dbController, mainController);

		if (game != null) {
			gameID = game.getIdGame();
			lastPlayerID = dbController.getCurrentPlayerID(gameID);
			lastRoundID = dbController.getRoundID(gameID);
		}
	}

	public void setGame(Game game) {
		this.game = game;
		gameID = game.getIdGame();
		lastPlayerID = dbController.getCurrentPlayerID(gameID);
		lastRoundID = dbController.getRoundID(gameID);
	}

	public void run() {

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (game != null && !dbController.isClosed()) {
				int newPlayerID = dbController.getCurrentPlayerID(gameID);
				int newRoundID = dbController.getRoundID(gameID);

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int idGame = mainController.getCurrentGame().getIdGame();
						if (dbController.getRoundID(idGame) == 1) {
							mainController.refreshChat();
						}

					}
				});

				if ((lastPlayerID != newPlayerID) || (lastRoundID != newRoundID)) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							lastPlayerID = newPlayerID;
							lastRoundID = newRoundID;

							mainController.loadGame(gameID);
							mainController.showGameLoggedInPlayer();
						}
					});
					continue;
				}

				// Check if end score view needs to be
				if (lastRoundID == 20 && gameController.isFinished(gameID)) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							mainController.showEndScoreView();
						}
					});
				}

			}

		}
	}
}