package view;

import controller.DatabaseController;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Game;
import model.Player;

public class MainScene extends Scene {

///////////////////////////////////////////////////////////////// Constructor

	private MainController mainController;
	private DatabaseController dbController;

	public MainScene(MainController mainController, DatabaseController dbController) {
		super(new Pane(), 1280, 720);
		this.mainController = mainController;
		this.dbController = dbController;

//		LobbyView lobbyView = new LobbyView();
//		setRoot(lobbyView);

	}

	// show gameview of player of game with nr 1,2,3 or 4.

	public void showGame(Game game, int playerNR) {
		Player playerShownOnScreen = game.getPlayers().get(playerNR); // creator of the game
		GameView gameView = new GameView(game, playerShownOnScreen, mainController);
		setRoot(gameView);

	}

	public void showChoosePatternCard() {
		ChoosePatternCardView choosePatterncardView = new ChoosePatternCardView(dbController);
		setRoot(choosePatterncardView);

	}

	public void changeCurrentPlayerView(Game game) {
		setRoot(new ChangeCurrentPlayerView(game, mainController));

	}
}
