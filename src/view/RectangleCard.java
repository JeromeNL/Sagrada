package view;

import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class RectangleCard extends Rectangle {

	public RectangleCard(int widthCard, int heightCard, String cardNumber) {
		super(widthCard, heightCard);
		
		setFill(Color.WHITE);
		setArcWidth(29.0);
		setArcHeight(29.0);

		hoverProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
					if (show) {
						setStroke(Color.LIGHTPINK);
						setStrokeWidth(4.0);
						setStrokeType(StrokeType.INSIDE);
					} else {
						setStrokeWidth(0.0);

					}
				});

		setOnMouseClicked(e -> {
			System.out.println("click " + cardNumber);
			
		});
	}

}
