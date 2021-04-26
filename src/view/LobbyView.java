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

public class LobbyView extends StackPane {

	
///////////////////////////////////////////////////////////////// Instance Variables
	
private Label availableGamesTitle, waitingGameTitle, waitingGameDescription, backButtonText, createGameButtonText;
private Rectangle createGameButton, backButton, panel;
private LobbyLineView lobbyLineView;
private LobbyWaitLineView lobbyWaitLineView;
private LobbyViewInGame lobbyViewInGame;
private DropShadow dropShadow;

///////////////////////////////////////////////////////////////// Constructor	
	
	public LobbyView() {

		lobbyLineView = new LobbyLineView();
		lobbyWaitLineView = new LobbyWaitLineView();
		lobbyViewInGame = new LobbyViewInGame();
		
		panel = new Rectangle(400,400,600,500);
		panel.setArcHeight(50);
		panel.setArcWidth(50);
		panel.setFill(Color.WHITE);
		
		availableGamesTitle = new Label("Beschikbare Spellen");
		availableGamesTitle.setTextFill(Color.PINK);
		availableGamesTitle.setFont(new Font("Arial", 30));
		availableGamesTitle.setStyle("-fx-font-weight: bold");
		availableGamesTitle.setTranslateY(-180);
			
		dropShadow = new DropShadow();
		dropShadow.setRadius(20);
		dropShadow.setColor(Color.LIGHTGREY);		 
		 
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
		waitingGameTitle.setTextFill(Color.PINK);
		waitingGameTitle.setFont(new Font("Arial", 30));
		waitingGameTitle.setStyle("-fx-font-weight: bold");
		waitingGameTitle.setTranslateY(-180);
		 
	    waitingGameDescription = new Label("Het spel begint als alle spelers aanwezig zijn");
		waitingGameDescription.setTextFill(Color.PINK);
		waitingGameDescription.setFont(new Font("Arial", 20));
		waitingGameDescription.setTranslateY(-140);
			
	    backButton = new Rectangle(400,400,155,50);
		backButton.setArcHeight(50);
		backButton.setArcWidth(50);
		backButton.setFill(Color.WHITE);
		backButton.setTranslateY(200);
	    backButton.setTranslateX(170);
		backButton.setStrokeWidth(5);
		backButton.setStroke(Color.PINK);
			 
		backButtonText = new Label("Terug");
		backButtonText.setTextFill(Color.PINK);
		backButtonText.setFont(new Font("Arial", 18));
		backButtonText.setStyle("-fx-font-weight: bold");
		backButtonText.setTranslateY(200);
		backButtonText.setTranslateX(169);
			
		
		registerHandler1(createGameButton, Color.WHITE, Color.PINK);
		registerHandler2(backButton, Color.WHITE, Color.PINK);

		
///	Activates Reespective Actions	
		
		createGameButton.setOnMouseClicked(e -> {System.out.println("button is pressed");
		 
		this.getChildren().clear();
		this.getChildren().addAll(panel,waitingGameTitle,waitingGameDescription,lobbyViewInGame,lobbyWaitLineView,backButton,backButtonText);
		 
		});
		 
		 backButton.setOnMouseClicked(e -> {System.out.println("button is pressed");
		 
		 this.getChildren().clear();
		 this.getChildren().addAll(panel,availableGamesTitle, lobbyLineView, createGameButton,createGameButtonText);
		 });  	
	}
	
	    private void registerHandler1(Shape s, Color defaultColor, Color hoverColor) {
	        s.setOnMouseEntered( e -> {
	        	
	        	s.setFill(hoverColor);
	        	createGameButtonText.setTextFill(Color.WHITE);
	        });
	       
	        
	        s.setOnMouseExited(e -> {
	        	
	        	s.setFill(defaultColor);
	        	createGameButtonText.setTextFill(Color.PINK);
	        });}
	        
	        
	        
		    private void registerHandler2(Shape s, Color defaultColor, Color hoverColor) {
		        s.setOnMouseEntered( e -> {
		        	
		        	s.setFill(hoverColor);
		        	backButtonText.setTextFill(Color.WHITE);
		        });
		       
		        s.setOnMouseExited(e -> {
		        	
		        	s.setFill(defaultColor);
		        	backButtonText.setTextFill(Color.PINK);         
		        });
		        

		setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
		
	    this.getChildren().addAll(panel,availableGamesTitle, lobbyLineView, createGameButton,createGameButtonText);
		
		
		
	}

}