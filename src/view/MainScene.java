package view;

import controller.ChoosePatternCardController;
import controller.DatabaseController;
import controller.MainController;

import imageChooser.CompactPrivateObjectiveCardImage;
import imageChooser.CompactPublicObjectiveCardImage;
import imageChooser.PrivateObjectiveCardImage;
import imageChooser.PublicObjectiveCardImage;
import imageChooser.ToolcardCardImage;

import controller.RegisterLoginController;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Game;
import model.Patterncard;
import model.Player;

public class MainScene extends Scene {

///////////////////////////////////////////////////////////////// Constructor

	private MainController mainController;
	private Patterncard patterncard;
	private ChoosePatternCardController choosePatternCardController;
	private ChoosePatternCardView choosePatterncardView;
	private boolean noCard;
	private DatabaseController dbController;
	private RegisterLoginController registerLoginController;

	public MainScene(MainController mainController, DatabaseController dbController) {
		super(new Pane(), 1280, 720);
		this.mainController = mainController;
S
		this.dbController = dbController;

//		LobbyView lobbyView = new LobbyView();
//		setRoot(lobbyView);


	}

	// show gameview of player of game with nr 1,2,3 or 4.

	public void showGame(Game game, int playerNR) {

		Player playerShownOnScreen = game.getPlayers().get(playerNR); // creator of the game

		GameView gameView = new GameView(game, playerShownOnScreen,mainController); 
		setRoot(gameView);
		
	
		
		
	//////////// IMAGE CHOOSER EXAMPLE
	// constuctor can be change to load a certain image.
		
		
	// LATEN STAAN ALSJEBLIEFT
		
		
//		ToolcardCardImage toolcardCardImage = new ToolcardCardImage(3); 
//		setRoot(toolcardCardImage);
//		
//		PublicObjectiveCardImage publicObjectiveCardImage = new PublicObjectiveCardImage(3); 
//		setRoot(publicObjectiveCardImage);
//		
//		CompactPublicObjectiveCardImage compactPublicObjectiveCardImage = new CompactPublicObjectiveCardImage(3); 
//		setRoot(compactPublicObjectiveCardImage);
//		
//		PrivateObjectiveCardImage privateObjectiveCardImage = new PrivateObjectiveCardImage("green"); 
//		setRoot(privateObjectiveCardImage); 
//		
//		CompactPrivateObjectiveCardImage compactPrivateObjectiveCardImage = new CompactPrivateObjectiveCardImage("pink"); 
//		setRoot(compactPrivateObjectiveCardImage); 
		
	
	
		
		
		
		
	// LATEN STAAN ALSJEBLIEFT
	
		
		
		
		
		GameView gameView = new GameView(game, playerShownOnScreen, mainController);
		setRoot(gameView);

	}

	public void showLoginView() {
		RegisterLoginController registerLoginController = new RegisterLoginController(dbController);
		LoginView loginView = new LoginView(dbController, registerLoginController);
		setRoot(loginView);
	}

	public void showChoosePatternCard(ChoosePatternCardController choosePatternCardController) {
		choosePatterncardView = new ChoosePatternCardView(choosePatternCardController);
		setRoot(choosePatterncardView);

	}

	public void changeCurrentPlayerView(Game game) {
		setRoot(new ChangeCurrentPlayerView(game, mainController));
	}

	public ChoosePatternCardView getChoosePatternCardView() {
		return choosePatterncardView;

	}
	

	
}
