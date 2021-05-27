package view;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TemporaryMenuView extends VBox {
	
	private MainController mainController;
	
	public TemporaryMenuView(MainController mainController) {
		this.mainController = mainController;
		setSpacing(10);
		
		Label newGameLabel = new Label("Create a new game: ");
		Button createGameButton = new Button("Create game");
		createGameButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mainController.createGame();
				showInviteView();
			}
		});
		
		HBox newGameBox = new HBox();
		newGameBox.getChildren().addAll(newGameLabel, createGameButton);
		
		Label loadGame = new Label("Load game with ID:");
		TextField gameID = new TextField();
		Button loadGameButton = new Button("Load game");
		loadGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainController.loadGame(Integer.valueOf(gameID.getText()));
				mainController.showGameLoggedInPlayer();
			}
		});
		HBox loadGameBox = new HBox();
		loadGameBox.getChildren().addAll(loadGame, gameID, loadGameButton);
		
		getChildren().addAll(newGameBox, loadGameBox);
	}

	private void showInviteView() {
		getChildren().clear();
		
		Label inviteLabel = new Label("Invite username: ");
		TextField username = new TextField();
		Button inviteButton = new Button("Invite user");
		inviteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getChildren().add(new Label(username.getText() + " is invited."));
				mainController.getCurrentGame().invitePlayer(username.getText());
				mainController.getCurrentGame().getPlayers().get(0).setPatternCard(1);
				username.clear();
			}
		});
		
		HBox inviteBox = new HBox();
		inviteBox.getChildren().addAll(inviteLabel, username, inviteButton);
		
		Button startGame = new Button("Start game");
		startGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainController.getCurrentGame().startGame();
				mainController.showGameLoggedInPlayer();
			}
		});
		
		getChildren().addAll(inviteBox, startGame);
	}

}
