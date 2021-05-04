package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainScene;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		testdblogic t = new testdblogic();
		t.oefenen();
//		t.randomizeColor();
//		t.randomizeNumber();
		t.fillingTheBag();
		MainScene mainScene = new MainScene();
		
		stage.setTitle("SOPRJ4 Sagrada - Groep R");
		stage.setResizable(false);
		stage.setScene(mainScene);
		stage.show();
		
		
	}
}
