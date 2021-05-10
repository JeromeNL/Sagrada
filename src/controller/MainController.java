package controller;

import javafx.stage.Stage;
import model.Game;
import view.MainScene;

public class MainController {
	
	private DatabaseController dbController;

	public MainController(Stage stage) {
		
		dbController = new DatabaseController();
		
		// create a testgame		
//		Game testGame = new Game("jerome", dbController);
//		testGame.invitePlayer("kees");
//		testGame.invitePlayer("jerome");
//		testGame.invitePlayer("joram");
		
		
		MainScene mainScene = new MainScene(null);

		stage.setTitle("SOPRJ4 Sagrada - Groep R");
		stage.setResizable(false);
		stage.setScene(mainScene);
		stage.show();
	}
	
}
