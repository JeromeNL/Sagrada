package view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ObjectiveInGameView extends StackPane {

Rectangle panel = new Rectangle(400,100);

Rectangle square = new Rectangle(40,40);
Label description = new Label("Som van de waardes op \n"
		                       + "de blauwe dobbelstenen.");
Label title = new Label("Shades Of Blue");
Label points = new Label("75");







public ObjectiveInGameView() {
	
	panel.setFill(Color.WHITE);
	panel.setTranslateX(-400);
	panel.setArcHeight(30);
	panel.setArcWidth(30);
	
	square.setFill(Color.LIGHTBLUE);
	square.setTranslateX(-540);
	square.setRotate(45);
	square.setStroke(Color.BLACK);
	square.setStrokeWidth(10);

	title.setTranslateX(-420);
	title.setTranslateY(-15);
	title.setFont(new Font("arial",20));
	title.setStyle("-fx-font-weight: bold");
	
	description.setTranslateX(-408);
	description.setTranslateY(17);
	description.setFont(new Font("arial",15));
	
	points.setTranslateX(-270);
	points.setTranslateY(0);
	points.setFont(new Font("arial",40));
	points.setTextFill(Color.LIGHTBLUE);
	points.setStyle("-fx-font-weight: bold");
	
	
	
	Group group = new Group();
	group.getChildren().addAll(square,description,title,panel);
	
	
	this.getChildren().addAll(panel,square,description,title,points);
	
	
	
}	
	
	
}
