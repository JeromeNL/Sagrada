package view;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.PlayerStatus;

public class AcceptDenyView extends BorderPane {

	private MainController mainController;
	private DatabaseController dbController;
	private VBox challengeBox;

	public AcceptDenyView(MainController mainController, DatabaseController dbController) {
		this.mainController = mainController;
		this.dbController = dbController;

		setPadding(new Insets(20));
		this.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));

		challengeBox = new VBox();
		challengeBox.setAlignment(Pos.CENTER);
		challengeBox.setSpacing(10);
		fillChallengeBox();
		setCenter(challengeBox);

		Button backToMenu = new Button("Terug naar menu");
		backToMenu.setOnAction(e -> mainController.showFirstMainMenu());
		setTop(backToMenu);
	}

	private void fillChallengeBox() {
		ArrayList<Integer> challengesIDs = dbController.getChallenges(mainController.getLoggedInUsername());

		Label title = new Label("Jouw openstaande uitdagingen");
		title.setFont(Font.font(null, FontWeight.BOLD, 30));
		title.setTextFill(Color.WHITE);

		challengeBox.getChildren().add(title);
		Button refreshButton = new Button("Lijst vernieuwen");
		refreshButton.setOnMouseClicked(e->mainController.showOpenChallenges());

		if (challengesIDs.size() == 0) {
			Label noChallengeLabel = new Label("Je hebt geen openstaande uitdagingen.");
			noChallengeLabel.setTextFill(Color.WHITE);
			challengeBox.getChildren().addAll(noChallengeLabel, refreshButton);
			
			return;
		}

		for (Integer idGame : challengesIDs) {
			HBox challenge = new HBox();
			challenge.setAlignment(Pos.CENTER);
			challenge.setSpacing(30);

			Label idGameLabel = new Label(String.valueOf("GameID: " + idGame));
			idGameLabel.setTextFill(Color.WHITE);

			String challenger = dbController.getChallenger(idGame);
			Label challengerLabel = new Label("Uitdager: " + challenger);
			challengerLabel.setTextFill(Color.WHITE);

			DeclineButton declineButton = new DeclineButton(idGame);
			AcceptButton acceptButton = new AcceptButton(idGame);

			challenge.getChildren().addAll(idGameLabel, challengerLabel, declineButton, acceptButton);
			challengeBox.getChildren().add(challenge);
		}
		
		challengeBox.getChildren().add(refreshButton);
	}

	private class DeclineButton extends Button {

		public DeclineButton(int idGame) {
			super("Weigeren");
			setTextFill(Color.RED);

			setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					dbController.setPlayerStatus(PlayerStatus.REFUSED, mainController.getLoggedInUsername(), idGame);
					mainController.showOpenChallenges();
				}
			});
		}
	}

	private class AcceptButton extends Button {

		public AcceptButton(int idGame) {
			super("Accepteren");
			setTextFill(Color.GREEN);

			setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					dbController.setPlayerStatus(PlayerStatus.ACCEPTED, mainController.getLoggedInUsername(), idGame);
					mainController.showOpenChallenges();
				}
			});
		}
	}
}
