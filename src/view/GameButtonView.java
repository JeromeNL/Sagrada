package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import model.Game;

public class GameButtonView extends HBox {

	private Circle backButton, toolCardButton1, toolCardButton2, toolCardButton3, confirmButton;

	GameButtonView(GameView gameView, Game game) {
		
		backButton = new GameButton();
		toolCardButton1 = new GameButton();
		toolCardButton1.setOnMouseClicked(e -> {
			gameView.showToolCardView();
		});
		toolCardButton2 = new GameButton();
		toolCardButton3 = new GameButton();
		confirmButton = new GameButton();
		confirmButton.setOnMouseClicked(e-> {game.setNextTurn();
		gameView.showGame();
		});
		setAlignment(Pos.CENTER);
		setSpacing(50);
		setPadding(new Insets(25));
		getChildren().addAll(backButton, toolCardButton1, toolCardButton2, toolCardButton3, confirmButton);
	}
}
