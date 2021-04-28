package view;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameView extends StackPane {

///////////////////////////////////////////////////////////////// Instance Variables	
	
	private RoundtrackView roundtrackView;
	private PatternCardView patternCardView;
	private DieSupply dieSupply;
	private GameButtonView gameButtonView;
	
	
///////////////////////////////////////////////////////////////// Constructor
	

	
	
	public GameView() {

		
			
		
/// Initializing Variables		
		
		patternCardView = new PatternCardView();
		roundtrackView = new RoundtrackView();
		dieSupply = new DieSupply(Color.LIGHTBLUE);
		gameButtonView = new GameButtonView();
		ChangeCurrentPlayerView changeCurrentPlayerView = new ChangeCurrentPlayerView();
			
		setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
		
		setAlignment(roundtrackView, Pos.TOP_CENTER);
		setAlignment(patternCardView, Pos.CENTER);
		
		///// enables gameview buttons to work while in development 
		
//		this.getChildren().addAll(roundtrackView,patternCardView,dieSupply,gameButtonView);

		//// enables the die supply to be draggable while in development
		
//		this.getChildren().addAll(roundtrackView,patternCardView,gameButtonView,dieSupply);

		
		this.getChildren().addAll(roundtrackView,patternCardView,gameButtonView,dieSupply,changeCurrentPlayerView);
		
	}

}
