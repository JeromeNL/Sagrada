package view;

import java.util.ArrayList;

import controller.MainController;
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
import model.Player;

public class EndScoreView extends Pane {

	private Rectangle outline;
	private Label title;
	private Label backButtonText, player1, player2, player3, player4;
	private Rectangle backButton, line, line2, line3, line4, line5;
	private int playerOne, playerTwo, playerThree, playerFour;

	public EndScoreView(int newPlayerOne, int newPlayerTwo, int newPlayerThree, int newPlayerFour,
			ArrayList<Player> players, MainController mainController) {

		playerOne = newPlayerOne;
		playerTwo = newPlayerTwo;
		playerThree = newPlayerThree;
		playerFour = newPlayerFour;

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

		player1 = new Label(players.get(0).getUsername() + "   " + playerOne);
		player1.setStyle("-fx-font-weight: bold");
		player1.setTextFill(Color.BLACK);
		player1.setFont(new Font("Arial", 35));
		player1.setTranslateX(100);
		player1.setTranslateY(190);

		player2 = new Label(players.get(1).getUsername() + "   " + playerTwo);
		player2.setStyle("-fx-font-weight: bold");
		player2.setTextFill(Color.BLACK);
		player2.setFont(new Font("Arial", 35));
		player2.setTranslateX(100);
		player2.setTranslateY(285);

		if (players.size() > 2) {
			player3 = new Label(players.get(2).getUsername() + "   " + playerThree);
			player3.setStyle("-fx-font-weight: bold");
			player3.setTextFill(Color.BLACK);
			player3.setFont(new Font("Arial", 35));
			player3.setTranslateX(100);
			player3.setTranslateY(380);
		}

		if (players.size() > 3) {
			player4 = new Label(players.get(3).getUsername() + "   " + playerFour);
			player4.setStyle("-fx-font-weight: bold");
			player4.setTextFill(Color.BLACK);
			player4.setFont(new Font("Arial", 35));
			player4.setTranslateX(100);
			player4.setTranslateY(475);
		}

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

		System.out.println("0000000000000000000000000000000000000000000000 " + playerThree);

		if ((playerThree == 786) && (playerFour == 786)) {

			this.getChildren().addAll(outline, title, line, line2, line3, line4, line5, backButton, backButtonText,
					player1, player2);

			System.out.println("111111111111111111111111111111111111111111111111111111111111111111111111111");

		} else if ((playerThree != 786) && (playerFour == 786)) {

			this.getChildren().addAll(outline, title, line, line2, line3, line4, line5, backButton, backButtonText,
					player1, player2, player3);

			System.out.println("22222222222222222222222222222222222222222222222222222222222222222222222222");

		} else if ((playerThree != 786) && (playerFour != 786)) {

			this.getChildren().addAll(outline, title, line, line2, line3, line4, line5, backButton, backButtonText,
					player1, player2, player3, player4);

			System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333");
		}

		registerHandler(backButton, Color.BLACK, Color.BLACK);
		backButton.setOnMouseClicked(e -> {
			mainController.showFirstMainMenu();
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
	}

}
