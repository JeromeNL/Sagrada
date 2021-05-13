package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.GameColor;
import model.Patterncard;
import model.PatterncardField;

public class PatterncardController {
	
		private DatabaseController dbController;
		private ArrayList<PatterncardField> fields;
		private Patterncard patterncard;
	
		public void loadFields() {
		ResultSet rs = dbController
				.doQuery("SELECT * FROM patterncardfield WHERE idpatterncard = " + patterncard.getIdPatterncard());
		
		try {
			while (rs.next()) {
				int xPosition = rs.getInt("position_x");
				int yPosition = rs.getInt("position_y");

				String color = rs.getString("color");

				GameColor colorRequirement = null;
				if (color == null) {
					colorRequirement = null;
				} else {
					colorRequirement = patterncard.stringToGameColor(color);
				}

				int valueRequirement = rs.getInt("value");
				
				fields.add(new PatterncardField(xPosition, yPosition, valueRequirement, colorRequirement, dbController));
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong while loading a patterncard from the database.");
			e.printStackTrace();
		}
	}

}
