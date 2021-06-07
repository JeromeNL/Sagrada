package view;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.MainController;
import imageChooser.PublicObjectiveCardImage;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ObjectivecardCardView extends Pane {

	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	static final String URL_TO_IMAGE = "/Group_24.png";
	RectangleCard rectangleCard1;
	private DatabaseController dbController;
	private Rectangle rect;
	private int X = 135;
	private StackPane card;
	private Label backButtonText;
	private MainController mainController;

	public ObjectivecardCardView(DatabaseController dbController, MainController mainController) {
		super();
		this.mainController = mainController;

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

		rect.setOnMouseClicked(e -> {

			mainController.loadGame(mainController.getCurrentGame().getIdGame());
			mainController.showGameLoggedInPlayer();
		});

		rect.setOnMouseEntered(e -> {
			rect.setFill(Color.WHITE);
			backButtonText.setTextFill(SAGRADAPINK);
		});

		rect.setOnMouseExited(e -> {
			rect.setFill(SAGRADAPINK);
			backButtonText.setTextFill(Color.WHITE);
		});

		this.dbController = dbController;
		setBackground(new Background(new BackgroundFill(SAGRADAPINK, null, null)));

		addCards();

	}

	public void addCards() {

		ArrayList<Integer> ids = dbController.getPublicObjectiveIDs(mainController.getCurrentGame().getIdGame());

		for (Integer id : ids) {
			card = new StackPane();
			PublicObjectiveCardImage publicObjectiveCardImage = new PublicObjectiveCardImage(id);
			publicObjectiveCardImage.setTranslateX(X);
			X = X + 350;
			publicObjectiveCardImage.setTranslateY(200);
			card.getChildren().add(publicObjectiveCardImage);
			getChildren().add(card);
		}

		getChildren().add(rect);
		getChildren().add(backButtonText);

	}
}
