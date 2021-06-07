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
import javafx.scene.layout.StackPane;
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
	private StackPane card;
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

		chatpane = new ChatPane(player, dbController);

		patternCardView = new PatternCardView(player.getPatterncard());
		privateObjective = new CompactPrivateObjectiveCardImage(player.getPrivateObjectiveCardColor().toString());
//		}

		topPart = new TopPart(dbController, game);

		dieSupply = new DieSupply(game.getDiesInSupply(), player);
		gameButtonView = new GameButtonView(this, game, mainController);
		changeCurrentPlayerView = new ChangeCurrentPlayerView(game, mainController);

		if (!mainController.getLoggedInUsername().equals(player.getUsername())) {
			chatpane.setDisable(true);
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
		} else if (player.getPatterncard() != null
				|| !player.getUsername().equals(mainController.getLoggedInUsername())) {
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

		setTop(topPart);

		Button backToMenu = new Button("Terug naar menu");
		backToMenu.setStyle("-fx-background-color: #ffffff");
		backToMenu.setOnAction(e -> mainController.showFirstMainMenu());

		ArrayList<Integer> objectiveIDs = game.getPublicObjectives();
		VBox objectives = new VBox();
		objectives.setSpacing(5);
		objectives.getChildren().add(privateObjective);
		for (Integer id : objectiveIDs) {
			objectives.getChildren().add(new CompactPublicObjectiveCardImage(id));
		}

		VBox leftPane = new VBox();
		leftPane.setPadding(new Insets(0, 0, 0, 10));
		leftPane.setMinWidth(400);
		leftPane.getChildren().addAll(new ChangePlayerButton(), backToMenu, objectives);

		setLeft(leftPane);


		showPatternCardViewFavorToken();
		setCenter(card);


		setRight(chatpane);

		VBox bottomPane = new VBox(dieSupply, gameButtonView);
		setBottom(bottomPane);
	}

	public void showToolCardView() {
		getChildren().clear();
		setCenter(new ToolCardInUseView(mainController));
	}
	
	public void showPatternCardViewFavorToken() {
		Rectangle rectangleCard = new Rectangle(350, 350);
		rectangleCard.setFill(Color.WHITE);
		rectangleCard.setArcWidth(29.0);
		rectangleCard.setArcHeight(29.0);

		card = new StackPane();
		BorderPane favortokenCard = new BorderPane();
		FractalDropsView favortokenView = new FractalDropsView(
		game.getAmountFavorTokens(player.getIdPlayer()));
		favortokenCard.setCenter(patternCardView);
		favortokenCard.setBottom(favortokenView);
		favortokenCard.setMaxHeight(320);
//		favortokenCard.setPadding(new Insets(30, 5, 15, 5));
		
		card.getChildren().addAll(rectangleCard, favortokenCard);
		
	}

	public void refreshChat() {
		try {
			chatpane.refresh();
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
				who = new Label("(Andere Speler)");
			}

			who.setStyle("-fx-font-weight: bold");
			who.setFont(new Font("Arial", 20));

			getChildren().addAll(dropDownButton, currentPlayerText, who);

			setOnMouseEntered(e -> currentPlayerText.setUnderline(true));
			setOnMouseExited(e -> currentPlayerText.setUnderline(false));
		}
	}

}
