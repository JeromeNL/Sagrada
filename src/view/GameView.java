package view;

import controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.Game;
import model.Patterncard;
import model.Player;

public class GameView extends BorderPane {

	private RoundtrackView roundtrackView;
	private PatternCardView patternCardView;
	private DieSupply dieSupply;
	private GameButtonView gameButtonView;
	private ObjectiveInGameView objectiveInGameView;
	private ChangeCurrentPlayerView changeCurrentPlayerView;
	private Game game;
	private Player player;
	private MainController mainController;

	public GameView(Game game, Player player, MainController mainController) {
		this.game = game;
		this.player = player;
		this.mainController = mainController;
		Patterncard playerPatterncard = player.getPatterncard();

		objectiveInGameView = new ObjectiveInGameView();
		patternCardView = new PatternCardView(playerPatterncard);

		roundtrackView = new RoundtrackView();
		dieSupply = new DieSupply(game.getDiesInSupply());
		gameButtonView = new GameButtonView(this);
		changeCurrentPlayerView = new ChangeCurrentPlayerView(game, mainController);

		setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

		setAlignment(roundtrackView, Pos.TOP_CENTER);

		showGame();

	}

	public void showChangeCurrentPlayerView() {
		getChildren().clear();
		setCenter(changeCurrentPlayerView);
	}

	public void showGame() {
		getChildren().clear();
		setTop(roundtrackView);
		VBox leftPane = new VBox();
		leftPane.setSpacing(25);
		leftPane.getChildren().addAll(new ChangePlayerButton(), objectiveInGameView);
		setLeft(leftPane);
		setCenter(patternCardView);
		VBox vBox = new VBox(dieSupply, gameButtonView);
		setBottom(vBox);
	}

	public void showToolCardView() {
		getChildren().clear();
		setCenter(new ToolCardInUseView());
	}

	private class ChangePlayerButton extends HBox {

		public ChangePlayerButton() {
			setSpacing(10);
			setAlignment(Pos.CENTER);
			
			setOnMouseClicked(e -> showChangeCurrentPlayerView());
			
			Rectangle dropDownButton = new Rectangle(30, 30);
			
			Label currentPlayerText = new Label(player.getUsername());
			currentPlayerText.setStyle("-fx-font-weight: bold");
			currentPlayerText.setFont(new Font("Arial", 40));
			
			getChildren().addAll(dropDownButton, currentPlayerText);
		}
	}

}
