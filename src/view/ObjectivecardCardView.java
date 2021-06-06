package view;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.MainController;
import imageChooser.PublicObjectiveCardImage;
import javafx.beans.value.ObservableValue;



import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

public class ObjectivecardCardView extends Pane {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	static final String URL_TO_IMAGE = "/Group_24.png";
	RectangleCard rectangleCard1 ;
	private int widthCard = 230;
	private int heightCard = 334;
	private DatabaseController dbController;
	private MainController mainController;
	private Rectangle rect;
	private int X=135;
	private StackPane card;
	private Label backButtonText;


	public ObjectivecardCardView(DatabaseController dbController, MainController mainController) {
		super();
		
		rect = new Rectangle();
		rect.setWidth(200);
		rect.setHeight(60);
		rect.setFill(Color.TRANSPARENT);
		rect.setX(940);
		rect.setY(600);
		rect.setArcHeight(50);
		rect.setArcWidth(50);
		rect.setStrokeWidth(5);
		rect.setStroke(Color.WHITE);
		
		backButtonText = new Label("Terug");
		 backButtonText.setTextFill(Color.WHITE);
		 backButtonText.setFont(new Font("Arial", 15));
		 backButtonText.setStyle("-fx-font-weight: bold");
		 backButtonText.setTranslateY(620);
		 backButtonText.setTranslateX(1015);
		 
		 rect.setOnMouseClicked(e -> {System.out.println("button is pressed");
		 mainController.showGameLoggedInPlayer();
		 });
		
		this.dbController = dbController;
		this.mainController = mainController;
	

		setBackground(new Background(new BackgroundFill(SAGRADAPINK, null, null)));

		addCards();
//		showImage();

	}

	public void addCards() {



		
		ArrayList<Integer> ids = dbController.getPublicObjectiveIDs(55);

//		ArrayList<Integer> ids = dbController.getPublicObjectiveIDs(mainController.getCurrentGame().getIdGame());
		
		for (Integer id : ids) {
			card = new StackPane();
			PublicObjectiveCardImage publicObjectiveCardImage = new PublicObjectiveCardImage(id);
			publicObjectiveCardImage.setTranslateX(X);
			X=X+350;
			publicObjectiveCardImage.setTranslateY(200);
			card.getChildren().add(publicObjectiveCardImage);
		    getChildren().add(card);
		}
		
		getChildren().add(rect);
		getChildren().add(backButtonText);

	}
}
