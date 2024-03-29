package view;

import controller.DatabaseController;
import controller.MainController;
import controller.RegisterLoginController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Game;
import model.Player;
import model.RegisterLoginModel;

public class MainScene extends Scene {

///////////////////////////////////////////////////////////////// Constructor

	private MainController mainController;
	private DatabaseController dbController;
	private RegisterLoginModel rlm;
	private RegisterLoginController registerLoginController;
	private GameView gameView;

	public MainScene(MainController mainController, DatabaseController dbController, RegisterLoginModel rlm,
			RegisterLoginController rlc) {
		super(new Pane(), 1280, 720);
		this.mainController = mainController;

		this.dbController = dbController;
		this.rlm = rlm;
		this.registerLoginController = rlc;
	}


	public void showGame(Game game, int playerNR) {
		Player playerShownOnScreen = game.getPlayers().get(playerNR); // creator of the game
		gameView = new GameView(game, playerShownOnScreen, mainController, dbController);
		setRoot(gameView);
	}

	public GameView getGameView() {
		return gameView;
	}


	public void changeCurrentPlayerView(Game game) {
		setRoot(new ChangeCurrentPlayerView(game, mainController));
	}

	public void showLoginScreen() {
		setRoot(new LoginView(dbController, registerLoginController, rlm));
	}

	public void showObjectivecardCardView() {
		ObjectivecardCardView objectivecardCardView = new ObjectivecardCardView(dbController, mainController);
		setRoot(objectivecardCardView);
	}

	public void showToolcardCardView() {
		ToolcardCardView toolcardCardView = new ToolcardCardView(dbController, mainController);
		setRoot(toolcardCardView);
	}

	public void showFirstMainMenu() {
		setRoot(new MainMenu(dbController, mainController));
	}

	public void showPlayerListView() {
		setRoot(new PlayerListView(dbController, mainController));
	}

	public void refreshChat() {
		gameView.refreshChat();
	}

	public void showPlayedGames() {
		setRoot(new PlayedGamesView(dbController, mainController));
	}

}
