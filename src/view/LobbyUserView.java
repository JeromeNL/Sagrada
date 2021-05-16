package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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
private TextField tfName;
///////////////////////////////////////////////////////////////// Constructor	
	
	public LobbyUserView() {
		tfName = new TextField();
		tfName.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(110), Insets.EMPTY)));
		tfName.setPromptText("Username");
		tfName.setStyle("-fx-border-color: pink;\r\n" + " -fx-border-width: 0 0 3 0;\r\n"
 				+ "    -fx-underline: true;" + "-fx-prompt-text-fill: pink;");
		tfName.setMaxWidth(400);
		tfName.setMinHeight(50);
		tfName.setFont(new Font("Arial", 20));
		
		Label addedUser1 = new Label("Janique is toegevoegd"); 
		Label addedUser2 = new Label("Joram is toegevoegd"); 
		Label addedUser3 = new Label("Jasper is toegevoegd"); 
		
		addedUser1.setTranslateX(-135);
		addedUser1.setTranslateY(60);
		addedUser1.setTextFill(Color.LIGHTBLUE);
		
		addedUser2.setTranslateX(-137);
		addedUser2.setTranslateY(100);
		addedUser2.setTextFill(Color.LIGHTBLUE);
		
		addedUser3.setTranslateX(-135);
		addedUser3.setTranslateY(140);
		addedUser3.setTextFill(Color.LIGHTBLUE);
	
		
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
	
			
		
this.getChildren().addAll(panel,availableGamesTitle,tfName,addedUser1,addedUser2,addedUser3);
setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

		
	}

}