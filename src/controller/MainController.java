package controller;

import java.util.ArrayList;

import javafx.stage.Stage;
import model.Game;
import model.Patterncard;
import model.Player;
import view.MainScene;

public class MainController {
	
	private DatabaseController dbController;

	public MainController(Stage stage) {
		
		dbController = new DatabaseController();
		
		// create a testgame		
		Game testGame = new Game("piet", dbController);
		testGame.getPlayers().get(0).setPatternCard(new Patterncard(10, new DatabaseController()));
		testGame.invitePlayer("jerome");
		testGame.getPlayers().get(1).setPatternCard(new Patterncard(10, new DatabaseController()));
		testGame.invitePlayer("kees");
		testGame.getPlayers().get(2).setPatternCard(new Patterncard(10, new DatabaseController()));

		MainScene mainScene = new MainScene(testGame);

		stage.setTitle("SOPRJ4 Sagrada - Groep R");
		stage.setResizable(false);
		stage.setScene(mainScene);
		stage.show();
	}
	
}
