package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Game;

public class RoundtrackView extends HBox {

///////////////////////////////////////////////////////////////// Instance Variables
	
	Label turnNames;		
	TextFlow textFlowPane;
	Text currentTurn, nextTurns;
	
	
///////////////////////////////////////////////////////////////// Constructor
	
	public RoundtrackView(Game game) {
		setMinSize(1000, 100);
		setMaxSize(1000, 100);
		
/// Initializing The Variables
		
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
		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0, 0, 20, 20, false), null)));
	}
}
