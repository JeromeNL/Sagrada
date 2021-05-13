package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class GameButtonView extends HBox {

	private Circle backButton, toolCardButton1,beerButton, toolCardButton2, toolCardButton3, confirmButton, menuButton,logoutButton,cheatButton;
	Label score, privateScore;
	
	GameButtonView(GameView gameView) {
		
		score = new Label(" Public Score  = 1234\n Private Score = 1234");
		privateScore = new Label("Favor Tokens = 1234");
		                          
		
		
		backButton = new GameButton();
		toolCardButton1 = new GameButton();
		toolCardButton1.setOnMouseClicked(e -> {
			gameView.showToolCardView();
		});
		toolCardButton2 = new GameButton();
		toolCardButton3 = new GameButton();
		confirmButton = new GameButton();
		menuButton = new GameButton();
		cheatButton = new GameButton();
		logoutButton = new GameButton();
		beerButton = new GameButton();

		setAlignment(Pos.CENTER);
		setSpacing(40);
		setPadding(new Insets(40));
		getChildren().addAll(privateScore,backButton, toolCardButton1, toolCardButton2, toolCardButton3,beerButton, confirmButton,menuButton,logoutButton,cheatButton,score);
	}
}
