package view;

import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Game;
import model.Patterncard;
import model.Player;

public class MainScene extends Scene {

///////////////////////////////////////////////////////////////// Constructor

	private MainController mainController;
	private Patterncard patterncard;

	public MainScene(MainController mainController) {
		super(new Pane(), 1280, 720);
		this.mainController = mainController;

		
		
//  	LobbyView lobbyView = new LobbyView();
//		setRoot(lobbyView);
		
		// showChoosePatternCard();
	}

	// show gameview of player of game with nr 1,2,3 or 4.
	public void showGame(Game game, int playerNR) {
		Player playerShownOnScreen = game.getPlayers().get(playerNR); // creator of the game
		GameView gameView = new GameView(game, playerShownOnScreen);
		setRoot(gameView);


	}
	
//	public void showChoosePatternCard() {
//		ChoosePatternCardView choosePatterncardView = new ChoosePatternCardView();
//		setRoot(choosePatterncardView);

		
//		ToolCardInUseView t = new ToolCardInUseView();
//		setRoot(t);
	}
//}
