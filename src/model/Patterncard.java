package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DatabaseController;
import javafx.scene.paint.Color;

public class Patterncard {
	
	private int idPatterncard;
	private ArrayList<PatterncardField> fields;
	private Player owner;

	private DatabaseController dbController;
	
	public Patterncard(int idPatterncard, DatabaseController dbController, Player owner) {
		this.dbController = dbController;
		this.idPatterncard = idPatterncard;
		this.owner = owner;
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
					Color colorFX = Color.valueOf(color);
					colorRequirement = GameColor.toGameColor(colorFX);
				}

				int valueRequirement = rs.getInt("value");
				
				fields.add(new PatterncardField(xPosition, yPosition, valueRequirement, colorRequirement, dbController, owner));
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong while loading a patterncard from the database.");
			e.printStackTrace();
		}
	}
	
	public ArrayList<PatterncardField> getFields() {
		return fields;
	}
}
