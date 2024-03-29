package view;

import controller.DatabaseController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Game;

public class TopPart extends VBox {

	private PlayerOrderView playerOrderView;
	private RoundTrackView roundTrackView;
	
	public TopPart(DatabaseController dbController, Game game) {
		this.setPadding(new Insets(8, 5, 15, 5));

		setMinSize(900, 120);
		setMaxSize(900, 120);

		playerOrderView = new PlayerOrderView(game);
		roundTrackView = new RoundTrackView(dbController, game);

		setAlignment(Pos.CENTER);
		setSpacing(15);
		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0, 0, 20, 20, false), null)));

		this.getChildren().addAll(playerOrderView, roundTrackView);
	}
}
