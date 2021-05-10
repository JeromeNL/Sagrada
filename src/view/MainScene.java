package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Die;
import model.DiesInSupply;
import model.Game;
import model.GameColor;
import model.Player;

public class MainScene extends Scene {

///////////////////////////////////////////////////////////////// Constructor

	public MainScene(Game testGame) {
		super(new Pane(), 1280, 720);
		
		// aanmaken onderstaande diesupply moet later in main controller gebeuren.
		DiesInSupply diesInSupply = new DiesInSupply();
		diesInSupply.addDie(new Die(GameColor.PURPLE, 3, 1));
		diesInSupply.addDie(new Die(GameColor.YELLOW, 2, 2));
		diesInSupply.addDie(new Die(GameColor.BLUE, 6, 3));
		diesInSupply.addDie(new Die(GameColor.RED, 1, 4));
		diesInSupply.addDie(new Die(GameColor.GREEN, 5, 5));
		
		Player testPlayer = testGame.getPlayers().get(1);

		GameView gameView = new GameView(diesInSupply, testGame, testPlayer); 
		setRoot(gameView);
		
//		LobbyView lobbyView = new LobbyView(); 
//		setRoot(lobbyView);
		
		
//		ToolcardView toolcardView = new ToolcardView();
//		setRoot(toolcardView);
		
	}
}
