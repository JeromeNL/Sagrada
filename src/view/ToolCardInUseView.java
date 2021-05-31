package view;

import controller.MainController;
import imageChooser.ToolcardCardImage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class ToolCardInUseView extends Pane{
	
///////////////////////////////////////////////////////////////// Instance Variables
	
	private Rectangle background,backButton,card,confirmButton;
	private Label backButtonText,confirmButtonText;
	private FavorTokensInUseView favortokensPlayer1,favortokensPlayer2,favortokensPlayer3,favortokensPlayer4;
	private GameView gameview;
	
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
	
	
	public ToolCardInUseView(MainController mainController){
	
/// Initializing Variables
		
	
		
	favortokensPlayer1 = new FavorTokensInUseView("Janique |  15",15);	
	favortokensPlayer1.setTranslateY(-50);
	
	favortokensPlayer2 = new FavorTokensInUseView("Jasper | 11",11);	
	favortokensPlayer2.setTranslateY(50);
	
	favortokensPlayer3 = new FavorTokensInUseView("Mandy | 5",5);	
	favortokensPlayer3.setTranslateY(150);
	
	favortokensPlayer4 = new FavorTokensInUseView("Freek | 8",8);	
	favortokensPlayer4.setTranslateY(250);
		
	 backButton = new Rectangle(400,400,155,50);
	 backButton.setArcHeight(50);
	 backButton.setArcWidth(50);
	 backButton.setFill(Color.LIGHTBLUE);
	 backButton.setTranslateY(200);
	 backButton.setTranslateX(450);
	 backButton.setStrokeWidth(5);
	 backButton.setStroke(Color.WHITE);
	 
	 backButtonText = new Label("Terug");
	 backButtonText.setTextFill(Color.WHITE);
	 backButtonText.setFont(new Font("Arial", 15));
	 backButtonText.setStyle("-fx-font-weight: bold");
	 backButtonText.setTranslateY(615);
	 backButtonText.setTranslateX(905);
	 
	 confirmButton = new Rectangle(400,400,155,50);
	 confirmButton.setArcHeight(50);
	 confirmButton.setArcWidth(50);
	 confirmButton.setFill(Color.LIGHTBLUE);
	 confirmButton.setTranslateY(200);
	 confirmButton.setTranslateX(655);
	 confirmButton.setStrokeWidth(5);
	 confirmButton.setStroke(Color.WHITE);
	 
	 confirmButtonText = new Label("Activeer");
	 confirmButtonText.setTextFill(Color.WHITE);
	 confirmButtonText.setFont(new Font("Arial", 15));
	 confirmButtonText.setStyle("-fx-font-weight: bold");
	 confirmButtonText.setTranslateY(615);
	 confirmButtonText.setTranslateX(1105);
	 
	 ToolcardCardImage toolcardCardImage = new ToolcardCardImage(9); 
	 toolcardCardImage.setLayoutX(50);
	 toolcardCardImage.setLayoutY(60);

	
	 background = new Rectangle(1280,720);
	 background.setFill(Color.LIGHTBLUE);
	 
	 setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
	 
	 registerHandler2();
	 backButton.setOnMouseClicked(e -> {System.out.println("button is pressed");
	 mainController.showGame();
	 });
	 
	 registerHandler();
	 confirmButton.setOnMouseClicked(e -> {System.out.println("button is pressed");
	 this.getChildren().clear();
	 
	 });
	
	this.getChildren().addAll(background,toolcardCardImage,confirmButton,confirmButtonText,favortokensPlayer1,favortokensPlayer2,favortokensPlayer3,favortokensPlayer4,backButton,backButtonText);
	
	
	}

///////////////////////////////////////////////////////////////// registerHandler

/* Method that is responsible for the change of 
 * color when hovering a button */	
	
	private void registerHandler() {
		
		confirmButton.setOnMouseEntered(e -> {
			confirmButton.setFill(Color.WHITE);
			confirmButtonText.setTextFill(Color.LIGHTBLUE);});
		
		confirmButton.setOnMouseExited(e -> {
			confirmButton.setFill(Color.LIGHTBLUE);
			confirmButtonText.setTextFill(Color.WHITE);});
	}
	
	private void registerHandler2() {
		
		backButton.setOnMouseEntered(e -> {
			backButton.setFill(Color.WHITE);
			backButtonText.setTextFill(Color.LIGHTBLUE);});
		
		backButton.setOnMouseExited(e -> {
			backButton.setFill(Color.LIGHTBLUE);
			backButtonText.setTextFill(Color.WHITE);});
	}
	
	
}
