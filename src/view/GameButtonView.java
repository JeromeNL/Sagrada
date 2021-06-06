package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Game;

public class GameButtonView extends HBox {

	private Circle toolCardButton1, ObjectivecardButton, confirmButton;

	GameButtonView(GameView gameView, Game game, MainController mainController) {

		toolCardButton1 = new GameButton();
		toolCardButton1.setFill(Color.LIGHTBLUE);
		toolCardButton1.setOnMouseEntered(e -> toolCardButton1.setFill(Color.BLUE));
		toolCardButton1.setOnMouseExited(e -> toolCardButton1.setFill(Color.LIGHTBLUE));
		toolCardButton1.setOnMouseClicked(e -> {
			mainController.ToolCardButton();
			;
		});

		ObjectivecardButton = new GameButton();
		ObjectivecardButton.setFill(Color.RED);
		ObjectivecardButton.setOnMouseEntered(e -> ObjectivecardButton.setFill(Color.INDIANRED));
		ObjectivecardButton.setOnMouseExited(e -> ObjectivecardButton.setFill(Color.RED));
		ObjectivecardButton.setOnMouseClicked(e -> {
			mainController.ObjectiveCardButton();
			;
		});

		confirmButton = new GameButton();
		confirmButton.setFill(Color.GREENYELLOW);
		confirmButton.setOnMouseEntered(e -> confirmButton.setFill(Color.GREEN));
		confirmButton.setOnMouseExited(e -> confirmButton.setFill(Color.GREENYELLOW));
		confirmButton.setOnMouseClicked(e -> {
			game.setNextTurn();
			gameView.showGame();
		});

		setAlignment(Pos.CENTER);
		setSpacing(40);
		setPadding(new Insets(10));
		getChildren().addAll(toolCardButton1, ObjectivecardButton, confirmButton);
	}
}
