package view;

import controller.ChoosePatternCardController;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Die;
import model.DiesInSupply;
import model.Game;

import model.GameColor;

import model.Patterncard;

import model.Player;

public class MainScene extends Scene {

///////////////////////////////////////////////////////////////// Constructor

	private MainController mainController;
	private Patterncard patterncard;
	private ChoosePatternCardController choosePatternCardController;
	private ChoosePatternCardView choosePatterncardView;
	private boolean noCard;

	public MainScene(MainController mainController) {
		super(new Pane(), 1280, 720);
		this.mainController = mainController;

//		LobbyView lobbyView = new LobbyView();
//		setRoot(lobbyView);


	}

	// show gameview of player of game with nr 1,2,3 or 4.
	public void showGame(Game game, int playerNR) {

		Player playerShownOnScreen = game.getPlayers().get(playerNR); // creator of the game
		GameView gameView = new GameView(game, playerShownOnScreen, mainController); 
		setRoot(gameView);

	}
	
	
	public void showChoosePatternCard(ChoosePatternCardController choosePatternCardController) {
		choosePatterncardView = new ChoosePatternCardView(choosePatternCardController);
		setRoot(choosePatterncardView);


	}
	
	public void changeCurrentPlayerView(Game game) {
		setRoot(new ChangeCurrentPlayerView(game, mainController));
//		ToolCardInUseView t = new ToolCardInUseView();
//		setRoot(t);

	}
	
	public ChoosePatternCardView getChoosePatternCardView() {
		return choosePatterncardView;
	}
}
