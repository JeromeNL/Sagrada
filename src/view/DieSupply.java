package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import model.Die;
import model.DiesInSupply;
import model.Player;

public class DieSupply extends HBox {

	private ArrayList<DieToDragView> draggableDies; // dies to place on patterncard
	private DiesInSupply diesInSupply;
	private Player player;
	
	public DieSupply(DiesInSupply diesInSupply, Player player) {
		this.diesInSupply = diesInSupply;
		this.player = player;
		
		draggableDies = new ArrayList<DieToDragView>();
		updateView();
		
		setAlignment(Pos.CENTER);
		setSpacing(25);

	}
	
	public void updateView() {
		getChildren().clear();
		draggableDies.clear();
		for (Die die : diesInSupply.getDies()) {
			DieToDragView dieToDragView = new DieToDragView(die, this);
			if (player.getDiePlacedInRound()) {
				dieToDragView.setOpacity(0.5);
				dieToDragView.setDisable(true);
			}
			draggableDies.add(dieToDragView);

		}
		getChildren().addAll(draggableDies);
	}
	
	public void removeDie(Die die) {
		diesInSupply.removeDie(die);
		updateView();
	}
}
