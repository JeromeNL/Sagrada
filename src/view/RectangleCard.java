package view;

import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class RectangleCard extends Rectangle {

	public boolean select;

	public RectangleCard(int widthCard, int heightCard, String cardNumber) {
		super(widthCard, heightCard);
		isSelected(false);

		setFill(Color.WHITE);
		setArcWidth(29.0);
		setArcHeight(29.0);

		hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if (show) {
				isSelected(true);
			} else if (select == true) {
				isSelected(true);

			} else {
				isSelected(false);
			}
		});

	}

	public boolean select() {
		return select;
	}

	public void selects() {
		isSelected(true);
		select = true;
	}

	public void notSelect() {
		isSelected(false);
		select = false;
	}

	public void isSelected(boolean select) {
		if (select) {
			setStroke(Color.LIGHTPINK);
			setStrokeWidth(4.0);
			setStrokeType(StrokeType.INSIDE);
			System.out.println("added stroke");
		} else {
			setStrokeWidth(0.0);
			System.out.println("removed stroke");
		}
	}

}
