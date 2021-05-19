package controller;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Game;
import view.MainScene;

public class MainController {
	
	private DatabaseController dbController;
	private Game currentGame;
	private String loggedInUsername = "";
	private MainScene mainScene;
	private String imageURL;
	private String combinedURL;

	public MainController(Stage stage) {
		dbController = new DatabaseController();
		mainScene = new MainScene(this);
		combinedURL = "/Images/Compact Private Objectives/green.png";
		imageURL = combinedURL.toString();
		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
		
		login("piet");
		createGame();
		currentGame.getPlayers().get(0).setPatternCard(10); // set patterncard for logged in user
		currentGame.invitePlayer("jerome");
		currentGame.getPlayers().get(1).setPatternCard(1); // set patterncard for invited player
		showGame();
		
		DieBagModel dieBagModel = new DieBagModel();
//		t.randomizeColor();
//		t.randomizeNumber();
		dieBagModel.fillingTheBag();

		stage.setTitle("SOPRJ4 Sagrada - Groep R");
		stage.setResizable(false);
		stage.getIcons().add(new Image("/Images/Compact Public Objectives/4.png"));
		stage.setScene(mainScene);
		stage.getIcons().add(toolCardImage);
		stage.show();
	}
	
	// Gets called when a user logs in.
	// TODO: should be called by a view instead of constructor
	public void login(String username) {
		loggedInUsername = username;
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
