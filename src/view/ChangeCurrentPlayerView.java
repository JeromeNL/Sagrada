package view;


import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ChangeCurrentPlayerView extends StackPane {

private Label currentPlayerText,user1,user2,user3,user4;
private Rectangle dropDownButton;	



public ChangeCurrentPlayerView() {
		
	
		Rectangle background = new Rectangle(1280,720);
		background.setFill(Color.LIGHTBLUE);
	 
		Rectangle animation = new Rectangle(700,700);
		animation.setFill(Color.TRANSPARENT);
		animation.setStrokeWidth(20);
		animation.setStroke(Color.BLACK);
		animation.setRotate(30);
		
		RotateTransition rt = new RotateTransition(Duration.millis(3000), animation);
		rt.setByAngle(180);
	    rt.setCycleCount(500);
	    rt.setAutoReverse(true);
	    rt.play();
		
		
		dropDownButton = new Rectangle(30,30);
		dropDownButton.setTranslateX(-260);
		dropDownButton.setTranslateY(-200);
		dropDownButton.setOnMouseClicked(e -> 
			
		{
		this.getChildren().clear();
		this.getChildren().addAll(background, animation,user1,user2,user3,user4);
		
		System.out.println("button is pressed");});
		
		currentPlayerText = new Label("Jasper");
		currentPlayerText.setStyle("-fx-font-weight: bold");
		currentPlayerText.setFont(new Font("Arial", 40));
		currentPlayerText.setTranslateX(-350);
		currentPlayerText.setTranslateY(-200);
		
		user1 = new Label("Jasper");
		user1.setStyle("-fx-font-weight: bold");
		user1.setFont(new Font("Arial", 40));
		user1.setTranslateX(0);
		user1.setTranslateY(-50);
		
		user1.setOnMouseEntered(e -> user1.setUnderline(true));
		user1.setOnMouseExited(e -> user1.setUnderline(false));
		
		
		
		user2 = new Label("Janique");
		user2.setStyle("-fx-font-weight: bold");
		user2.setFont(new Font("Arial", 40));
		user2.setTranslateX(0);
		user2.setTranslateY(0);
		
		user2.setOnMouseEntered(e -> user2.setUnderline(true));
		user2.setOnMouseExited(e -> user2.setUnderline(false));
		
		
		user3 = new Label("Imke");
		user3.setStyle("-fx-font-weight: bold");
		user3.setFont(new Font("Arial", 40));
		user3.setTranslateX(0);
		user3.setTranslateY(50);
		
		user3.setOnMouseEntered(e -> user3.setUnderline(true));
		user3.setOnMouseExited(e -> user3.setUnderline(false));
		
		user4 = new Label("Mandy");
		user4.setStyle("-fx-font-weight: bold");
		user4.setFont(new Font("Arial", 40));
		user4.setTranslateX(0);
		user4.setTranslateY(100);
		
		user4.setOnMouseEntered(e -> user4.setUnderline(true));
		user4.setOnMouseExited(e -> user4.setUnderline(false));
		
		
//		s.setOnMouseExited(e -> s.setFill(defaultColor));
		
		
		
		this.getChildren().addAll(dropDownButton, currentPlayerText);
	
	}
	
	
}
