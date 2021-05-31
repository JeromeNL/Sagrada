package view;

import controller.DatabaseController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.Patterncard;

public class ChoosePatternCardCards extends FlowPane {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);

	private PatternCardView patternCardView1;
	private PatternCardView patternCardView2;
	private PatternCardView patternCardView3;
	private PatternCardView patternCardView4;
	
	private Patterncard patterncard1;
	private Patterncard patterncard2;
	private Patterncard patterncard3;
	private Patterncard patterncard4;
	
	private DatabaseController dbController;
	

	private int widthCard = 315;
	private int heightCard = 315;

	public ChoosePatternCardCards(DatabaseController dbController) {
		super(Orientation.HORIZONTAL, 25, 25);
		this.dbController = dbController;
		
		this.setMinWidth(750);
		this.setMaxWidth(750);

		this.setPadding(new Insets(5, 5, 0, 5));
		

//		patterncard1 = new Patterncard(2, dbController);
//		patterncard2 = new Patterncard(5, dbController);
//		patterncard3 = new Patterncard(4, dbController);
//		patterncard4 = new Patterncard(2, dbController);

		

		patternCardView1 = new PatternCardView(patterncard1);
		patternCardView2 = new PatternCardView(patterncard2);
		patternCardView3 = new PatternCardView(patterncard3);
		patternCardView4 = new PatternCardView(patterncard4);

		setAlignment(Pos.CENTER);

		setBackground(new Background(new BackgroundFill(SAGRADAPINK, null, null)));

		addCards(4, 5, 6, 3);

	}

	private void addCards(int drop1, int drop2, int drop3, int drop4) {

		StackPane card1 = new StackPane();
		StackPane card2 = new StackPane();
		StackPane card3 = new StackPane();
		StackPane card4 = new StackPane();

		BorderPane viewDrops1 = new BorderPane();
		BorderPane viewDrops2 = new BorderPane();
		BorderPane viewDrops3 = new BorderPane();
		BorderPane viewDrops4 = new BorderPane();

		FractalDropsView fractalDrops1 = new FractalDropsView(3);
		FractalDropsView fractalDrops2 = new FractalDropsView(4);
		FractalDropsView fractalDrops3 = new FractalDropsView(5);
		FractalDropsView fractalDrops4 = new FractalDropsView(6);

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

		viewDrops1.setCenter(patternCardView1);
		viewDrops1.setBottom(fractalDrops1);

		viewDrops2.setCenter(patternCardView2);
		viewDrops2.setBottom(fractalDrops2);

		viewDrops3.setCenter(patternCardView3);
		viewDrops3.setBottom(fractalDrops3);

		viewDrops4.setCenter(patternCardView4);
		viewDrops4.setBottom(fractalDrops4);

		card1.getChildren().addAll(rectanglePatternCard1, viewDrops1);
		card2.getChildren().addAll(rectanglePatternCard2, viewDrops2);
		card3.getChildren().addAll(rectanglePatternCard3, viewDrops3);
		card4.getChildren().addAll(rectanglePatternCard4, viewDrops4);

		getChildren().addAll(card1, card2, card3, card4);

		viewDrops1.setMouseTransparent(true);
		viewDrops2.setMouseTransparent(true);
		viewDrops3.setMouseTransparent(true);
		viewDrops4.setMouseTransparent(true);

		rectanglePatternCard1.setOnMouseClicked(e -> {
			System.out.println("click " + "card1");
			rectanglePatternCard1.selects();
			rectanglePatternCard2.notSelect();
			rectanglePatternCard3.notSelect();
			rectanglePatternCard4.notSelect();

		});

		rectanglePatternCard2.setOnMouseClicked(e -> {
			System.out.println("click " + "card2");
			rectanglePatternCard2.selects();
			rectanglePatternCard1.notSelect();
			rectanglePatternCard3.notSelect();
			rectanglePatternCard4.notSelect();

		});

		rectanglePatternCard3.setOnMouseClicked(e -> {
			System.out.println("click " + "card3");
			rectanglePatternCard3.selects();
			rectanglePatternCard2.notSelect();
			rectanglePatternCard1.notSelect();
			rectanglePatternCard4.notSelect();

		});

		rectanglePatternCard4.setOnMouseClicked(e -> {
			System.out.println("click " + "card4");
			rectanglePatternCard4.selects();
			rectanglePatternCard2.notSelect();
			rectanglePatternCard3.notSelect();
			rectanglePatternCard1.notSelect();

		});

	}

}
