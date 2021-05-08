package view;


import controller.DatabaseController;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import model.Patterncard;

public class PatternCardHBox extends FlowPane{

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	
	private PatternCardView patternCardView1;
	private PatternCardView patternCardView2;
	private PatternCardView patternCardView3;
	private PatternCardView patternCardView4;
	
	private DatabaseController dbcontroller;

	private int widthCard = 315;
	private int heightCard = 315;

	
	public PatternCardHBox(){
		super(Orientation.HORIZONTAL , 25, 25);

		this.setMinWidth(750);
		this.setMaxWidth(750);
		
		this.setPadding(new Insets(5, 5, 0, 5));
		
		DatabaseController dbcontroller = new DatabaseController();
		
		Patterncard patterncard1 = new Patterncard(1, dbcontroller);
		Patterncard patterncard2 = new Patterncard(1, dbcontroller);
		Patterncard patterncard3 = new Patterncard(1, dbcontroller);
		Patterncard patterncard4 = new Patterncard(1, dbcontroller);		
		
		patternCardView1 = new PatternCardView(patterncard1);		
		patternCardView2 = new PatternCardView(patterncard2);	
		patternCardView3 = new PatternCardView(patterncard3);	
		patternCardView4 = new PatternCardView(patterncard4);	
		
		setAlignment(Pos.CENTER);

		setBackground(new Background(new BackgroundFill(SAGRADAPINK, null, null)));
		
		addCards();
		
	}
	
	private void addCards(){
		
		
		
		StackPane card1 = new StackPane();
		StackPane card2 = new StackPane();
		StackPane card3 = new StackPane();
		StackPane card4 = new StackPane();
		

		
		RectangleCard rectanglePatternCard1 = new RectangleCard(widthCard, heightCard, "card1");
		RectangleCard rectanglePatternCard2 = new RectangleCard(widthCard, heightCard, "card2");
		RectangleCard rectanglePatternCard3 = new RectangleCard(widthCard, heightCard, "card3");
		RectangleCard rectanglePatternCard4 = new RectangleCard(widthCard, heightCard, "card4");
		
		patternCardView1.setMaxWidth(50);
		patternCardView1.setMaxHeight(50);
		patternCardView2.setMaxWidth(50);
		patternCardView2.setMaxHeight(50);		
		patternCardView3.setMaxWidth(50);
		patternCardView3.setMaxHeight(50);	
		patternCardView4.setMaxWidth(50);
		patternCardView4.setMaxHeight(50);		


		
		card1.getChildren().addAll(rectanglePatternCard1, patternCardView1);
		card2.getChildren().addAll(rectanglePatternCard2, patternCardView2);
		card3.getChildren().addAll(rectanglePatternCard3, patternCardView3);
		card4.getChildren().addAll(rectanglePatternCard4, patternCardView4);		
		
		
		getChildren().addAll(card1, card2, card3, card4);
		
		patternCardView1.hoverProperty()
		.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if (show) {
				rectanglePatternCard1.setStroke(Color.LIGHTPINK);
				rectanglePatternCard1.setStrokeWidth(4.0);
				rectanglePatternCard1.setStrokeType(StrokeType.INSIDE);
			} else {
				rectanglePatternCard1.setStrokeWidth(0.0);

			}
		});

		patternCardView1.setOnMouseClicked(e -> {
			System.out.println("click " + "card1");
		});
		
		patternCardView2.hoverProperty()
		.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if (show) {
				rectanglePatternCard2.setStroke(Color.LIGHTPINK);
				rectanglePatternCard2.setStrokeWidth(4.0);
				rectanglePatternCard2.setStrokeType(StrokeType.INSIDE);
			} else {
				rectanglePatternCard2.setStrokeWidth(0.0);

			}
		});

		patternCardView2.setOnMouseClicked(e -> {
			System.out.println("click " + "card2");
		});
		
		patternCardView3.hoverProperty()
		.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if (show) {
				rectanglePatternCard3.setStroke(Color.LIGHTPINK);
				rectanglePatternCard3.setStrokeWidth(4.0);
				rectanglePatternCard3.setStrokeType(StrokeType.INSIDE);
			} else {
				rectanglePatternCard3.setStrokeWidth(0.0);

			}
		});

		patternCardView3.setOnMouseClicked(e -> {
			System.out.println("click " + "card3");
		});
		
		patternCardView4.hoverProperty()
		.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if (show) {
				rectanglePatternCard4.setStroke(Color.LIGHTPINK);
				rectanglePatternCard4.setStrokeWidth(4.0);
				rectanglePatternCard4.setStrokeType(StrokeType.INSIDE);
			} else {
				rectanglePatternCard4.setStrokeWidth(0.0);

			}
		});

		patternCardView4.setOnMouseClicked(e -> {
			System.out.println("click " + "card4");
		});
		
	}
	
	

	
}
