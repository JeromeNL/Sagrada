package view;

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

	public ObjectivecardCardView() {
		super();

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

		StackPane card1 = new StackPane();
		StackPane card2 = new StackPane();
		StackPane card3 = new StackPane();






		RectangleCard rectangleCard1 = new RectangleCard(widthCard, heightCard, "card1");
		RectangleCard rectangleCard2 = new RectangleCard(widthCard, heightCard, "card2");
		RectangleCard rectangleCard3 = new RectangleCard(widthCard, heightCard, "card3");
		
		card1.getChildren().addAll(rectangleCard1, imageView);
		card2.getChildren().addAll(rectangleCard2, imageView2);
		card3.getChildren().addAll(rectangleCard3, imageView3);

		getChildren().addAll(card1, card2, card3);

	}

}
