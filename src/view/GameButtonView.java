package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Game;

public class GameButtonView extends HBox {

	private Circle backButton, toolCardButton1,beerButton, toolCardButton2, toolCardButton3, confirmButton, menuButton,logoutButton,cheatButton;
	private Label score, privateScore;
	
	GameButtonView(GameView gameView, Game game, MainController mainController) {
		
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



		confirmButton.setFill(Color.GREENYELLOW);
		confirmButton.setOnMouseEntered(e -> confirmButton.setFill(Color.GREEN));
		confirmButton.setOnMouseExited(e -> confirmButton.setFill(Color.GREENYELLOW));
		confirmButton.setOnMouseClicked(e-> {game.setNextTurn();
		gameView.showGame();
		});
		
		menuButton = new GameButton();
		cheatButton = new GameButton();
		logoutButton = new GameButton();
		beerButton = new GameButton();


		setAlignment(Pos.CENTER);
		setSpacing(40);
		setPadding(new Insets(40));
		getChildren().addAll(privateScore,backButton, toolCardButton1, toolCardButton2, toolCardButton3,
				
				
				
				
				confirmButton,score);
	}
}
