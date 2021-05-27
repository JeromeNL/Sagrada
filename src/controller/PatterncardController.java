package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.GameColor;
import model.PatterncardField;
import model.Player;

public class PatterncardController {

	private DatabaseController dbController;

	public PatterncardController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	public ArrayList<PatterncardField> loadFields(int idPatterncard, Player owner) {

		ArrayList<PatterncardField> fields = new ArrayList<PatterncardField>();

		ResultSet rs = dbController.doQuery("SELECT * FROM patterncardfield WHERE idpatterncard = " + idPatterncard);

		try {
			while (rs.next()) {
				int xPosition = rs.getInt("position_x");
				int yPosition = rs.getInt("position_y");

				String color = rs.getString("color");

				GameColor colorRequirement = null;
				if (color == null) {
					colorRequirement = null;
				} else {
					colorRequirement = GameColor.valueOf(color.toUpperCase());
				}

				int valueRequirement = rs.getInt("value");

				fields.add(
						new PatterncardField(xPosition, yPosition, valueRequirement, colorRequirement, dbController, owner));
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong while loading a patterncard from the database.");
			e.printStackTrace();
		}
		return fields;
	}

}
