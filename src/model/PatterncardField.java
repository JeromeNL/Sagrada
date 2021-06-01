package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DatabaseController;
import javafx.scene.paint.Color;

public class PatterncardField {

	private int xPosition; // range: 1 to 5
	private int yPosition; // range: 1 to 4

	private GameColor selectedDieColor;
	private int selectedDieEyes;
//	private int gameID = 487;
//	private int playerID = 962; // 961

	private int leftX;
	private int rightX;
	private int topY;
	private int bottomY;

	private int eyesCountRequirement; // 0 = no requirement
	private GameColor colorRequirement; // null is no requirement

	private Die dieOnField;
	private DatabaseController dbController;

	private Player owner;

	public PatterncardField(int xPosition, int yPosition, int eyesCountRequirement, GameColor colorRequirement,
			DatabaseController dbController, Player owner) {

		this.xPosition = xPosition;
		this.yPosition = yPosition;

		this.eyesCountRequirement = eyesCountRequirement;
		this.colorRequirement = colorRequirement;
		this.dbController = dbController;
		this.owner = owner;

		leftX = xPosition - 1;
		rightX = xPosition + 1;
		topY = yPosition - 1;
		bottomY = yPosition + 1;

	}

	// The great 'isValidMove'-check.
	public boolean isValidMove(int eyesCount, GameColor dieColor) throws SQLException {
		this.selectedDieEyes = eyesCount;
		this.selectedDieColor = dieColor;

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

		// 3.5rd check: IS THIS THE FIRST TURN?
		// DOESNT WORK ATM: CANT READ DIES FROM THE DB, CUS IS ALL EMPTY
		if (isFirstTurn()) {
			// FOR THE FIRST DIE ONLY!
			// 5th check: The first die is on the edge or in the corner
			if (!firstDieIsOnEdge()) {
				System.out.println(" 5 false");
				return false;
			}
		}

		else {

			// 3rd check: The field is NOT adjacent to a stone of the same color or value
			if (!hasOtherValueAndColorSurrounding()) {
				System.out.println(" 3 false");

				return false;
			}
			// FOR ALL DIES AFTER THE 1ST ONE ONLY!
			// 4th check: The field is adjacent to a field that already has a die on it
			System.out.println("--------");
			System.out.println("CHECK 4");
			if (!isAdjacentToDie()) {
				System.out.println(" 4 false");
				return false;
			}

		}
		System.out.println("This move is valid! | all statements == true");
		System.out.println("Placed die | eyes: " + selectedDieEyes + " die color: " + selectedDieColor);
		return true;
	}

