package view;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class LobbyViewInGame extends StackPane {

///////////////////////////////////////////////////////////////// Instance Variables

	private Rectangle bar;
	private Circle avatar;
	private Label username;
	private DropShadow dropShadow;

///////////////////////////////////////////////////////////////// Constructor	

	LobbyViewInGame() {

		dropShadow = new DropShadow();
		dropShadow.setRadius(20);
		dropShadow.setColor(Color.LIGHTGREY);

		bar = new Rectangle(400, 400, 200, 55);
		bar.setArcHeight(55);
		bar.setArcWidth(55);
		bar.setFill(Color.WHITE);
		bar.setTranslateY(-70);
		bar.setTranslateX(-120);
		bar.setEffect(dropShadow);

		avatar = new Circle(400, 400, 20);
		avatar.setFill(Color.BLACK);
		avatar.setTranslateY(-70);
		avatar.setTranslateX(-185);

		username = new Label("Janique");
		username.setTextFill(Color.PINK);
		username.setFont(new Font("Arial", 18));
		username.setStyle("-fx-font-weight: bold");
		username.setTranslateY(-70);
		username.setTranslateX(-110);

		this.getChildren().addAll(bar, avatar, username);

	}
}
