package model;

import controller.DatabaseController;
import javafx.scene.paint.Color;

public class PatterncardField {

	private int xPosition; // range: 1 to 5
	private int yPosition; // range: 1 to 4

	private int eyesCountRequirement; // 0 = no requirement
	private GameColor colorRequirement; // null is no requirement

	private Die dieOnField;
	private DatabaseController dbController;

	public PatterncardField(int xPosition, int yPosition, DatabaseController dbController) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;

		this.dbController = dbController;
	}

	public PatterncardField(int xPosition, int yPosition, int eyesCountRequirement, GameColor colorRequirement, DatabaseController dbController) {


	}
	
	// The great 'isValidMove'-check. 
	public boolean isValidMove(int eyesCount, Color dieColor) {
		// 1st check: Is the field empty?
		if (fieldHasDie()) {
			System.out.println(" 1 false");
			return false;
		}

		// 2nd check: the color and value is correct
		if (!isCorrectNumber(eyesCount) || !isCorrectColor(dieColor)) {
			System.out.println(" 2 false");
			return false;
		}

		// 3rd check: The field is NOT adjacent to a stone of the same color or value
		if (!hasOtherValueOrColorSurrounding()) {
			System.out.println(" 3 false");
			return false;
		}
		// 3.5rd check: IS THIS THE FIRST TURN?
		if (isFirstTurn()) {
			// FOR THE FIRST DIE ONLY!
			// 5th check: The first die is on the edge or in the corner
			if (!firstDieIsOnEdge()) {
				System.out.println(" 5 false");
				return false;
			}
		}

		else {
			// FOR ALL DIES AFTER THE 1ST ONE ONLY!
			// 4th check: The field is adjacent to a field that already has a die on it
			if (!isAdjacentToDie()) {
				System.out.println(" 4 false");
				return false;
			}

		}
		System.out.println("This move is valid! | all statements == true");
		return true;
	}
	

	// All 
	private boolean isFirstTurn() {
		// Check of de steen die gelegd gaat worden de eerste steen op het bord is.
		return false;
	}

	private boolean isCorrectColor(Color dieColor) {
		// Check of de steen aan de kleurvoorwaarde voldoet.

		if (hasColorRequirement()) {
			GameColor colorRequirement =  getColorRequirement();
			if (colorRequirement.equals(dieColor)) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean isCorrectNumber(int eyesCount) {
		// Check of de steen aan de waardevoorwaarde voldoet.
		if (hasEyesCountRequirement()) {
			if (getEyesCountRequirement() == eyesCount) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean fieldHasDie() {
		// Check of het veld nog leeg is of niet
		if (hasDie()) {
			return true;
		}
		return false;
	}

	private boolean hasOtherValueOrColorSurrounding() {
		// Check of de omliggende stenen een andere kleur/waarde hebben.
		return true;
	}

	private boolean isAdjacentToDie() {
		// Check of het veld aan een al bestaande steen ligt.
		return true;
	}

	// Check of de eerste steen in de hoek/rand is gelegd.
	private boolean firstDieIsOnEdge() {
		if (isFirstTurn()) {
			if (getYPosition() == 1 || getYPosition() == 4 || getXPosition() == 1 || getXPosition() == 5) {
				return true;

			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	// end of checks //////////////////////////////////////////////////////////

	public PatterncardField(int xPosition, int yPosition, int eyesCountRequirement, Color colorRequirement) {

		this.xPosition = xPosition;
		this.yPosition = yPosition;

		this.eyesCountRequirement = eyesCountRequirement;
		this.colorRequirement = colorRequirement;
		this.dbController = dbController;
	}

	public void placeDie(Die die) {
		this.dieOnField = die;

//		addDieToDatabase(playerID, gameID, die);
	}

	// Adds a row to the playerframefield table so the placement of a die is saved
	// to the database.
	private void addDieToDatabase(int playerID, int gameID, Die die) {
		// playerframfield has columns : idplayer, position_x, position_y, idgame,
		// dienumber, diecolor
		String query = "INSERT INTO playerframefield VALUES (" + playerID + ", " + xPosition + ", " + yPosition + ", "
				+ gameID + ", " + die.getEyesCount() + ", \"" + die.getStringColor() + "\");";

		dbController.doUpdateQuery(query);
	}

	// Removes the die from the database.
	private void removeDieFromDatabase() {
		String query = "UPDATE playerframefield SET dienumber = NULL, diecolor = NULL WHERE position_x = " + xPosition
				+ " AND position_y = " + yPosition + ";";

		dbController.doUpdateQuery(query);
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

	public GameColor getColorRequirement() {
		return colorRequirement;
	}

	public int getEyesCountRequirement() {
		return eyesCountRequirement;
	}

}
