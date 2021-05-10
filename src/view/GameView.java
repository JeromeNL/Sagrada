package view;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.DiesInSupply;
import model.Game;
import model.Patterncard;
import model.Player;

public class GameView extends BorderPane {

	private RoundtrackView roundtrackView;
	private PatternCardView patternCardView;
	private DieSupply dieSupply;
	private GameButtonView gameButtonView;
	private ObjectiveInGameView objectiveInGameView;
	private ChangeCurrentPlayerView changeCurrentPlayerView;
	private Game game;
	private Player player;
	

	public GameView(Game game, Player player) {
		this.game = game;
		this.player = player;
		Patterncard playerPatterncard = player.getPatterncard();
		
		objectiveInGameView = new ObjectiveInGameView();
		patternCardView = new PatternCardView(playerPatterncard);
		
		roundtrackView = new RoundtrackView();
		dieSupply = new DieSupply(game.getDiesInSupply());
		gameButtonView = new GameButtonView(this);
		changeCurrentPlayerView = new ChangeCurrentPlayerView();

		setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

		setAlignment(roundtrackView, Pos.TOP_CENTER);

		showGame();
		
//		showChangeCurrentPlayerView();
	}
	
	public void showChangeCurrentPlayerView() {
		getChildren().clear();
		setCenter(changeCurrentPlayerView);
	}
	
	public void showGame() {
		getChildren().clear();
		setTop(roundtrackView);
		setLeft(objectiveInGameView);
		setCenter(patternCardView);
		VBox vBox = new VBox(dieSupply, gameButtonView);
		setBottom(vBox);
	}
	
	public void showToolCardView() {
		getChildren().clear();
		setCenter(new ToolCardInUseView(this));
	}

}
