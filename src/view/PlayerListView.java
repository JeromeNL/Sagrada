package view;

import java.sql.SQLException;
import java.util.Arrays;

import controller.DatabaseController;
import controller.MainController;
import controller.PlayerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.PlayerStats;

public class PlayerListView extends VBox {
	private VBox container;
	private Text playerTxt;
	private TableView<PlayerStats> table = new TableView<>();

	private DatabaseController dbController;
	private MainController mainController;

	public PlayerListView(DatabaseController dbController, MainController mainController) {
		this.dbController = dbController;
		this.mainController = mainController;

		// creating the view
		container = new VBox();
		container.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0))));
		container.setPrefHeight(600);
		container.setMaxWidth(1005);
		container.setStyle("-fx-hgap: 4;");

		playerTxt = new Text("Spelers Overzicht");
		playerTxt.setFont(new Font("Arial", 30));

		Button backButton = new Button("Terug naar menu");
		// Styling button
		String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color: transparent;" + "-fx-font-size: 20;" + "-fx-text-fill: white;";
		String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color:white;" + "-fx-font-size: 20;" + "-fx-text-fill:blue;";

		backButton.setStyle(IDLE_BUTTON_STYLE);
		backButton.setOnMouseEntered(e -> backButton.setStyle(HOVERED_BUTTON_STYLE));
		backButton.setOnMouseExited(e -> backButton.setStyle(IDLE_BUTTON_STYLE));

		// Event after clicking the "Terug naar menu" button
		backButton.setOnMouseClicked(e -> {
			mainController.showFirstMainMenu();
		});

		// Creating the tables
		TableColumn<PlayerStats, String> username = new TableColumn<>("Naam");
		username.setCellValueFactory(new PropertyValueFactory<PlayerStats, String>("Username"));
		username.prefWidthProperty().setValue(155);

		TableColumn<PlayerStats, Integer> highScore = new TableColumn<>("Top score");
		highScore.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("Highscore"));

		TableColumn<PlayerStats, Integer> mostUsedDieValue = new TableColumn<>("Meest gebruikte waarde");
		mostUsedDieValue.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("mostUsedDieValue"));

		TableColumn<PlayerStats, String> mostUsedDieColor = new TableColumn<>("Meest gebruikte kleur");
		mostUsedDieColor.setCellValueFactory(new PropertyValueFactory<PlayerStats, String>("mostUsedDieColor"));

		TableColumn<PlayerStats, Integer> oponentCount = new TableColumn<>("Aantal verschillende tegenstanders");
		oponentCount.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("oponentCount"));

		TableColumn<PlayerStats, Integer> wins = new TableColumn<>("Gewonnen");
		wins.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("wins"));

		TableColumn<PlayerStats, Integer> losses = new TableColumn<>("Verloren");
		losses.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("losses"));

		table.getColumns().addAll(
				Arrays.asList(username, highScore, mostUsedDieValue, mostUsedDieColor, oponentCount, wins, losses));
		ObservableList<PlayerStats> playerStats;
		try {
			playerStats = FXCollections
					.observableArrayList(new PlayerController(dbController, mainController).AllPlayerStats());
			table.setItems(playerStats);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setMaxHeight(600);
		table.setMinHeight(600);

// Adding everything to the view
		container.getChildren().addAll(table);
		this.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(0), new Insets(0))));
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(playerTxt, container, backButton);

	}
}
