package view;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.PatterncardField;

public class DieSupply extends HBox {

	private ArrayList<DieToDragView> availableDies; // dies to place on patterncard

	public DieSupply(Color newColor) {
		
		availableDies = new ArrayList<DieToDragView>(); 
		
		// Adding dies to show.
		DieToDragView die1 = new DieToDragView(new PatterncardField(3, Color.MEDIUMPURPLE));
		availableDies.add(die1);
		DieToDragView die2 = new DieToDragView(new PatterncardField(2, Color.LIGHTYELLOW));
		availableDies.add(die2);
		DieToDragView die3 = new DieToDragView(new PatterncardField(6, Color.LIGHTBLUE));
		availableDies.add(die3);
		DieToDragView die4 = new DieToDragView(new PatterncardField(1, Color.INDIANRED));
		availableDies.add(die4);
		DieToDragView die5 = new DieToDragView(new PatterncardField(5, Color.LIGHTGREEN));
		availableDies.add(die5);
		
		setAlignment(Pos.CENTER);
		setSpacing(25);

		getChildren().addAll(availableDies);
	}
}
