package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import controller.DatabaseController;
import controller.MainController;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu extends VBox {
	private ImageView imageView;
	public Button spelen;
	public Button spelersOverzicht;
	public Button spelOverzicht;
	private MainController mainController;

	public MainMenu(DatabaseController dbController, MainController mainController) {

		VBox box = new VBox(20);
		HBox layout = new HBox();
		VBox sagradaText = new VBox();

		// Add buttons
		Button spelen = new MenuButton("Spelen");
		spelen.setOnMouseClicked(e -> mainController.showMainMenu());
		Button challenges = new MenuButton("Openstaande uitdagingen");
		challenges.setOnMouseClicked(e->mainController.showOpenChallenges());
		Button spelersOverzicht = new MenuButton("SpelersOverzicht");
		Button spelOverzicht = new MenuButton("SpelOverzicht");

		VBox buttons = new VBox();
		buttons.getChildren().addAll(spelen, challenges, spelersOverzicht, spelOverzicht);
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