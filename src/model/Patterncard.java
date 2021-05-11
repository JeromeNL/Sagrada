package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DatabaseController;

public class Patterncard {
	
	private int idPatterncard;
	private ArrayList<PatterncardField> fields;

	private DatabaseController dbController;
	
	public Patterncard(int idPatterncard, DatabaseController dbController) {
		this.dbController = dbController;
		this.idPatterncard = idPatterncard;
		fields = new ArrayList<PatterncardField>();
		
		loadFields();
	}
	
	private void loadFields() {
		ResultSet rs = dbController
				.doQuery("SELECT * FROM patterncardfield WHERE idpatterncard = " + idPatterncard);
		
		try {
			while (rs.next()) {
				int xPosition = rs.getInt("position_x");
				int yPosition = rs.getInt("position_y");

				String color = rs.getString("color");

				GameColor colorRequirement = null;
				if (color == null) {
					colorRequirement = null;
				} else {
					colorRequirement = stringToGameColor(color);
				}

				int valueRequirement = rs.getInt("value");
				
				fields.add(new PatterncardField(xPosition, yPosition, valueRequirement, colorRequirement, dbController));
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong while loading a patterncard from the database.");
			e.printStackTrace();
		}
	}
	
	// Convert string color from database to gameColor.
	private GameColor stringToGameColor(String colorString) {
		GameColor gameColor = null;		
		if (colorString.equals("red")) {
			gameColor = GameColor.RED;
		} else if (colorString.equals("blue")) {
			gameColor = GameColor.BLUE;
		} else if (colorString.equals("yellow")) {
			gameColor = GameColor.YELLOW;
		} else if (colorString.equals("purple")) {
			gameColor = GameColor.PURPLE;
		} else if (colorString.equals("green")) {
			gameColor = GameColor.GREEN;
		}
		return gameColor;
	}
	
	public ArrayList<PatterncardField> getFields() {
		return fields;
	}
}
