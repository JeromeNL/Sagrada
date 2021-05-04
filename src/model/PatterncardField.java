package model;

import controller.DatabaseController;
import javafx.scene.paint.Color;

public class PatterncardField {

	private int xPosition; // range: 1 to 5
	private int yPosition; // range: 1 to 4

	private int eyesCountRequirement; // 0 = no requirement
	private Color colorRequirement; // null is no requirement

	private Die dieOnField;

	public PatterncardField(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public PatterncardField(int xPosition, int yPosition, int eyesCountRequirement, Color colorRequirement) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;

		this.eyesCountRequirement = eyesCountRequirement;
		this.colorRequirement = colorRequirement;
	}

	public void placeDie(Die die) {
		this.dieOnField = die;

//		addDieToDatabase(playerID, gameID, die);
	}

	// Adds a row to the playerframefield table so the placement of a die is saved to the database.
	private void addDieToDatabase(int playerID, int gameID, Die die) {
		// playerframfield has columns : idplayer, position_x, position_y, idgame, dienumber, diecolor
		String query = "INSERT INTO playerframefield VALUES (" + playerID + ", " + xPosition + ", " + yPosition + ", "
				+ gameID + ", " + die.getEyesCount() + ", \"" + die.getStringColor() + "\");";

		DatabaseController dc = new DatabaseController();
		dc.doUpdateQuery(query);
	}
	
	// Removes the die from the database. 
	private void removeDieFromDatabase() {
		String query = "UPDATE playerframefield SET dienumber = NULL, diecolor = NULL WHERE position_x = " + xPosition + " AND position_y = " + yPosition + ";";

		DatabaseController dc = new DatabaseController();
		dc.doUpdateQuery(query);
	}

	public void removeDie() {
		dieOnField = null;
//		removeDieFromDatabase();
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public Die getDie() {
		return dieOnField;
	}

	public boolean hasDie() {
		return dieOnField != null;
	}

	public boolean hasColorRequirement() {
		return colorRequirement != null;
	}

	public boolean hasEyesCountRequirement() {
		return eyesCountRequirement != 0;
	}

	public Color getColorRequirement() {
		return colorRequirement;
	}

	public int getEyesCountRequirement() {
		return eyesCountRequirement;
	}

}
