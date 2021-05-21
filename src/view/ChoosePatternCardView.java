package view;

import controller.ChoosePatternCardController;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class ChoosePatternCardView extends BorderPane {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	private ChoosePatternCardCards choosePatternCardCards;
	private ChooseButton button;
	private ChoosePatternCardController choosePatternCardController;
	
	private int cardId;
	private boolean hasCard;
	

	public ChoosePatternCardView(ChoosePatternCardController choosePatternCardController) {
		choosePatternCardCards = new ChoosePatternCardCards(choosePatternCardController);
		button = new ChooseButton();
		hasCard = false;
		
		
		
		button.setOnMouseClicked(e->
		{ if(choosePatternCardCards.rectanglePatternCard1.select == true) {
			System.out.println(choosePatternCardController.getPatternCard().get(0).getIdPatternCard());
			cardId = choosePatternCardController.getPatternCard().get(0).getIdPatternCard();
			hasCard = true;
			
		} else if(choosePatternCardCards.rectanglePatternCard2.select == true) {
			System.out.println(choosePatternCardController.getPatternCard().get(1).getIdPatternCard());
			cardId = choosePatternCardController.getPatternCard().get(1).getIdPatternCard();
			hasCard = true;
			
		} else if(choosePatternCardCards.rectanglePatternCard3.select == true) {
			System.out.println(choosePatternCardController.getPatternCard().get(2).getIdPatternCard());
			cardId = choosePatternCardController.getPatternCard().get(2).getIdPatternCard();
			hasCard = true;
			
		} else if(choosePatternCardCards.rectanglePatternCard4.select == true) {
			System.out.println(choosePatternCardController.getPatternCard().get(3).getIdPatternCard());
			cardId = choosePatternCardController.getPatternCard().get(3).getIdPatternCard();
			hasCard = true;
			
		} else
		 
		System.out.println("nothing is selected please select a card");
		hasCard = false;
		
		});
		
		

		this.setBackground(new Background(new BackgroundFill(SAGRADAPINK, null, null)));

		this.setCenter(choosePatternCardCards);
		this.setBottom(button);

	}
	
	public int getCard() {
		return cardId;
	}
	
	
	public boolean hasCard() {
		return hasCard;
	}

}
