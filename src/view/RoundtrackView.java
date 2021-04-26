package view;

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

public class RoundtrackView extends HBox {

///////////////////////////////////////////////////////////////// Instance Variables
	
	Label turnNames;		
	TextFlow textFlowPane;
	Text currentTurn, nextTurns;
	
	
///////////////////////////////////////////////////////////////// Constructor
	
	public RoundtrackView() {
		setMinSize(1000, 100);
		setMaxSize(1000, 100);
		
/// Initializing The Variables
		
		turnNames = new Label("Freek > Freek > Jasper > Janique > Imke");
		turnNames.setStyle("-fx-font-weight: bold");
			
		textFlowPane = new TextFlow();
		
		currentTurn = new Text("Freek ");
		currentTurn.setFill(Color.PINK);
		currentTurn.setFont(new Font("Arial", 20));
		currentTurn.setStyle("-fx-font-weight: bold");
		
		nextTurns = new Text("> Freek > Jasper > Janique > Imke");
		nextTurns.setFill(Color.BLACK);
		nextTurns.setFont(new Font("Arial", 20));
		nextTurns.setStyle("-fx-font-weight: bold");
		
		this.getChildren().addAll(currentTurn, nextTurns);
	
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0, 0, 20, 20, false), null)));
	}
}
