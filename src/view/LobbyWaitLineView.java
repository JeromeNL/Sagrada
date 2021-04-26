package view;

import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class LobbyWaitLineView extends StackPane {

///////////////////////////////////////////////////////////////// Instance Variables
	
	private Rectangle bar, rotatingSquare;
	private DropShadow dropShadow;
	private Label waitingText;

	
///////////////////////////////////////////////////////////////// Constructor
	
	public LobbyWaitLineView() {

		dropShadow = new DropShadow();
		dropShadow.setRadius(20);
		dropShadow.setColor(Color.LIGHTGREY);

		bar = new Rectangle(400, 400, 200, 55);
		bar.setArcHeight(55);
		bar.setArcWidth(55);
		bar.setFill(Color.WHITE);
		bar.setTranslateY(-70);
		bar.setTranslateX(120);
		bar.setEffect(dropShadow);

		rotatingSquare = new Rectangle(30, 30);
		rotatingSquare.setFill(Color.PINK);
		rotatingSquare.setTranslateY(-70);
		rotatingSquare.setTranslateX(55);

		waitingText = new Label("Waiting");
		waitingText.setTextFill(Color.PINK);
		waitingText.setFont(new Font("Arial", 18));
		waitingText.setStyle("-fx-font-weight: bold");
		waitingText.setTranslateY(-70);
		waitingText.setTranslateX(130);

		RotateTransition rotate = new RotateTransition();
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(360);
		rotate.setCycleCount(500);
		rotate.setDuration(Duration.millis(1000));
		rotate.setNode(rotatingSquare);
		rotate.play();
		
		this.getChildren().addAll(bar,rotatingSquare,waitingText);
	
	}

}
