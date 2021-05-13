package controller;

import model.Die;
import model.PatterncardField;

public class PatterncardFieldsController {

	private PatterncardField patterncardField;
	private DatabaseController dbController;

	// Adds a row to the playerframefield table so the placement of a die is saved
	// to the database.
	public void addDieToDatabase(int playerID, int gameID, Die die) {
		// playerframfield has columns : idplayer, position_x, position_y, idgame,
		// dienumber, diecolor
		String query = "INSERT INTO playerframefield VALUES (" + playerID + ", " + patterncardField.getXPosition()
				+ ", " + patterncardField.getYPosition() + ", " + gameID + ", " + die.getEyesCount() + ", \""
				+ die.getStringColor() + "\");";

		dbController.doUpdateQuery(query);
	}

	// Removes the die from the database.
	public void removeDieFromDatabase() {
		String query = "UPDATE playerframefield SET dienumber = NULL, diecolor = NULL WHERE position_x = "
				+ patterncardField.getXPosition() + " AND position_y = " + patterncardField.getYPosition() + ";";

		dbController.doUpdateQuery(query);
	}

}
