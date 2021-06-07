package view;

import java.util.ArrayList;

import controller.MainController;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.Game;
import model.Player;

public class ChangeCurrentPlayerView extends StackPane {

	private MainController mainController;
	private VBox playersVBox;

	public ChangeCurrentPlayerView(Game game, MainController mainController) {
		this.mainController = mainController;

		Rectangle background = new Rectangle(1280, 720);
		background.setFill(Color.LIGHTBLUE);

		Rectangle animation = new Rectangle(700, 700);
		animation.setFill(Color.TRANSPARENT);
		animation.setStrokeWidth(20);
		animation.setStroke(Color.BLACK);
		animation.setRotate(30);

		RotateTransition rt = new RotateTransition(Duration.millis(3000), animation);
		rt.setByAngle(180);
		rt.setCycleCount(500);
		rt.setAutoReverse(true);
		rt.play();

		playersVBox = new VBox();
		playersVBox.setSpacing(20);
		playersVBox.setCenterShape(true);
		playersVBox.setAlignment(Pos.CENTER);

		ArrayList<Player> players = game.getPlayers();
		for (Player player : players) {
			UserLabel userLabel = new UserLabel(player.getUsername());
			if (mainController.getLoggedInUsername().equals(player.getUsername())) {
				userLabel = new UserLabel(player.getUsername() + " (Jij)");
			}
			playersVBox.getChildren().add(userLabel);
		}

		getChildren().addAll(background, animation, playersVBox);
	}

	private class UserLabel extends Label {

		public UserLabel(String playerName) {
			super(playerName);
			setStyle("-fx-font-weight: bold");
			setFont(new Font("Arial", 40));

			setOnMouseEntered(e -> setUnderline(true));
			setOnMouseExited(e -> setUnderline(false));
			
			setOnMouseClicked(e->mainController.showGame(playersVBox.getChildren().indexOf(this)));
		}
	}

}
