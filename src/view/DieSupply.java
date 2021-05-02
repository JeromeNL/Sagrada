package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Die;

public class DieSupply extends HBox {

	private ArrayList<DieToDragView> availableDies; // dies to place on patterncard

	public DieSupply(Color newColor) {
		
		availableDies = new ArrayList<DieToDragView>(); 
		
		// Adding dies to show.
		DieToDragView die1 = new DieToDragView(new Die(Color.MEDIUMPURPLE, 3));
		availableDies.add(die1);
		DieToDragView die2 = new DieToDragView(new Die(Color.LIGHTYELLOW, 2));
		availableDies.add(die2);
		DieToDragView die3 = new DieToDragView(new Die(Color.LIGHTBLUE, 6));
		availableDies.add(die3);
		DieToDragView die4 = new DieToDragView(new Die(Color.INDIANRED, 1));
		availableDies.add(die4);
		DieToDragView die5 = new DieToDragView(new Die(Color.LIGHTGREEN, 5));
		availableDies.add(die5);
		
		setAlignment(Pos.CENTER);
		setSpacing(25);

		getChildren().addAll(availableDies);
	}
}
