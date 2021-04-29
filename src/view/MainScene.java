package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainScene extends Scene {

///////////////////////////////////////////////////////////////// Constructor

	public MainScene() {
		super(new Pane(), 1280, 720);
		EndScoreView endScoreView= new EndScoreView();
		GameView gameView = new GameView(); 
	
		setRoot(endScoreView);
		
//		setRoot(gameView);
		
		
//		LobbyView lobbyView = new LobbyView(); 
//		setRoot(lobbyView);
		
	}
}
