package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Game;

public class PlayerOrderView extends HBox {

///////////////////////////////////////////////////////////////// Instance Variables

	Label turnNames;
	TextFlow textFlowPane;
	Text currentTurn, nextTurns;

///////////////////////////////////////////////////////////////// Constructor

	public PlayerOrderView(Game game) {

/// Initializing The Variables
		setAlignment(Pos.CENTER);
		setSpacing(10);

		ArrayList<String> playerOrder = game.getPlayerOrder();
		for (String username : playerOrder) {
			Label player = new Label(username);
			Label splitter = new Label(">");
			player.setStyle("-fx-font-weight: bold");
			player.setFont(new Font("Arial", 20));
			getChildren().addAll(player, splitter);
		}

		setAlignment(Pos.CENTER);
	}
}
