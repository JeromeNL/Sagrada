package view;

import controller.DatabaseController;
import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainMenu extends VBox {
//	private ImageView imageView;
	public Button spelen;
	public Button spelersOverzicht;
	public Button spellenOverzicht;

	public MainMenu(DatabaseController dbController, MainController mainController) {

		VBox box = new VBox(20);
		HBox layout = new HBox();
		VBox sagradaText = new VBox();

		// Add buttons
		Button spelen = new MenuButton("Nieuw spel aanmaken");
		spelen.setOnMouseClicked(e -> mainController.showNewGame());
		Button yourGames = new MenuButton("Bestaande spellen");
		yourGames.setOnMouseClicked(e -> mainController.showYourGames());
		Button challenges = new MenuButton("Openstaande uitdagingen");
		challenges.setOnMouseClicked(e -> mainController.showOpenChallenges());
		Button spelersOverzicht = new MenuButton("Spelers Overzicht");
		spelersOverzicht.setOnMouseClicked(e -> mainController.showPlayerListView());
		Button spellenOverzicht = new MenuButton("Spellen Overzicht");
		spellenOverzicht.setOnMouseClicked(e -> mainController.showPlayedGames());
		Button logout = new MenuButton("Uitloggen");
		logout.setOnMouseClicked(e -> mainController.logout());

		VBox buttons = new VBox();
		Label loggedInUsername = new Label("Logged in user: " + mainController.getLoggedInUsername());
		buttons.getChildren().addAll(loggedInUsername, spelen, yourGames, challenges, spelersOverzicht, spellenOverzicht,
				logout);
		buttons.setSpacing(10);

		Text sagradaTxt = new Text("Sagrada");
		sagradaText.setAlignment(Pos.CENTER_LEFT);

		sagradaText.getChildren().addAll(sagradaTxt);

		layout.getChildren().addAll(buttons);
		box.getChildren().addAll(sagradaText, layout);

		HBox.setMargin(buttons, new Insets(50, 10, 40, 100));
		box.setAlignment(Pos.CENTER);
		box.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));

		sagradaText.setAlignment(Pos.CENTER_LEFT);

		VBox.setMargin(sagradaTxt, new Insets(100, 10, 0, 100));
		layout.setAlignment(Pos.CENTER_LEFT);

		sagradaText.setMinHeight(100);
		sagradaTxt.setStyle("-fx-font: 80 Arial;" + "-fx-fill: white;");

		this.getChildren().add(box);
		this.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private class MenuButton extends Button {

		public MenuButton(String text) {
			super(text);

			String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
					+ "-fx-background-color: transparent;" + "-fx-font-size: 20;" + "-fx-text-fill: white;";
			String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
					+ "-fx-background-color:white;" + "-fx-font-size: 20;" + "-fx-text-fill:blue;";
			setStyle(IDLE_BUTTON_STYLE);
			setOnMouseEntered(e -> setStyle(HOVERED_BUTTON_STYLE));
			setOnMouseExited(e -> setStyle(IDLE_BUTTON_STYLE));
		}

	}

}