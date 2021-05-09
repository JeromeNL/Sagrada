package view;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import controller.PlayerController;
import model.PlayerStats;

public class PlayerListView extends VBox {
	private VBox container;
	private Text playerTxt;
	private TableView<PlayerStats> table = new TableView<>();

	public PlayerListView() {

		container = new VBox();
		container.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0))));
		container.setPrefHeight(600);
		container.setMaxWidth(800);
		container.setStyle("-fx-hgap: 4;");
		Text tekst = new Text("fill");

		playerTxt = new Text("Spelers");
		playerTxt.setFont(new Font("Arial", 30));

		// Table commands
		TableColumn<PlayerStats,String> username = new TableColumn<>("Naam");
		username.setCellValueFactory(new PropertyValueFactory<PlayerStats, String>("Username"));
		username.prefWidthProperty().setValue(155);
		
		TableColumn<PlayerStats,Integer>  highScore = new TableColumn<>("Top score");
		highScore.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("Highscore"));
		
		TableColumn<PlayerStats,Integer>  mostUsedDieValue = new TableColumn<>("Meest gebruikte dobbelsteen waarde");
		mostUsedDieValue.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("mostUsedDieValue"));
		
		TableColumn<PlayerStats,String>  mostUsedDieColor = new TableColumn<>("Meest gebruikte dobbelsteen kleur");
		mostUsedDieColor.setCellValueFactory(new PropertyValueFactory<PlayerStats, String>("mostUsedDieColor"));
		
		TableColumn<PlayerStats,Integer>  oponentCount = new TableColumn<>("Aantal verschillende tegenstanders");
		oponentCount.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("oponentCount"));
		
		TableColumn<PlayerStats,Integer> wins = new TableColumn<>("Gewonnen");
		wins.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("wins"));
		
		TableColumn<PlayerStats,Integer>  losses = new TableColumn<>("Verloren");
		losses.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("losses"));

		table.getColumns().addAll(Arrays.asList(username, highScore, mostUsedDieValue, mostUsedDieColor, oponentCount, wins, losses));
		ObservableList<PlayerStats> playerStats = FXCollections.observableArrayList(PlayerController.AllPlayerStats());
		table.setItems(playerStats);

		container.getChildren().addAll(table);
		this.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(0), new Insets(0))));
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(playerTxt, container);

	}
}
