package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DatabaseController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Patterncard;
import model.PatterncardField;

public class PatternCardView extends GridPane {

///////////////////////////////////////////////////////////////// Instance Variables
	
	private final int height = 300;
	private final int width = 350;
	private final int gridGapSize = 5;
	
	private Patterncard patterncard;

///////////////////////////////////////////////////////////////// Constructor
	
	public PatternCardView(Patterncard patterncard) {
		this.patterncard = patterncard;

		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), new Insets(0))));
		setMaxSize(width, height);
		setAlignment(Pos.CENTER);

		setHgap(gridGapSize);
		setVgap(gridGapSize);
		
		loadPatterncard();
	}

///////////////////////////////////////////////////////////////// createEmptyCard

/* Method that is responsible that is responsible for the 
 * of an empty  pattern card*/
	
	private void createEmptyCard() {
		for (int x = 1; x <= 5; x++) {
			for (int y = 1; y <= 4; y++) {
				add(new DieView(new PatterncardField(1, 1, 2, null)), x, y);
			}
		}
	}

///////////////////////////////////////////////////////////////// loadPatternCard

/* Method that is responsible that is responsible for 
 * loading pattern card data from the database
 * and filling the board with the data*/
	
	
	private void loadPatterncard() {
		for (PatterncardField field : patterncard.getFields()) {
			add(new DieView(field), field.getXPosition(), field.getYPosition());
		}		
	}
}
