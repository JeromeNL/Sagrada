package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class ToolCardInUseView extends StackPane{
	
///////////////////////////////////////////////////////////////// Instance Variables
	
	private Rectangle background,backButton,card;
	private Label backButtonText;
	private FavorTokensInUseView favortokens;
	
///////////////////////////////////////////////////////////////// Getters And Setters	
	
	public Rectangle getR() {
		return background;
	}

	public Rectangle getBackButton() {
		return backButton;
	}

	public Rectangle getCard() {
		return card;
	}
	
	public Label getJoinButtonText() {
		return backButtonText;
	}

///////////////////////////////////////////////////////////////// Constructor
	
	
	public ToolCardInUseView(){
	
/// Initializing Variables
		
	 
	favortokens = new FavorTokensInUseView("Janique",15);	
		
	 backButton = new Rectangle(400,400,155,50);
	 backButton.setArcHeight(50);
	 backButton.setArcWidth(50);
	 backButton.setFill(Color.LIGHTBLUE);
	 backButton.setTranslateY(200);
	 backButton.setTranslateX(170);
	 backButton.setStrokeWidth(5);
	 backButton.setStroke(Color.WHITE);
	 
	 backButtonText = new Label("Terug");
	 backButtonText.setTextFill(Color.WHITE);
	 backButtonText.setFont(new Font("Arial", 15));
	 backButtonText.setStyle("-fx-font-weight: bold");
	 backButtonText.setTranslateY(200);
	 backButtonText.setTranslateX(170);

	 card = new Rectangle(200,400,330,600);
	 card.setArcHeight(50);
	 card.setArcWidth(50);
	 card.setFill(Color.WHITE);
	 card.setTranslateX(-350);
	
	 background = new Rectangle(1280,720);
	 background.setFill(Color.LIGHTBLUE);
	 
	 setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
	 
	 registerHandler(backButton, Color.WHITE, Color.WHITE);
	 backButton.setOnMouseClicked(e -> {System.out.println("button is pressed");
	 this.getChildren().clear();
	 });
	
	this.getChildren().addAll(background,card,backButton,backButtonText,favortokens);
	
	
	}

///////////////////////////////////////////////////////////////// registerHandler

/* Method that is responsible for the change of 
 * color when hovering a button */	
	
	private void registerHandler(Shape s, Color defaultColor, Color hoverColor) {
		
		s.setOnMouseEntered(e -> {
			s.setFill(hoverColor);
			backButtonText.setTextFill(Color.LIGHTBLUE);});
		
		s.setOnMouseExited(e -> {
			s.setFill(Color.LIGHTBLUE);
			backButtonText.setTextFill(Color.WHITE);});
	}
	
}
