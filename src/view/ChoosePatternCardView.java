package view;

import controller.ChoosePatternCardController;
import controller.MainController;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.Player;

public class ChoosePatternCardView extends BorderPane {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	private ChoosePatternCardCards choosePatternCardCards;
	private ChooseButton button;

	private int cardId;
	private boolean hasCard;

	public ChoosePatternCardView(ChoosePatternCardController choosePatternCardController, MainController mainController, Player player) {
		choosePatternCardCards = new ChoosePatternCardCards(choosePatternCardController, mainController);
		button = new ChooseButton();
		hasCard = false;

		button.setOnMouseClicked(e -> {
			if (choosePatternCardCards.rectanglePatternCard1.select == true) {
				cardId = choosePatternCardController.getPatternCard().get(0).getIdPatternCard();
				hasCard = true;
				player.setPatternCard(cardId);
				mainController.showGameLoggedInPlayer();
			} else if (choosePatternCardCards.rectanglePatternCard2.select == true) {
				cardId = choosePatternCardController.getPatternCard().get(1).getIdPatternCard();
				hasCard = true;
				player.setPatternCard(cardId);
				mainController.showGameLoggedInPlayer();
			} else if (choosePatternCardCards.rectanglePatternCard3.select == true) {
				cardId = choosePatternCardController.getPatternCard().get(2).getIdPatternCard();
				hasCard = true;
				player.setPatternCard(cardId);
				mainController.showGameLoggedInPlayer();
			} else if (choosePatternCardCards.rectanglePatternCard4.select == true) {
				cardId = choosePatternCardController.getPatternCard().get(3).getIdPatternCard();
				hasCard = true;
				player.setPatternCard(cardId);
				mainController.showGameLoggedInPlayer();
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
