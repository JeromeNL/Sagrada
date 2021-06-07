package view;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.MainController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class YourGamesView extends BorderPane {

	private MainController mainController;
	private DatabaseController dbController;
	private VBox gamesBox;
	private VBox scrollBox;
	private VBox boxAround;

	public YourGamesView(MainController mainController, DatabaseController dbController) {
		this.mainController = mainController;
		this.dbController = dbController;

		setPadding(new Insets(20));
		this.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));

		Button backToMenu = new Button("Terug naar menu");
		backToMenu.setStyle("-fx-background-color: #ffffff");
		backToMenu.setOnAction(e -> mainController.showFirstMainMenu());
		setTop(backToMenu);

		gamesBox = new VBox();
		gamesBox.setAlignment(Pos.CENTER);
		gamesBox.setSpacing(10);

		boxAround = new VBox();
		boxAround.setSpacing(10);
		fillGamesBox();
		setCenter(gamesBox);
	}

	private void fillGamesBox() {
		ArrayList<Integer> gamesToBePlayed = dbController.getGames(mainController.getLoggedInUsername());

		Label title = new Label("Jouw spellen");
		title.setFont(Font.font(null, FontWeight.BOLD, 30));
		title.setTextFill(Color.WHITE);

		Label description = new Label(
				"Een lijst van al jouw geaccepteerde spellen die gestart zijn en nog niet uitgespeeld zijn.");
		description.setTextFill(Color.WHITE);
		gamesBox.getChildren().addAll(title, description);
		Button refreshButton = new Button("Lijst vernieuwen");
		refreshButton.setStyle("-fx-background-color: #ffffff");
		refreshButton.setOnMouseClicked(e -> mainController.showYourGames());

		if (gamesToBePlayed.size() == 0) {
			Label noChallengeLabel = new Label("Je hebt geen beschikbare spellen om te spelen.");
			gamesBox.getChildren().addAll(noChallengeLabel, refreshButton);

			return;
		}

		for (Integer idGame : gamesToBePlayed) {

			HBox game = new HBox();
			game.setAlignment(Pos.CENTER);
			game.setSpacing(30);

			Label idGameLabel = new Label(String.valueOf("GameID: " + idGame));
			idGameLabel.setTextFill(Color.WHITE);

			String challenger = dbController.getChallenger(idGame);
			Label challengerLabel = new Label("Uitdager: " + challenger);
			challengerLabel.setTextFill(Color.WHITE);

			Button loadGame = new Button("Spel laden");
			loadGame.setStyle("-fx-background-color: #ffffff");
			loadGame.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					mainController.loadGame(idGame);
					mainController.showGameLoggedInPlayer();
				}

			});

			game.getChildren().addAll(idGameLabel, challengerLabel, loadGame);
			scrollBox = new VBox();
			scrollBox.getChildren().add(game);
			boxAround.getChildren().addAll(scrollBox);

		}

		ScrollPane sc = new ScrollPane();
		sc.setContent(boxAround);
		sc.setHbarPolicy(ScrollBarPolicy.NEVER);
		sc.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sc.setMaxWidth(400);
		sc.setStyle(
				"-fx-background: transparent; -fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

		gamesBox.getChildren().addAll(sc);
		gamesBox.getChildren().add(refreshButton);
	}
}
