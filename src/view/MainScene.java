package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainScene extends Scene {

///////////////////////////////////////////////////////////////// Constructor

	public MainScene() {
		super(new Pane(), 1280, 720);

		GameView gameView = new GameView(); 
		setRoot(gameView);
		
		
		LobbyView lobbyView = new LobbyView(); 
		setRoot(lobbyView);
		
	}
}
