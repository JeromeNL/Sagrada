package view;

import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class EndScoreView extends Pane {

	private Rectangle outline;
	private Label title;
	private Label currentPlayerText, user1, user2, user3, user4, backButtonText, tag1;
	private Rectangle dropDownButton, backButton, line, line2, line3, line4, line5;

	public EndScoreView() {

// Score Table

		outline = new Rectangle(500, 500);
		outline.setTranslateX(50);
		outline.setTranslateY(150);
		outline.setFill(Color.TRANSPARENT);
		outline.setStrokeWidth(30);
		outline.setStroke(Color.BLACK);

		title = new Label("EindScore");
		title.setStyle("-fx-font-weight: bold");
		title.setTextFill(Color.BLACK);
		title.setFont(new Font("Arial", 60));
		title.setTranslateX(30);
		title.setTranslateY(50);

		tag1 = new Label("Private Objectives");
		tag1.setStyle("-fx-font-weight: bold");
		tag1.setTextFill(Color.BLACK);
		tag1.setFont(new Font("Arial", 35));
		tag1.setTranslateX(100);
		tag1.setTranslateY(190);

		line = new Rectangle(10, 500);
		line.setTranslateX(450);
		line.setTranslateY(150);
		line.setFill(Color.BLACK);

		line2 = new Rectangle(500, 10);
		line2.setTranslateX(50);
		line2.setTranslateY(250);
		line2.setFill(Color.BLACK);

		line3 = new Rectangle(500, 10);
		line3.setTranslateX(50);
		line3.setTranslateY(350);
		line3.setFill(Color.BLACK);

		line4 = new Rectangle(500, 10);
		line4.setTranslateX(50);
		line4.setTranslateY(450);
		line4.setFill(Color.BLACK);

		line5 = new Rectangle(500, 10);
		line5.setTranslateX(50);
		line5.setTranslateY(550);
		line5.setFill(Color.BLACK);

// TEXTBUTTONS

		user1 = new Label("Jasper");
		user1.setStyle("-fx-font-weight: bold");
		user1.setFont(new Font("Arial", 40));
		user1.setTranslateX(750);
		user1.setTranslateY(250);
		user1.setTextFill(Color.BLACK);

		user1.setOnMouseEntered(e -> user1.setUnderline(true));
		user1.setOnMouseExited(e -> user1.setUnderline(false));

		user2 = new Label("Janique");
		user2.setStyle("-fx-font-weight: bold");
		user2.setFont(new Font("Arial", 40));
		user2.setTranslateX(750);
		user2.setTranslateY(300);
		user2.setTextFill(Color.BLACK);

		user2.setOnMouseEntered(e -> user2.setUnderline(true));
		user2.setOnMouseExited(e -> user2.setUnderline(false));

		user3 = new Label("Imke");
		user3.setStyle("-fx-font-weight: bold");
		user3.setFont(new Font("Arial", 40));
		user3.setTranslateX(750);
		user3.setTranslateY(350);
		user3.setTextFill(Color.BLACK);

		user3.setOnMouseEntered(e -> user3.setUnderline(true));
		user3.setOnMouseExited(e -> user3.setUnderline(false));

		user4 = new Label("Mandy");
		user4.setStyle("-fx-font-weight: bold");
		user4.setFont(new Font("Arial", 40));
		user4.setTranslateX(750);
		user4.setTranslateY(400);
		user4.setTextFill(Color.BLACK);

		user4.setOnMouseEntered(e -> user4.setUnderline(true));
		user4.setOnMouseExited(e -> user4.setUnderline(false));

//Button

		backButton = new Rectangle(400, 400, 155, 50);
		backButton.setArcHeight(50);
		backButton.setArcWidth(50);
		backButton.setFill(Color.LIGHTBLUE);
		backButton.setTranslateY(210);
		backButton.setTranslateX(600);
		backButton.setStrokeWidth(10);
		backButton.setStroke(Color.BLACK);

		backButtonText = new Label("Terug");
		backButtonText.setTextFill(Color.BLACK);
		backButtonText.setFont(new Font("Arial", 15));
		backButtonText.setStyle("-fx-font-weight: bold");
		backButtonText.setTranslateY(625);
		backButtonText.setTranslateX(1050);

		setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(0), new Insets(0))));

		registerHandler(backButton, Color.BLACK, Color.BLACK);
		backButton.setOnMouseClicked(e -> {
			System.out.println("button is pressed");
			this.getChildren().clear();
			this.getChildren().addAll(outline, title, user1, user2, user3, user4, line, line2, line3, line4, line5,
					backButton, backButtonText, tag1);

		});
	}

	private void registerHandler(Shape s, Color defaultColor, Color hoverColor) {

		s.setOnMouseEntered(e -> {
			s.setFill(hoverColor);
			backButtonText.setTextFill(Color.WHITE);
		});

		s.setOnMouseExited(e -> {
			s.setFill(Color.LIGHTBLUE);
			backButtonText.setTextFill(Color.BLACK);
		});

		backButton = new Rectangle(400, 400, 155, 50);
		backButton.setArcHeight(50);
		backButton.setArcWidth(50);
		backButton.setFill(Color.LIGHTBLUE);
		backButton.setTranslateY(210);
		backButton.setTranslateX(600);
		backButton.setStrokeWidth(10);
		backButton.setStroke(Color.BLACK);

		backButtonText = new Label("Terug");
		backButtonText.setTextFill(Color.BLACK);
		backButtonText.setFont(new Font("Arial", 15));
		backButtonText.setStyle("-fx-font-weight: bold");
		backButtonText.setTranslateY(625);
		backButtonText.setTranslateX(1050);

		setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(0), new Insets(0))));

		registerHandler(backButton, Color.BLACK, Color.BLACK);
		this.getChildren().clear();

		this.getChildren().addAll(outline, title, user1, user2, user3, user4, line, line2, line3, line4, line5,
				backButton, backButtonText, tag1);

	}
}
