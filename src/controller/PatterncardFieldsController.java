package controller;

import model.Die;

public class PatterncardFieldsController {

	private DatabaseController dbController;
	
	private PatterncardFieldsController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	// Adds a row to the playerframefield table so the placement of a die is saved
	// to the database.
	public void addDieToDatabase(int playerID, int xPosition, int yPosition, int gameID, Die die) {
		// playerframfield has columns : idplayer, position_x, position_y, idgame,
		// dienumber, diecolor
		String query = "INSERT INTO playerframefield VALUES (" + playerID + ", " + xPosition + ", " + yPosition + ", "
				+ gameID + ", " + die.getEyesCount() + ", \"" + die.getColor() + "\");";

		dbController.doUpdateQuery(query);
	}

	// Removes the die from the database.
	public void removeDieFromDatabase(int xPosition, int yPosition) {
		String query = "UPDATE playerframefield SET dienumber = NULL, diecolor = NULL WHERE position_x = " + xPosition
				+ " AND position_y = " + yPosition + ";";

		dbController.doUpdateQuery(query);
	}

}
