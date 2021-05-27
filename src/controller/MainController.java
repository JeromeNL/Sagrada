package controller;

import java.util.ArrayList;

import javafx.stage.Stage;
import model.Game;
import model.Player;
import model.Refresh;
import model.RegisterLoginModel;
import view.MainScene;

public class MainController {
	
	private DatabaseController dbController;
	private Game currentGame;

	private String loggedInUsername = "";
	private MainScene mainScene;

	public MainController(Stage stage) {
		dbController = new DatabaseController();
		RegisterLoginModel rlm = new RegisterLoginModel(this);
		mainScene = new MainScene(this, dbController, rlm);		
		
		showLoginScreen();

		stage.setTitle("SOPRJ4 Sagrada - Groep R");
		stage.setResizable(false);
		stage.setScene(mainScene);
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
	
	public void showLoginScreen() {
		mainScene.showLoginScreen();
	}
	
	public Game getCurrentGame() {
		return currentGame;
	}
	
	public void showMainMenu() {
		mainScene.showMainMenu();
	}
	
}
