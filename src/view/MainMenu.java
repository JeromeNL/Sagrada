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


//		InputStream stream;
//		try {
//			stream = new FileInputStream("LoginImg.png");
//			Image image = new Image(stream);
//			imageView = new ImageView();
//			imageView.setImage(image);
//			imageView.setTranslateX(600);
//			imageView.setFitWidth(575);
//			imageView.setPreserveRatio(true);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}

		VBox box = new VBox(20);
		HBox layout = new HBox();
		VBox buttons = new VBox();
		StackPane colorBurn = new StackPane();
		VBox sagradaText = new VBox();
		
		Button spelen = new Button("Spelen");
		Button spelersOverzicht = new Button("SpelersOverzicht");
		Button spelOverzicht = new Button("SpelOverzicht");
		
		
		Text sagradaTxt = new Text("Sagrada ");
//		sagradaTxt.setTranslateX(-300);
//		sagradaTxt.setTranslateY(200);

//		Rectangle burnOverlay = new Rectangle();
//		box.setWidth(500);
//		box.setHeight(700);
//		burnOverlay.setFill(Color.TRANSPARENT);
//		burnOverlay.setBlendMode(BlendMode.COLOR_BURN);
		
		
		box.getChildren().addAll(sagradaText, layout);
//		colorBurn.getChildren().addAll(burnOverlay);
//		buttons.setAlignment(Pos.BASELINE_LEFT);
		HBox.setMargin(buttons, new Insets(50, 10, 40, 100));
		buttons.setSpacing(10);
		layout.getChildren().addAll(buttons);
		buttons.getChildren().addAll(spelen, spelersOverzicht, spelOverzicht);
		sagradaText.getChildren().addAll(sagradaTxt);
		box.setAlignment(Pos.CENTER);
		box.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));
		
		sagradaText.setAlignment(Pos.CENTER_LEFT);
		VBox.setMargin(sagradaTxt, new Insets(100, 10, 0, 100));
		layout.setAlignment(Pos.CENTER_LEFT);

		
		sagradaText.setMinHeight(100);
		sagradaTxt.setStyle("-fx-font: 80 Arial;" +
				"-fx-fill: white;");
		
		this.getChildren().add(box);
		this.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));

		String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color: transparent;" + "-fx-font-size: 20;" + "-fx-text-fill: white;";
		String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color:white;" + "-fx-font-size: 20;" + "-fx-text-fill:blue;";

		spelen.setStyle(IDLE_BUTTON_STYLE);
		spelen.setOnMouseEntered(e -> spelen.setStyle(HOVERED_BUTTON_STYLE));
		spelen.setOnMouseExited(e -> spelen.setStyle(IDLE_BUTTON_STYLE));
		
		spelersOverzicht.setStyle(IDLE_BUTTON_STYLE);
		spelersOverzicht.setOnMouseEntered(e -> spelersOverzicht.setStyle(HOVERED_BUTTON_STYLE));
		spelersOverzicht.setOnMouseExited(e -> spelersOverzicht.setStyle(IDLE_BUTTON_STYLE));
		
		spelOverzicht.setStyle(IDLE_BUTTON_STYLE);
		spelOverzicht.setOnMouseEntered(e -> spelOverzicht.setStyle(HOVERED_BUTTON_STYLE));
		spelOverzicht.setOnMouseExited(e -> spelOverzicht.setStyle(IDLE_BUTTON_STYLE));


		
		
		// Event after clicking the "Login" button
		spelen.setOnMouseClicked(e -> {
			mainController.showMainMenu();
		});

	}

}