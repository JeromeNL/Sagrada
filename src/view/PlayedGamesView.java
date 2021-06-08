package view;

import java.sql.SQLException;
import java.util.Arrays;

import controller.DatabaseController;
import controller.GameController;
import controller.MainController;
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
import model.GameStats;

public class PlayedGamesView extends VBox {
	private VBox container;
	private Text gameTxt;
	private TableView<GameStats> table = new TableView<>();

	public PlayedGamesView(DatabaseController dbController, MainController mainController) {
		// creating the view
		container = new VBox();
		container.setPrefHeight(600);
		container.setMaxWidth(1005);
		container.setStyle("-fx-hgap: 4;");

		gameTxt = new Text("Spellen Overzicht");
		gameTxt.setFont(new Font("Arial", 30));

		Button backButton = new Button("Terug naar menu");
		String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color: transparent;" + "-fx-font-size: 20;" + "-fx-text-fill: white;";
		String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color:white;" + "-fx-font-size: 20;" + "-fx-text-fill:deeppink;";

		backButton.setStyle(IDLE_BUTTON_STYLE);
		backButton.setOnMouseEntered(e -> backButton.setStyle(HOVERED_BUTTON_STYLE));
		backButton.setOnMouseExited(e -> backButton.setStyle(IDLE_BUTTON_STYLE));

		// Event after clicking the "Terug naar menu" button
		backButton.setOnMouseClicked(e -> {
			mainController.showFirstMainMenu();
		});

		// Creating the tables
		TableColumn<GameStats, String> username = new TableColumn<>("Spelers");
		username.setCellValueFactory(new PropertyValueFactory<GameStats, String>("username"));
		username.prefWidthProperty().setValue(200);
		
		

		TableColumn<GameStats, Integer> idgame = new TableColumn<>("Spel");
		idgame.setCellValueFactory(new PropertyValueFactory<GameStats, Integer>("idgame"));
		idgame.prefWidthProperty().setValue(200);

		TableColumn<GameStats, Integer> round = new TableColumn<>("Ronde");
		round.setCellValueFactory(new PropertyValueFactory<GameStats, Integer>("round"));
		round.prefWidthProperty().setValue(200);

		TableColumn<GameStats, String> creationdate = new TableColumn<>("Aanmaakdatum");
		creationdate.setCellValueFactory(new PropertyValueFactory<GameStats, String>("creationdate"));
		creationdate.prefWidthProperty().setValue(200);

		TableColumn<GameStats, String> gamestatus = new TableColumn<>("Status");
		gamestatus.setCellValueFactory(new PropertyValueFactory<GameStats, String>("gamestatus"));
		gamestatus.prefWidthProperty().setValue(200);
		
		gamestatus.setStyle("-fx-background-color: tomato;");
		
	
		   

		table.getColumns().addAll(Arrays.asList(idgame, round, creationdate, gamestatus, username));
		try {
			ObservableList<GameStats> gameStats = FXCollections
					.observableArrayList(new GameController(dbController, mainController).AllGameStats());
			table.setItems(gameStats);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setMaxHeight(600);
		table.setMinHeight(600);

		// Adding everything to the view
		container.getChildren().addAll(table);
		this.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(0), new Insets(0))));
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(gameTxt, container, backButton);

	}
	

    }

	