	// All
	private boolean isFirstTurn() throws SQLException {
		// Check of de steen die gelegd gaat worden de eerste steen op het bord is.

		ResultSet amountOfDies = dbController.doQuery("SELECT count(diecolor) FROM playerframefield WHERE idgame ='"
				+ owner.getGameID() + "' AND idplayer ='" + owner.getIdPlayer() + "'");
		int value = 0;
		while (amountOfDies.next()) {
			value = ((Number) amountOfDies.getObject(1)).intValue();
			System.out.println("isFirst Turn: " + value);
		}

		if (value == 0) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isCorrectColor(GameColor dieColor) {
		// Check of de steen aan de kleurvoorwaarde voldoet.

		if (hasColorRequirement()) {
			GameColor colorRequirement = getColorRequirement();
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

		// dieOnField = dbController.getDie(owner.getIdPlayer(), xPosition, yPosition);

	}

	private boolean hasOtherValueAndColorSurrounding() throws SQLException {
		// Check of de omliggende stenen een andere kleur/waarde hebben.

		if (isAdjacentFieldSameColor(yPosition, leftX) == false && isAdjacentFieldSameColor(yPosition, rightX) == false
				&& isAdjacentFieldSameColor(topY, xPosition) == false
				&& isAdjacentFieldSameColor(bottomY, xPosition) == false) {

			System.out.println("Not adjacent to same color");

			if (isAdjacentFieldSameValue(yPosition, leftX) == false
					&& isAdjacentFieldSameValue(yPosition, rightX) == false
					&& isAdjacentFieldSameValue(topY, xPosition) == false
					&& isAdjacentFieldSameValue(bottomY, xPosition) == false) {

				System.out.println("Not adjacent to same value");
				return true;
			} else {
				System.out.println("INVALID! Adjacent to same value");
				return false;
			}
		}

		else {
			System.out.println("INVALID! Adjacent to same color");
			return false;
		}

	}

	private boolean isAdjacentFieldSameColor(int y, int x) throws SQLException {
		// check: same color surrounding
		System.out.println("====== START OF ADJACENT OF FIELD SAME COLOR =====");
		// boolean isSameColor = false;

		if (x < 1 || x > 5 || y < 1 || y > 4) {
			System.out.println("Is out of board");
			return false;
		} else if (isFieldEmpty(y, x) == true) {
			System.out.println("Field is empty");
			return false;
		} else {
			ResultSet colorOfAdjacentDie = dbController.doQuery("SELECT diecolor FROM playerframefield "
					+ "WHERE idgame = '" + owner.getGameID() + "' AND idplayer = '" + owner.getIdPlayer()
					+ "' AND position_x = '" + x + "' AND position_y = '" + y + "'");

			String color = "";

			while (colorOfAdjacentDie.next()) {
				color = colorOfAdjacentDie.getObject(1).toString().toLowerCase();
				System.out.println("color of adjacent die: " + color);
				System.out.println(x + " " + y);
			}
			System.out.println(color);
			System.out.println(selectedDieColor.toString().toLowerCase());
			if (color.equals(selectedDieColor.toString().toLowerCase())) {
				System.out.println("die color is the same!");
				System.out.println("====== END OF ADJACENT OF FIELD SAME COLOR =====");
				return true;
			} else {
				System.out.println("die color is not the same!");
				System.out.println("====== END OF ADJACENT OF FIELD SAME COLOR =====");
				return false;
			}

		}

	}

	private boolean isAdjacentFieldSameValue(int y, int x) throws SQLException {
		// check: same value surrounding
	
		System.out.println("start isSameValue Method");
		if (x < 1 || x > 5 || y < 1 || y > 4) {
			System.out.println(x + " " + y);
			return false;
		} else if (isFieldEmpty(y, x) == true) {
			return false;
		} else {
			ResultSet valueOfAdjacentDie = dbController.doQuery("SELECT gamedie.eyes FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idplayer = '" + owner.getIdPlayer() + "' AND playerframefield.idgame = '" + owner.getGameID() + "' AND playerframefield.position_x = '" + x + "' AND playerframefield.position_y = '" + y + "'");
			
			
			int value = 0;

			while (valueOfAdjacentDie.next()) {
				value = valueOfAdjacentDie.getInt(1);
				System.out.println("value of adjacent die: " + value);
				System.out.println(x + " " + y);
			}
			System.out.println(value);
			
			if (value == selectedDieEyes) {
				System.out.println("die value is the same!");
				System.out.println("====== END OF ADJACENT OF FIELD SAME VALUE =====");
				return true;
			} else {
				System.out.println("die color is not the same!");
				System.out.println("====== END OF ADJACENT OF FIELD SAME VALUE =====");
				return false;
			}
		}

	
	}

	private boolean isAdjacentToDie() throws SQLException {
		// boolean isAdjacent;
		// horizontal/vertical checks
		if (isFieldEmpty(yPosition, leftX) == false) {
			return true;
		} else if (isFieldEmpty(yPosition, rightX) == false) {
			return true;
		} else if (isFieldEmpty(topY, xPosition) == false) {
			return true;
		} else if (isFieldEmpty(bottomY, xPosition) == false) {
			return true;
		}
		// diagonal checks
		else if (isFieldEmpty((yPosition + 1), (xPosition - 1)) == false) {
			return true;
		} else if (isFieldEmpty((yPosition + 1), xPosition + 1) == false) {
			return true;
		} else if (isFieldEmpty((yPosition - 1), xPosition + 1) == false) {
			return true;
		} else if (isFieldEmpty((yPosition - 1), xPosition - 1) == false) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFieldEmpty(int y, int x) throws SQLException {

		boolean isEmpty = false;
		System.out.println("start isFieldEmpty Method");
		if (x < 1 || x > 5 || y < 1 || y > 4) {
			System.out.println("x: " + x + " y: " + y);
			return true;
		} else {

			ResultSet zeroOrOneDie = dbController
					.doQuery("select COUNT(dienumber) from playerframefield where  idgame = '" + owner.getGameID()
							+ "' AND idplayer = '" + owner.getIdPlayer() + "' AND position_y = '" + y
							+ "' AND position_x = '" + x + "'");

			int value = 0;

			while (zeroOrOneDie.next()) {
				value = ((Number) zeroOrOneDie.getObject(1)).intValue();
				System.out.println("value of fieldempty (0 = empty, 1 = filled) :" + value);
				System.out.println(x + " " + y);
			}
			System.out.println(value);
			if (value == 0) {
				isEmpty = true;
				System.out.println(isEmpty);
				return isEmpty;
			} else {
				isEmpty = false;
				System.out.println(isEmpty);
				return isEmpty;
			}
		}
	}

	// Check of de eerste steen in de hoek/rand is gelegd.
	private boolean firstDieIsOnEdge() throws SQLException {
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

	public void placeDie(Die die) {
		this.dieOnField = die;
		dbController.placeDie(owner.getIdPlayer(), owner.getGameID(), die, xPosition, yPosition);
		owner.setDiePlacedInRound(true);

	}

	public void removeDie() {
		dieOnField = null;
//		patterncardFieldsController.removeDieFromDatabase();
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
