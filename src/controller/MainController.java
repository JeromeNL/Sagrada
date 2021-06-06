package controller;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Game;
import model.Player;
import model.Refresh;
import model.RegisterLoginModel;
import view.AcceptDenyView;
import view.EndScoreView;
import view.MainScene;
import view.NewGameView;
import view.YourGamesView;

public class MainController {

	private DatabaseController dbController;
	private Game currentGame;

	private String loggedInUsername = "";
	private MainScene mainScene;

	private String imageURL;
	private String combinedURL;



//	public MainController(Stage stage) {
//		dbController = new DatabaseController();
//		mainScene = new MainScene(this);
//		combinedURL = "/Images/Compact Private Objectives/green.png";
//		imageURL = combinedURL.toString();
//		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
//		
//		login("piet");
//	}

	private ChoosePatternCardController choosePatternCardController;

	private Refresh refreshThread;


		

	public MainController(Stage stage) {
		dbController = new DatabaseController(this);
		RegisterLoginController rlc = new RegisterLoginController(dbController);
		RegisterLoginModel rlm = new RegisterLoginModel(this, rlc);
		mainScene = new MainScene(this, dbController, rlm, rlc);
		combinedURL = "/Images/Compact Private Objectives/green.png";
		imageURL = combinedURL.toString();
		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());

		showLoginScreen();
//		showPlayerListView();
//		showPlayedGames();

		refreshThread = new Refresh(currentGame, this, dbController);
		refreshThread.start();


		// mainScene.getChoosePatternCardView().getCard(); //will give you an int after
		// you clicked on kiezen

		stage.setTitle("SOPRJ4 Sagrada - Groep R");
		stage.setResizable(false);
		stage.getIcons().add(new Image("/Images/Compact Public Objectives/4.png"));
		stage.setScene(mainScene);
		stage.getIcons().add(toolCardImage);
		stage.setOnCloseRequest(e -> dbController.closeConnection());

		stage.show();
	}

	// Gets called when a user logs in.
	// TODO: should be called by a view instead of constructor
	public void login(String username) {
		loggedInUsername = username;
	}

	public void loadGame(int idGame) {
		currentGame = new Game(idGame, dbController, this);
		refreshThread.setGame(currentGame);
	}

	// Creates a new game with the loggedInUsername as the owner of the game.
	// TODO: should be called by lobby create game view.
	public void createGame() {
		currentGame = new Game(loggedInUsername, dbController, this);
		refreshThread.setGame(currentGame);
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

	public void showLoginView() {
		mainScene.showLoginView();
	}

	public void showLoginScreen() {
		mainScene.showLoginScreen();
	}

	public void showFirstMainMenu() {
		mainScene.showFirstMainMenu();
	}

	public void showOpenChallenges() {
		mainScene.setRoot(new AcceptDenyView(this, dbController));
	}

	public void showYourGames() {
		mainScene.setRoot(new YourGamesView(this, dbController));
	}

	public Game getCurrentGame() {
		return currentGame;
	}

	public void showNewGame() {
		mainScene.setRoot(new NewGameView(this, dbController));
	}

	public void logout() {
		loggedInUsername = "";
		showLoginScreen();
	}

	public void showEndScoreView(int player1, int player2, int player3, int player4, ArrayList<Player> players) {
		mainScene.setRoot(new EndScoreView(player1, player2, player3, player4, players ));

	}

	public void showPlayerListView() {
		mainScene.showPlayerListView();
	}

	public void showPlayedGames() {
		mainScene.showPlayedGames();
	}


}



