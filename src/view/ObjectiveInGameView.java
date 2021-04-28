package view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObjectiveInGameView extends StackPane {

Rectangle panel = new Rectangle(300,100);

Rectangle square = new Rectangle(40,40);
Label description = new Label();
Label title = new Label("TITEL");
Label points = new Label();






public ObjectiveInGameView() {
	
	panel.setFill(Color.WHITE);
	panel.setTranslateX(-400);
	panel.setArcHeight(30);
	panel.setArcWidth(30);
	
	square.setFill(Color.LIGHTBLUE);
	square.setTranslateX(-490);
	square.setRotate(45);
	square.setStroke(Color.BLACK);
	square.setStrokeWidth(10);

	
	
	
	
	this.getChildren().addAll(panel,square,description,title,points);
	
	
	
}	
	
	
}
