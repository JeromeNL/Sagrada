package controller;

import java.util.ArrayList;

import javafx.stage.Stage;
import model.DieBagModel;
import model.Game;
import model.Player;
import view.MainScene;

public class MainController {

	private DatabaseController dbController;
	private Game currentGame;
	private String loggedInUsername = "";
	private MainScene mainScene;

	public MainController(Stage stage) {
		dbController = new DatabaseController();
		mainScene = new MainScene(this, dbController);

		login("mandy");
//		loadGame(759);
		createGame();
		currentGame.getPlayers().get(0).setPatternCard(10); // set patterncard for logged in user
		currentGame.invitePlayer("jasper");
		currentGame.getPlayers().get(1).setPatternCard(1); // set patterncard for invited player

//		showChoosePatternCard();

		currentGame.invitePlayer("jerome");
		currentGame.getPlayers().get(2).setPatternCard(3); // set patterncard for invited player
		currentGame.invitePlayer("imke");
		currentGame.getPlayers().get(3).setPatternCard(8); // set patterncard for invited player
		currentGame.startGame();

		showGameLoggedInPlayer();
//		showLoginView();

		stage.setTitle("SOPRJ4 Sagrada - Groep R");
		stage.setResizable(false);
		stage.setScene(mainScene);
		stage.setOnCloseRequest(e->dbController.closeConnection());
		stage.show();
	}

	// Gets called when a user logs in.
	// TODO: should be called by a view instead of constructor
	public void login(String username) {
		loggedInUsername = username;
	}

	public void loadGame(int idGame) {
		currentGame = new Game(idGame, dbController, this);
	}

	// Creates a new game with the loggedInUsername as the owner of the game.
	// TODO: should be called by lobby create game view.
	public void createGame() {
		currentGame = new Game(loggedInUsername, dbController, this);
	}

	// Shows game of logged in player
	public void showGameLoggedInPlayer() {
		ArrayList<Player> players = currentGame.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equals(loggedInUsername)) {
				mainScene.showGame(currentGame, i);
				return;
			}
		}
	}

	// Show the game of a player in the game (0 is first player) e.g. 0, 1, 2, 3
	public void showGame(int playernr) {
		mainScene.showGame(currentGame, playernr);
	}

	public String getLoggedInUsername() {
		return loggedInUsername;
	}

	public void showChoosePatternCard() {
		mainScene.showChoosePatternCard();
	}
	
	public void showLoginView() {
		mainScene.showLoginView();
	}

}
