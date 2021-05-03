package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameButton extends Circle {

	public GameButton() {
		super(25, Color.WHITE);
		setStroke(Color.BLACK);
		setStrokeWidth(5);

		setOnMouseEntered(e -> setFill(Color.LIGHTBLUE));
		setOnMouseExited(e -> setFill(Color.WHITE));
	}
}
