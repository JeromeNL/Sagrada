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
import model.DieFieldModel;

public class PatternCardView extends GridPane {

///////////////////////////////////////////////////////////////// Instance Variables
	
	private final int height = 300;
	private final int width = 350;
	private final int gridGapSize = 5;

///////////////////////////////////////////////////////////////// Constructor
	
	public PatternCardView() {

		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), new Insets(0))));
		setMaxSize(width, height);
		setAlignment(Pos.CENTER);

		setHgap(gridGapSize);
		setVgap(gridGapSize);

		loadPatternCard(2);
	}

///////////////////////////////////////////////////////////////// createEmptyCard

/* Method that is responsible that is responsible for the 
 * of an empty  pattern card*/
	
	private void createEmptyCard() {
		for (int x = 1; x <= 5; x++) {
			for (int y = 1; y <= 4; y++) {
				add(new DieView(new DieFieldModel(1, 1, 2, null)), x, y);
			}
		}
	}

///////////////////////////////////////////////////////////////// loadPatternCard

/* Method that is responsible that is responsible for 
 * loading pattern card data from the database
 * and filling the board with the data*/
	
	
	private void loadPatternCard(int idPatterncard) {
		DatabaseController databaseController = new DatabaseController();
		ResultSet rs = databaseController
				.doQuery("SELECT * FROM patterncardfield WHERE idpatterncard = " + idPatterncard);
		try {
			while (rs.next()) {
				int xPosition = rs.getInt("position_x");
				int yPosition = rs.getInt("position_y");
//				Color colorRequirement = Color.valueOf(rs.getString("color"));
				String color = rs.getString("color");
				Color colorRequirement = null;
				if (color == null) {
					colorRequirement = Color.WHITE;
				} else {
					if (color.equals("red")) {
						colorRequirement = Color.INDIANRED;
					}
					if (color.equals("blue")) {
						colorRequirement = Color.LIGHTBLUE;
					}
					if (color.equals("yellow")) {
						colorRequirement = Color.LIGHTYELLOW;
					}
					if (color.equals("purple")) {
						colorRequirement = Color.MEDIUMPURPLE;
					}
					if (color.equals("GREEN")) {
						colorRequirement = Color.LIGHTGREEN;
					}
				}

				int valueRequirement = rs.getInt("value");

				add(new DieView(new DieFieldModel(xPosition, yPosition, valueRequirement, colorRequirement)), xPosition,
						yPosition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
