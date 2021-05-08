package view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PatternCardBorderPane extends BorderPane {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	private PatternCardHBox cardHBox;
	private ToolcardButtonView button;
	
	
	public PatternCardBorderPane() {
		cardHBox = new PatternCardHBox();
		button = new ToolcardButtonView();
		
		
		
		this.setBackground(new Background(new BackgroundFill(SAGRADAPINK, null, null)));
		

		this.setCenter(cardHBox);
		this.setBottom(button);

	}
	
}
