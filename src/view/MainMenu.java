package view;

import controller.DatabaseController;
import controller.MainController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainMenu extends Pane {
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
		loggedInUsername.setTextFill(Color.WHITE);
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
	
	
		final Rectangle rectBasicTimeline = new Rectangle(500, 250, 200, 200);
		rectBasicTimeline.setFill(Color.TRANSPARENT);
		rectBasicTimeline.setStroke(Color.BLACK);
		rectBasicTimeline.setStrokeWidth(20);
		
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		
		final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(),1500,
		 Interpolator.EASE_BOTH);
		final KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
		timeline.getKeyFrames().addAll(kf);
		timeline.play();
		
		this.getChildren().add(rectBasicTimeline);
		
		final Rectangle rectBasicTimeline2 = new Rectangle(500, 250, 200, 200);
		rectBasicTimeline2.setFill(Color.LIGHTBLUE);
		rectBasicTimeline2.setStroke(Color.BLACK);
		rectBasicTimeline2.setStrokeWidth(20);
		
		final Timeline timeline2 = new Timeline();
		timeline2.setCycleCount(Timeline.INDEFINITE);
		timeline2.setAutoReverse(true);
		
		final KeyValue kv2 = new KeyValue(rectBasicTimeline2.yProperty(),1500,
		 Interpolator.EASE_BOTH);
		final KeyFrame kf2 = new KeyFrame(Duration.millis(2000), kv2);
		timeline2.getKeyFrames().addAll(kf2);
		timeline2.play();
		
		this.getChildren().add(rectBasicTimeline2);
		
		final Label rectBasicTimeline3 = new Label("6");
		rectBasicTimeline3.setTextFill(Color.BLACK);
		rectBasicTimeline3.setLayoutX(575);
		rectBasicTimeline3.setLayoutY(310);
		rectBasicTimeline3.setStyle("-fx-font: 80 Arial;" + "-fx-fill: white;");
		
		final Timeline timeline3 = new Timeline();
		timeline3.setCycleCount(Timeline.INDEFINITE);
		timeline3.setAutoReverse(true);
		
		
		final KeyValue kv3 = new KeyValue(rectBasicTimeline3.layoutYProperty(),1500,
		 Interpolator.EASE_BOTH);
		final KeyFrame kf3 = new KeyFrame(Duration.millis(2000), kv3);
		timeline3.getKeyFrames().addAll(kf3);
		timeline3.play();
		
		this.getChildren().add(rectBasicTimeline3);
	
	}

	private class MenuButton extends Button {

		public MenuButton(String text) {
			super(text);

			String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
					+ "-fx-background-color: transparent;" + "-fx-font-size: 20;" + "-fx-text-fill: white;";
			String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
					+ "-fx-background-color:white;" + "-fx-font-size: 20;" + "-fx-text-fill:hotpink;";
			setStyle(IDLE_BUTTON_STYLE);
			setOnMouseEntered(e -> setStyle(HOVERED_BUTTON_STYLE));
			setOnMouseExited(e -> setStyle(IDLE_BUTTON_STYLE));
		}

	}

}