package view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RoundTrackView extends HBox {
	private int dieSize = 50;

	public RoundTrackView() {

		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);

		for (int i = 0; i < 10; i++) {
			Rectangle emptyDie = new Rectangle(dieSize, dieSize);
			emptyDie.setStroke(Color.BLACK);
			emptyDie.setFill(Color.TRANSPARENT);
			emptyDie.setStrokeWidth(4);
			StackPane whiteDie = new StackPane();

			Text dieNumberText = new Text(String.valueOf(i + 1));
			dieNumberText.setFill(Color.GRAY);
			dieNumberText.setFont(new Font("Arial", 20));

			whiteDie.getChildren().addAll(emptyDie, dieNumberText);
			getChildren().addAll(whiteDie);
		}

	}

}
