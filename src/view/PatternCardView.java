package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Patterncard;
import model.PatterncardField;

public class PatternCardView extends GridPane {

	private final int height = 300;
	private final int width = 350;
	private final int gridGapSize = 5;
	
	private Patterncard patterncard;

	public PatternCardView(Patterncard patterncard) {
		this.patterncard = patterncard;

		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), new Insets(0))));
		setMaxSize(width, height);
		setAlignment(Pos.CENTER);

		setHgap(gridGapSize);
		setVgap(gridGapSize);
		
		if (patterncard != null) {
			loadPatterncard();			
		} else {
			add(new Label("This player has not yet chosen a patterncard."), 1, 1);
		}
	}


/* Method that is responsible that is responsible for 
 * loading pattern card data from the database
 * and filling the board with the data*/
	
	private void loadPatterncard() {
		for (PatterncardField field : patterncard.getFields()) {
			add(new DieView(field), field.getXPosition(), field.getYPosition());
		}		
	}
}
