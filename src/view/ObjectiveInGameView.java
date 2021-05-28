package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ObjectiveInGameView extends HBox {

	private Rectangle square;
	private Label description;
	private Label title;
	private Label points;

	public ObjectiveInGameView() {

		setMaxSize(400, 100);
		setMinSize(400, 100);
		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(30), null)));
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(0, 30, 0, 30));
		setSpacing(30);

		square = new Rectangle(40, 40);
		square.setFill(Color.LIGHTBLUE);
		square.setRotate(45);
		square.setStroke(Color.BLACK);
		square.setStrokeWidth(10);

		title = new Label("Shades Of Blue");
		title.setFont(new Font("arial", 20));
		title.setStyle("-fx-font-weight: bold");

		description = new Label("Som van de waardes op \n" + "de blauwe dobbelstenen.");
		description.setFont(new Font("arial", 15));

		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER_LEFT);
		vBox.getChildren().addAll(title, description);

		points = new Label("75");
		points.setTranslateY(0);
		points.setFont(new Font("arial", 40));
		points.setTextFill(Color.LIGHTBLUE);
		points.setStyle("-fx-font-weight: bold");

		getChildren().addAll(square, vBox, points);
	}
	
	public void hide() {
		getChildren().clear();
		Label label = new Label("You cannot see other player's private objectives");
		getChildren().add(label);
	}
}
