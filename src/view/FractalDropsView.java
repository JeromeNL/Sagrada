package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FractalDropsView extends VBox {

	public FractalDropsView(int amount) {

		Text text = new Text(" Betaalstenen ");
		setAlignment(Pos.CENTER);
		text.setFont(Font.font("Futura Std", 18));
		text.setFill(Color.BLACK);
		Rectangle line = new Rectangle(150, 4);

		HBox fractalDropRectangles = new HBox();
		fractalDropRectangles.setAlignment(Pos.CENTER);
		fractalDropRectangles.setPadding(new Insets(8, 5, 15, 5));
		fractalDropRectangles.setSpacing(10);

		for (int i = 0; i < amount; i++) {
			Rectangle rectangleDrops = new Rectangle(10, 10);
			rectangleDrops.setRotate(45);
			fractalDropRectangles.getChildren().addAll(rectangleDrops);
		}

		getChildren().addAll(text, line, fractalDropRectangles);

	}
}
