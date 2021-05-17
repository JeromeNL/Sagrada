package controller;

import javafx.stage.Stage;
import model.Game;
import view.MainScene;

public class MainController {
	
	private DatabaseController dbController;
	private Game currentGame;
	private String loggedInUsername = "";
	private MainScene mainScene;

	public MainController(Stage stage) {
		dbController = new DatabaseController();
		mainScene = new MainScene(this);		
		
		login("janique");
//		loadGame(450);
		createGame();
		currentGame.getPlayers().get(0).setPatternCard(10); // set patterncard for logged in user
		currentGame.invitePlayer("jasper");
		currentGame.getPlayers().get(1).setPatternCard(1); // set patterncard for invited player
		currentGame.invitePlayer("mandy");
		currentGame.invitePlayer("imke");
		currentGame.startGame();
		showGame();
		
//		DieBagModel dieBagModel = new DieBagModel();
//		t.randomizeColor();
//		t.randomizeNumber();
//		dieBagModel.fillingTheBag();

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
		currentGame = new Game(idGame, dbController);
	}
	
	// Creates a new game with the loggedInUsername as the owner of the game.
	// TODO: should be called by lobby create game view.
	public void createGame() {
		currentGame = new Game(loggedInUsername, dbController);
	}

	// Show the game of logged in user
	public void showGame() {
		mainScene.showGame(currentGame, 0);
	}
}
