package view;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.MainController;
import imageChooser.PublicObjectiveCardImage;
import javafx.beans.value.ObservableValue;



import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class ObjectivecardCardView extends HBox {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	static final String URL_TO_IMAGE = "/Group_24.png";
RectangleCard rectangleCard1 ;
	private int widthCard = 230;
	private int heightCard = 334;
	private DatabaseController dbController;
	private MainController mainController;

	public ObjectivecardCardView(DatabaseController dbController, MainController mainController) {
		super();

		this.dbController = dbController;
		this.mainController = mainController;
		setSpacing(128);
		setAlignment(Pos.CENTER);

		setBackground(new Background(new BackgroundFill(SAGRADAPINK, null, null)));

		addCards();
//		showImage();

	}

	public void addCards() {

		Image group = new Image(getClass().getResource(URL_TO_IMAGE).toString());

		ImageView imageView = new ImageView(group);
		ImageView imageView2 = new ImageView(group);
		ImageView imageView3 = new ImageView(group);

		imageView.setFitHeight(heightCard - 30);
		imageView.setFitWidth(widthCard - 30);
		imageView2.setFitHeight(heightCard - 30);
		imageView2.setFitWidth(widthCard - 30);
		imageView3.setFitHeight(heightCard - 30);
		imageView3.setFitWidth(widthCard - 30);
		
		ArrayList<Integer> ids = dbController.getPublicObjectiveIDs(55);

//		ArrayList<Integer> ids = dbController.getPublicObjectiveIDs(mainController.getCurrentGame().getIdGame());
		
		for (Integer id : ids) {
			StackPane card = new StackPane();
			PublicObjectiveCardImage publicObjectiveCardImage = new PublicObjectiveCardImage(id);
			publicObjectiveCardImage.setTranslateX(15);
			publicObjectiveCardImage.setTranslateY(200);
			card.getChildren().add(publicObjectiveCardImage);
			getChildren().add(card);
		}
	

	}
}
