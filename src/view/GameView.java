package view;


import java.sql.SQLException;
import java.util.ArrayList;

import controller.ChoosePatternCardController;
import controller.DatabaseController;
import controller.MainController;
import imageChooser.CompactPrivateObjectiveCardImage;
import imageChooser.CompactPublicObjectiveCardImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.Game;
import model.Player;

public class GameView extends BorderPane {

	private TopPart topPart;
	private PatternCardView patternCardView;
	private DieSupply dieSupply;
	private GameButtonView gameButtonView;
	private ChangeCurrentPlayerView changeCurrentPlayerView;
	private Game game;
	private Player player;
	private MainController mainController;
	private DatabaseController dbController;
	private CompactPrivateObjectiveCardImage privateObjective;
	private ChatPane chatpane;

	public GameView(Game game, Player player, MainController mainController, DatabaseController dbController) {

		this.game = game;
		this.player = player;
		this.mainController = mainController;
		this.dbController = dbController;
		
		patternCardView = new PatternCardView(player.getPatterncard());
//		if (player.getPrivateObjectiveCardColor() != null) {
			privateObjective = new CompactPrivateObjectiveCardImage(player.getPrivateObjectiveCardColor().toString());			
//		}
		
		topPart = new TopPart(game);

		dieSupply = new DieSupply(game.getDiesInSupply(), player);
		gameButtonView = new GameButtonView(this, game, mainController);
		changeCurrentPlayerView = new ChangeCurrentPlayerView(game, mainController);

		if (!mainController.getLoggedInUsername().equals(player.getUsername())) {
			dieSupply.setDisable(true);
			dieSupply.setOpacity(0.5);
			gameButtonView.setDisable(true);
			gameButtonView.setOpacity(0.5);
			privateObjective.hide();
		}

		setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

		setAlignment(topPart, Pos.TOP_CENTER);

		if (player.getPatterncard() == null && player.getUsername().equals(mainController.getLoggedInUsername())) {
			showPatternCardChooser();
		} else if(player.getPatterncard() != null || !player.getUsername().equals(mainController.getLoggedInUsername()) ) {
			showGame();
		}


	}

	private void disableElements() {
		dieSupply.setDisable(true);
		dieSupply.setOpacity(0.5);
		gameButtonView.setDisable(true);
		gameButtonView.setOpacity(0.5);
	}


	public void showChangeCurrentPlayerView() {
		getChildren().clear();
		setCenter(changeCurrentPlayerView);
	}
	
	public void showPatternCardChooser() {
		getChildren().clear();
		ChoosePatternCardController choosePatternCardController = new ChoosePatternCardController(dbController);
		setCenter(new ChoosePatternCardView(choosePatternCardController, mainController, player));
	}

	public void showGame() {
		// Disable elements when it's not the gameview of the logged in player
		if (!mainController.getLoggedInUsername().equals(player.getUsername())) {
			disableElements();
			privateObjective.hide();
		}
		
		// Disable elements when it's not the player's turn		
		if (!player.getUsername().equals(game.getCurrentPlayer())) {
			disableElements();
		}
		
		getChildren().clear();

		VBox topPane = new VBox();
		topPane.setAlignment(Pos.CENTER);
		topPane.getChildren().addAll(topPart, new InfoPane(game, dbController));

		setTop(topPane);

		Button backToMenu = new Button("Terug naar menu");
		backToMenu.setOnAction(e -> mainController.showFirstMainMenu());
		
		ArrayList<Integer> objectiveIDs = game.getPublicObjectives();
		VBox objectives = new VBox();
		objectives.setSpacing(5);
		objectives.getChildren().add(privateObjective);
		for (Integer id : objectiveIDs) {
			objectives.getChildren().add(new CompactPublicObjectiveCardImage(id));
		}
		
		VBox leftPane = new VBox();
		leftPane.setPadding(new Insets(0,0,0,10));
		leftPane.setMinWidth(400);
		leftPane.getChildren().addAll(new ChangePlayerButton(), backToMenu, objectives);
		
		setLeft(leftPane);

		setCenter(patternCardView);
			
		Pane rightPane = new Pane();
		rightPane.setMinWidth(400);
		setRight(rightPane);
		
		chatpane = new ChatPane(player, dbController);
		setRight(chatpane);
		
		VBox bottomPane = new VBox(dieSupply, gameButtonView);
		setBottom(bottomPane);
	}

	public void showToolCardView() {
		getChildren().clear();
		setCenter(new ToolCardInUseView(mainController));
	}
	
	public void refreshChat() {
		try {
			if (chatpane != null) {
				chatpane.refresh();				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class ChangePlayerButton extends HBox {

		public ChangePlayerButton() {
			setSpacing(10);
			setAlignment(Pos.BASELINE_LEFT);

			setOnMouseClicked(e -> showChangeCurrentPlayerView());

			Rectangle dropDownButton = new Rectangle(30, 30);

			Label currentPlayerText = new Label(player.getUsername());
			currentPlayerText.setStyle("-fx-font-weight: bold");
			currentPlayerText.setFont(new Font("Arial", 40));

			Label who;
			if (mainController.getLoggedInUsername().equals(player.getUsername())) {
				who = new Label("(You)");
			} else {
				who = new Label("(Other player)");
			}

			who.setStyle("-fx-font-weight: bold");
			who.setFont(new Font("Arial", 20));

			getChildren().addAll(dropDownButton, currentPlayerText, who);

			setOnMouseEntered(e -> currentPlayerText.setUnderline(true));
			setOnMouseExited(e -> currentPlayerText.setUnderline(false));
		}
	}

	// Temporary class to show game details while developing
	private class InfoPane extends HBox {

		public InfoPane(Game game, DatabaseController dbController) {

			setSpacing(30);
			setAlignment(Pos.CENTER);

			Label gameStatus = new Label("Gameid: " + game.getIdGame());

			int playerID = dbController.getCurrentPlayerID(game.getIdGame());
			String username = dbController.getUsername(playerID);
			Label currentPlayer = new Label("Current player: " + username + " (ID: " + playerID + ")");

			int roundID = dbController.getRoundID(game.getIdGame());
			boolean isClockwise = dbController.isClockwise(roundID);
			Label round = new Label("roundID: " + roundID + " (clockwise: " + isClockwise + ")" + " RoundNR: "
					+ dbController.getRoundNr(roundID));

			getChildren().addAll(gameStatus, currentPlayer, round);
		}

	}

}
