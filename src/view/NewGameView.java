package view;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.MainController;
import controller.RegisterLoginController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import model.Player;

public class NewGameView extends BorderPane {
	
	private MainController mainController;
	private DatabaseController dbController;
	private RegisterLoginController rlc;
	private VBox createGameBox;
	private HBox inviteBox;
	private Label errorMessage;
	private Button inviteButton;
	private TextField username;
	private HBox bottom;

	public NewGameView(MainController mainController, DatabaseController dbController) {
		this.mainController = mainController;
		this.dbController = dbController;
		rlc = new RegisterLoginController(dbController);
		errorMessage = new Label("");
		errorMessage.setTextFill(Color.WHITE);
		
		setPadding(new Insets(20));
		this.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));

		Button backToMenu = new Button("Terug naar menu");
		backToMenu.setOnAction(e -> mainController.showFirstMainMenu());
		setTop(backToMenu);
		
		createGameBox = new VBox();
		createGameBox.setAlignment(Pos.CENTER);
		createGameBox.setSpacing(10);
		
		Label title = new Label("Maak een nieuw spel aan");
		title.setFont(Font.font(null, FontWeight.BOLD, 30));
		title.setTextFill(Color.WHITE);

		Label description = new Label(
				"Je kan hier een nieuw spel aanmaken andere spelers uitnodigen om deel te nemen.");
		description.setTextFill(Color.WHITE);
		createGameBox.getChildren().addAll(title, description);
		
		Button createGameButton = new Button("Nieuw spel aanmaken");
		createGameButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				createGameButton.setDisable(true);
				Label gameCreatedLabel = new Label("Een nieuw spel is aangemaakt. Daag hieronder nu 1 tot 3 andere spelers uit.");
				gameCreatedLabel.setTextFill(Color.WHITE);
				createGameBox.getChildren().add(gameCreatedLabel);
				mainController.createGame();
				showInviteBox();
			}
			
		});
		createGameBox.getChildren().addAll(createGameButton);
		setCenter(createGameBox);
		
	}	
	
	private void showInviteBox() {
		
		inviteBox = new HBox();
		Label inviteLabel = new Label("Speler gebruikersnaam: ");
		inviteLabel.setTextFill(Color.WHITE);
		username = new TextField();
		inviteButton = new Button("Uitdagen");
		inviteButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				errorMessage.setText("");
				String typedUsername = username.getText();
				username.clear();
				if (isValidInvite(typedUsername)) {
					mainController.getCurrentGame().invitePlayer(typedUsername);
					Label invitedUser = new Label(typedUsername + " is uitgedaagd voor dit spel!");
					invitedUser.setTextFill(Color.WHITE);
					createGameBox.getChildren().add(invitedUser);
					showStartGameButton();
				}
				
			}

			
		});
		
		inviteBox.setSpacing(10);
		inviteBox.setAlignment(Pos.CENTER);
		inviteBox.getChildren().addAll(inviteLabel, username, inviteButton, errorMessage);
		
		createGameBox.getChildren().add(inviteBox);
	}
	
	private void showStartGameButton() {
		Label cannotStart = new Label("");
		cannotStart.setTextFill(Color.WHITE);
		
		bottom = new HBox();
		bottom.setAlignment(Pos.CENTER_RIGHT);
		bottom.setSpacing(10);
		setBottom(bottom);

		Button startGameButton = new Button("Start het spel");
		startGameButton.setStyle("-fx-background-color: #ffffff");
		bottom.getChildren().addAll(cannotStart, startGameButton);
		
		startGameButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (gameReadyToStart()) {
					mainController.getCurrentGame().startGame();
					mainController.showGameLoggedInPlayer();
				} else {
					cannotStart.setText("(Nog) niet iedereen heeft de uitnodiging geaccepteerd, probeer later opnieuw.");
					
				}
			}
		});
	}
	
	private boolean gameReadyToStart() {
		ArrayList<Integer> gamesToStart = dbController.getGames(mainController.getLoggedInUsername());
		int gameCreatedID = mainController.getCurrentGame().getIdGame();
		if (gamesToStart.contains(gameCreatedID)) {
			return true;
		}
		
		return false;
	}
	
	private boolean isValidInvite(String username) {
		if (rlc.nameAvailableCheck(username) != 1) {
			errorMessage.setText("Deze speler bestaat niet.");
			return false;
		} else if (isAlreadyInvited(username)){
			errorMessage.setText("Deze speler is al uitgenodigd.");
			return false;
		} else if (tooManyPlayers()) {
			errorMessage.setText("Je kunt niet meer dan 3 andere spelers uitnodigen.");
			return false;
		}
		return true;
	}
	
	private boolean isAlreadyInvited(String username) {
		int gameID = mainController.getCurrentGame().getIdGame();
		ArrayList<Player> playersInGame = dbController.getPlayers(gameID);
		for (Player player : playersInGame) {
			if (player.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean tooManyPlayers() {
		int gameID = mainController.getCurrentGame().getIdGame();
		ArrayList<Player> playersInGame = dbController.getPlayers(gameID);
		if (playersInGame.size() == 4) {
			inviteButton.setDisable(true);
			username.setDisable(true);
			return true;
		}
		return false;
	}
}
