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
		setAlignment(Pos.CENTER);
		setSpacing(10);

		ArrayList<String> playerOrder = game.getPlayerOrder();
		
		int currentRoundID = game.getRoundID();
		
		// Show all the names
		for (int i = 0; i < playerOrder.size(); i++) {
			Label player = new Label(playerOrder.get(i));
			Label splitter = new Label(">");
			player.setStyle("-fx-font-weight: bold");
			player.setFont(new Font("Arial", 20));
			
			// Check if the name needs to get a highlight.
			if (playerOrder.get(i).equals(game.getCurrentPlayer())) {
				if (currentRoundID % 2 != 0 && i < playerOrder.size()/2) {
					player.setTextFill(Color.RED);					
				}
				if (currentRoundID % 2 == 0 && i > playerOrder.size()/2-1) {
					player.setTextFill(Color.RED);					
				}
			}
			
			if (i < playerOrder.size()-1) {
				getChildren().addAll(player, splitter);				
			} else {
				getChildren().addAll(player);		
			}
		}

		setAlignment(Pos.CENTER);
	}
}
