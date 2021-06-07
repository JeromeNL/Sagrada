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
	private int difficulty;
	private boolean hasCard;

	public ChoosePatternCardView(ChoosePatternCardController choosePatternCardController, MainController mainController,
			Player player) {
		choosePatternCardCards = new ChoosePatternCardCards(choosePatternCardController, mainController);
		button = new ChooseButton();
		hasCard = false;

		button.setOnMouseClicked(e -> {
			if (choosePatternCardCards.rectanglePatternCard1.select == true) {
				cardId = choosePatternCardController.getPatternCard().get(0).getIdPatternCard();
				difficulty = choosePatternCardController.getPatternCard().get(0).getDifficulty();
				System.out.println(player.getIdPlayer());
				System.out.println(difficulty);
				hasCard = true;
				player.setPatternCard(cardId);
				mainController.getCurrentGame().assignFavorTokens(difficulty, player.getIdPlayer());
			} else if (choosePatternCardCards.rectanglePatternCard2.select == true) {
				cardId = choosePatternCardController.getPatternCard().get(1).getIdPatternCard();
				difficulty = choosePatternCardController.getPatternCard().get(1).getDifficulty();
				System.out.println(player.getIdPlayer());
				System.out.println(difficulty);
				hasCard = true;
				player.setPatternCard(cardId);
				mainController.getCurrentGame().assignFavorTokens(difficulty, player.getIdPlayer());

			} else if (choosePatternCardCards.rectanglePatternCard3.select == true) {
				cardId = choosePatternCardController.getPatternCard().get(2).getIdPatternCard();
				difficulty = choosePatternCardController.getPatternCard().get(2).getDifficulty();
				System.out.println(player.getIdPlayer());
				System.out.println(difficulty);
				hasCard = true;
				player.setPatternCard(cardId);
				mainController.getCurrentGame().assignFavorTokens(difficulty, player.getIdPlayer());

			} else if (choosePatternCardCards.rectanglePatternCard4.select == true) {
				cardId = choosePatternCardController.getPatternCard().get(3).getIdPatternCard();
				difficulty = choosePatternCardController.getPatternCard().get(3).getDifficulty();
				System.out.println(player.getIdPlayer());
				System.out.println(difficulty);
				hasCard = true;
				player.setPatternCard(cardId);
				mainController.getCurrentGame().assignFavorTokens(difficulty, player.getIdPlayer());

			} else {
				System.out.println("nothing is selected please select a card");
				hasCard = false;
			}
			System.out.println("CHECK");
			mainController.loadGame(mainController.getCurrentGame().getIdGame());
			mainController.showGameLoggedInPlayer();
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
