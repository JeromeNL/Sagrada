package view;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class LobbyUserView extends StackPane {

	
///////////////////////////////////////////////////////////////// Instance Variables
	
private Label availableGamesTitle, waitingGameTitle, waitingGameDescription, backButtonText, createGameButtonText;
private Rectangle createGameButton, backButton, panel;
private LobbyLineView lobbyLineView;
private LobbyWaitLineView lobbyWaitLineView;
private LobbyViewInGame lobbyViewInGame;
private DropShadow dropShadow;

///////////////////////////////////////////////////////////////// Constructor	
	
	public LobbyUserView() {

		lobbyLineView = new LobbyLineView();
		lobbyWaitLineView = new LobbyWaitLineView();
		lobbyViewInGame = new LobbyViewInGame();
		
		panel = new Rectangle(400,400,600,500);
		panel.setArcHeight(50);
		panel.setArcWidth(50);
		panel.setFill(Color.WHITE);
		
		availableGamesTitle = new Label("Vul gebruikernaam in");
		availableGamesTitle.setTextFill(Color.PINK);
		availableGamesTitle.setFont(new Font("Arial", 30));
		availableGamesTitle.setStyle("-fx-font-weight: bold");
		availableGamesTitle.setTranslateY(-180);
			
		
		createGameButton = new Rectangle(400,400,155,50);
		createGameButton.setArcHeight(50);
		createGameButton.setArcWidth(50);
		createGameButton.setFill(Color.WHITE);
		createGameButton.setTranslateY(200);
		createGameButton.setTranslateX(170);
		createGameButton.setStrokeWidth(5);
		createGameButton.setStroke(Color.PINK);
		 
		createGameButtonText = new Label("Creëer Spel");
		createGameButtonText.setTextFill(Color.PINK);
		createGameButtonText.setFont(new Font("Arial", 18));
		createGameButtonText.setStyle("-fx-font-weight: bold");
		createGameButtonText.setTranslateY(200);
		createGameButtonText.setTranslateX(169);
		 
		waitingGameTitle = new Label("Wachten Op Andere");
	
			
		
this.getChildren().addAll(panel,availableGamesTitle);
setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

		
	}

}