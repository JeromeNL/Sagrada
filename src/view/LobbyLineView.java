package view;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class LobbyLineView extends StackPane {

///////////////////////////////////////////////////////////////// Instance Variables
	
	private Label username, buttonTag;
	private Rectangle bar, joinButton;
	private Circle avatar;
	
	
///////////////////////////////////////////////////////////////// Constructor
	
	public LobbyLineView() {
			
		 DropShadow dropShadow = new DropShadow();
		 dropShadow.setRadius(20);
		 dropShadow.setColor(Color.LIGHTGREY);
		 
		 bar = new Rectangle(400,400,500,55);
		 bar.setArcHeight(55);
		 bar.setArcWidth(55);
		 bar.setFill(Color.WHITE);
		 bar.setTranslateY(-115);
		 bar.setEffect(dropShadow);
		 
		 avatar = new Circle(400,400, 20);
		 avatar.setFill(Color.BLACK);
		 avatar.setTranslateY(-115);
		 avatar.setTranslateX(-215);
		 
		 username = new Label("Janique                                    3/4");
		 username.setTextFill(Color.PINK);
		 username.setFont(new Font("Arial", 18));
		 username.setStyle("-fx-font-weight: bold");
		 username.setTranslateY(-115);
		 username.setTranslateX(-45);
		 
		 buttonTag = new Label("Join Game");
		 buttonTag.setTextFill(Color.WHITE);
		 buttonTag.setFont(new Font("Arial", 12));
		 buttonTag.setStyle("-fx-font-weight: bold");
		 buttonTag.setTranslateY(-115);
		 buttonTag.setTranslateX(180);
		 
		 joinButton = new Rectangle(400,400,80,21);
		 joinButton.setArcHeight(20);
		 joinButton.setArcWidth(20);
		 joinButton.setFill(Color.PINK);
		 joinButton.setTranslateY(-115);
		 joinButton.setTranslateX(180);
		 joinButton.setEffect(dropShadow);
		 
		 StackPane sp = new StackPane();
		 sp.getChildren().addAll(bar,joinButton,buttonTag,username,avatar);
		 sp.setTranslateX(0); //// changing the height
		 
		 this.getChildren().addAll(sp);
		 
	}	
}
