package view;

import java.sql.SQLException;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import controller.GameController;

public class PlayedGamesView extends VBox {
	private VBox container;
	private Text gameTxt;
	private TableView<GameStats> table = new TableView<>();

	public PlayedGamesView() {
		// creating the view
		container = new VBox();
		container.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0))));
		container.setPrefHeight(600);
		container.setMaxWidth(1005);
		container.setStyle("-fx-hgap: 4;");
		Text tekst = new Text("fill");

		gameTxt = new Text("Games");
		gameTxt.setFont(new Font("Arial", 30));

		// Creating the tables
		TableColumn<GameStats, String> username = new TableColumn<>("Spelers");
		username.setCellValueFactory(new PropertyValueFactory<GameStats, String>("username"));
		username.prefWidthProperty().setValue(155);
		
		TableColumn<GameStats, Integer> idgame = new TableColumn<>("Spel");
		idgame.setCellValueFactory(new PropertyValueFactory<GameStats, Integer>("idgame"));
		
		TableColumn<GameStats, Integer> round = new TableColumn<>("Ronde");
		round.setCellValueFactory(new PropertyValueFactory<GameStats, Integer>("round"));
		
		TableColumn<GameStats, String> creationdate = new TableColumn<>("Aanmaakdatum");
		creationdate.setCellValueFactory(new PropertyValueFactory<GameStats, String>("creationdate"));
		
		TableColumn<GameStats, String> gamestatus = new TableColumn<>("Status");
		gamestatus.setCellValueFactory(new PropertyValueFactory<GameStats, String>("gamestatus"));

		table.getColumns().addAll(Arrays.asList(idgame, round, creationdate, gamestatus, username));
		try {
			ObservableList<GameStats> gameStats = FXCollections.observableArrayList(GameController.AllGameStats());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setMaxHeight(600);
		table.setMinHeight(600);

		// Adding everything to the view
		container.getChildren().addAll(table);
		this.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(0), new Insets(0))));
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(gameTxt, container);

	}
}
