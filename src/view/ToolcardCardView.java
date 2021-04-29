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

public class ToolcardCardView extends HBox {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	private static final String URL_TO_IMAGE = "/images/Group_24.png";

	private int widthCard = 230;
	private int heightCard = 334;

	public ToolcardCardView() {
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
		
		imageView.setFitHeight(heightCard-30);
		imageView.setFitWidth(widthCard-30);
		imageView2.setFitHeight(heightCard-30);
		imageView2.setFitWidth(widthCard-30);
		imageView3.setFitHeight(heightCard-30);
		imageView3.setFitWidth(widthCard-30);
		

		StackPane card1 = new StackPane();
		StackPane card2 = new StackPane();
		StackPane card3 = new StackPane();
		

		Rectangle r1 = new Rectangle(widthCard, heightCard);
		r1.setFill(Color.WHITE);
		r1.setArcWidth(29.0);
		r1.setArcHeight(29.0);
		card1.getChildren().addAll(r1, imageView); 
		
		r1.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if(show) {
				r1.setStroke(Color.LIGHTPINK);
				r1.setStrokeWidth(4.0);
				r1.setStrokeType(StrokeType.INSIDE);
			} else {

				r1.setStrokeWidth(0.0);
		
			}
		});
		
		r1.setOnMouseClicked(e->
			{
			
			System.out.println("click card1");
		});

		Rectangle r2 = new Rectangle(widthCard, heightCard);
		r2.setFill(Color.WHITE);
		r2.setArcWidth(29.0);
		r2.setArcHeight(29.0);
		card2.getChildren().addAll(r2, imageView2);
		
		r2.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if(show) {
				r2.setStroke(Color.LIGHTPINK);
				r2.setStrokeWidth(4.0);
				r2.setStrokeType(StrokeType.INSIDE);
			} else {

				r2.setStrokeWidth(0.0);
		
			}
		});
		
		r2.setOnMouseClicked(e->
			{
			
			System.out.println("click card2");
		});


		Rectangle r3 = new Rectangle(widthCard, heightCard);
		r3.setFill(Color.WHITE);
		r3.setArcWidth(29.0);
		r3.setArcHeight(29.0);
		card3.getChildren().addAll(r3, imageView3);
		r3.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if(show) {
				r3.setStroke(Color.LIGHTPINK);
				r3.setStrokeWidth(4.0);
				r3.setStrokeType(StrokeType.INSIDE);
			} else {

				r3.setStrokeWidth(0.0);
		
			}
		});
		
		r3.setOnMouseClicked(e->
			{
			
			System.out.println("click card3");
		});
		
		getChildren().addAll(card1, card2, card3);

	}


}
