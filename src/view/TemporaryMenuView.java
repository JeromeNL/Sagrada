package view;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TemporaryMenuView extends BorderPane {
	
	private MainController mainController;
	
	public TemporaryMenuView(MainController mainController) {
		this.mainController = mainController;
		
		setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
		
		Label createGameLabel = new Label("Create a new game: ");
		Button createGameButton = new Button("Create game");
		createGameButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mainController.createGame();
				showInviteView();
			}
		});
		
		HBox createGameBox = new HBox();
		createGameBox.getChildren().addAll(createGameLabel, createGameButton);
		createGameBox.setAlignment(Pos.BASELINE_CENTER);
		
		Label loadGameLabel = new Label("Load game with ID:");
		TextField loadGameTextField = new TextField();
		Button loadGameButton = new Button("Load game");
		loadGameButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainController.loadGame(Integer.valueOf(loadGameTextField.getText()));
				mainController.showGameLoggedInPlayer();
			}
		});
		
		HBox loadGameBox = new HBox();
		loadGameBox.getChildren().addAll(loadGameLabel, loadGameTextField, loadGameButton);
		loadGameBox.setAlignment(Pos.CENTER);
		
		VBox createAndLoadBox = new VBox();
		createAndLoadBox.setSpacing(10);
		createAndLoadBox.setAlignment(Pos.CENTER);
		createAndLoadBox.getChildren().addAll(createGameBox, loadGameBox);
		
		setCenter(createAndLoadBox);
	}

	private void showInviteView() {
		getChildren().clear();
		
		Label inviteLabel = new Label("Invite username: ");
		TextField username = new TextField();
		Button inviteButton = new Button("Invite user");
		
		
		HBox inviteBox = new HBox();
		inviteBox.setAlignment(Pos.CENTER);
		inviteBox.getChildren().addAll(inviteLabel, username, inviteButton);
		
		Button startGame = new Button("Start game");
		startGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainController.getCurrentGame().startGame();
				mainController.showGameLoggedInPlayer();
			}
		});
		
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(inviteBox, startGame);
		
		inviteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				box.getChildren().add(new Label(username.getText() + " is invited."));
				mainController.getCurrentGame().invitePlayer(username.getText());
				username.clear();
				
				// TODO: remove what's under here when patterncard chooser works
				mainController.getCurrentGame().getPlayers().get(0).setPatternCard(1);
				mainController.getCurrentGame().getPlayers().get(1).setPatternCard(2);
			}
		});
		
		setCenter(box);
	}

}
