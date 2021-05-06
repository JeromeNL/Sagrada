package view;

import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Die;
import model.DiesInSupply;

public class MainScene extends Scene {

	public MainScene() {
		super(new Pane(), 1280, 720);
		
		// aanmaken onderstaande diesupply moet later in main controller gebeuren.
		DiesInSupply diesInSupply = new DiesInSupply();
		diesInSupply.addDie(new Die(Color.MEDIUMPURPLE, 3, 1));
		diesInSupply.addDie(new Die(Color.LIGHTYELLOW, 2, 2));
		diesInSupply.addDie(new Die(Color.LIGHTBLUE, 6, 3));
		diesInSupply.addDie(new Die(Color.INDIANRED, 1, 4));
		diesInSupply.addDie(new Die(Color.LIGHTGREEN, 5, 5));

    GameView gameView = new GameView(diesInSupply); 
		setRoot(gameView);
		
//		LobbyView lobbyView = new LobbyView(); 
//		setRoot(lobbyView);
    
//		LoginView loginView = new LoginView();
//		setRoot(loginView);
		
//		RegisterView registerView = new RegisterView();
//		setRoot(registerView);
    
//		ToolcardView toolcardView = new ToolcardView();
//		setRoot(toolcardView);
	}
}
