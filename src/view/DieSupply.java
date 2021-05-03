package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import model.Die;
import model.DiesInSupply;

public class DieSupply extends HBox {

	private ArrayList<DieToDragView> draggableDies; // dies to place on patterncard
	private DiesInSupply diesInSupply;
	
	public DieSupply(DiesInSupply diesInSupply) {
		this.diesInSupply = diesInSupply;
		
		draggableDies = new ArrayList<DieToDragView>();
		updateView();
		
		setAlignment(Pos.CENTER);
		setSpacing(25);
	}
	
	public void updateView() {
		getChildren().clear();
		draggableDies.clear();
		for (Die die : diesInSupply.getDies()) {
			draggableDies.add(new DieToDragView(die, this));
		}
		getChildren().addAll(draggableDies);
	}
	
	public void removeDie(Die die) {
		diesInSupply.removeDie(die);
		updateView();
	}
}
