package view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class ChoosePatternCardView extends BorderPane {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	private ChoosePatternCardCards choosePatternCardCards;
	private ChooseButton button;

	public ChoosePatternCardView() {
		choosePatternCardCards = new ChoosePatternCardCards();
		button = new ChooseButton();

		this.setBackground(new Background(new BackgroundFill(SAGRADAPINK, null, null)));

		this.setCenter(choosePatternCardCards);
		this.setBottom(button);

	}

}
