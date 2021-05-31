package view;

import java.util.ArrayList;

import controller.DatabaseController;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Die;
import model.Game;

public class RoundTrackView extends HBox {
	private int dieSize = 50;
	private DatabaseController dbController;
	private Game game;

	public RoundTrackView(DatabaseController dbController, Game game) {
		this.dbController = dbController;
		this.game = game;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);

		ArrayList<Die> diesOnRoundtrack = dbController.getDieRoundTrack(game.getGameID());
		int size = diesOnRoundtrack.size();

		for (int i = 0; i < 10; i++) {

			Rectangle emptyDie = new Rectangle(dieSize, dieSize);
			emptyDie.setStroke(Color.BLACK);
			emptyDie.setFill(Color.TRANSPARENT);

			emptyDie.setStrokeWidth(4);
			StackPane whiteDie = new StackPane();

			Text dieNumberText = new Text(String.valueOf(i + 1));
			dieNumberText.setFill(Color.GRAY);
			dieNumberText.setFont(new Font("Arial", 20));

			if (i < size) {
				if (diesOnRoundtrack.get(i) != null) {
					Die die = diesOnRoundtrack.get(i);
					Color color = die.getColor().toFXColor();
					emptyDie.setFill(color);

					dieNumberText.setText(String.valueOf(die.getEyesCount()));
					dieNumberText.setFill(Color.BLACK);
				}
			}

			whiteDie.getChildren().addAll(emptyDie, dieNumberText);
			getChildren().addAll(whiteDie);

		}
	}

	public void getDieRoundTrack() {
		dbController.getDieRoundTrack(game.getGameID());
	}

}
