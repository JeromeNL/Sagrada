package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import controller.DatabaseController;


public class PatterncardField {

	private int xPosition; // range: 1 to 5
	private int yPosition; // range: 1 to 4

	private GameColor selectedDieColor;
	private int selectedDieEyes;

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

		// 2nd check: Is the color/value requirement correct?
		if (!isCorrectNumber(eyesCount) || !isCorrectColor(dieColor)) {
			System.out.println(" 2 false");
			return false;
		}

		// 3.5rd check: IS THIS THE FIRST TURN?
		if (isFirstTurn()) {
			// 5th check: Is the die in the corner or on the edge?
			if (!firstDieIsOnEdge()) {
				System.out.println(" 5 false");
				return false;
			}
		}

		else {

			// 3rd check: Is the field adjacent to a die of the same color or value?
			if (!hasOtherValueAndColorSurrounding()) {
				System.out.println(" 3 false");

				return false;
			}

			// 4th check: Is the field adjacent to a field with a die already on it?
			if (!isAdjacentToDie()) {
				System.out.println(" 4 false");
				return false;
			}

		}
		System.out.println("=========================");
		System.out.println("Placed die | eyes: " + selectedDieEyes + " die color: " + selectedDieColor);
		System.out.println("This move is valid! | all statements == true");
		System.out.println("=========================");
		return true;
	}

	// All checks

	// Is this die the first die?
	private boolean isFirstTurn() throws SQLException {
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
		if (hasDie()) {
			return true;
		}
		return false;
	}

	// Has field surrounding dies of the same color and/or value?
	private boolean hasOtherValueAndColorSurrounding() throws SQLException {
		if (isAdjacentFieldSameColor(yPosition, leftX) == false && isAdjacentFieldSameColor(yPosition, rightX) == false
				&& isAdjacentFieldSameColor(topY, xPosition) == false
				&& isAdjacentFieldSameColor(bottomY, xPosition) == false) {

			System.out.println("!!! | Not adjacent to same color");

			if (isAdjacentFieldSameValue(yPosition, leftX) == false
					&& isAdjacentFieldSameValue(yPosition, rightX) == false
					&& isAdjacentFieldSameValue(topY, xPosition) == false
					&& isAdjacentFieldSameValue(bottomY, xPosition) == false) {

				System.out.println("!!! | Not adjacent to same value");
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
		System.out.println("====== START OF ADJACENT OF FIELD SAME COLOR =====");
		if (x < 1 || x > 5 || y < 1 || y > 4) {
			System.out.println("Die isn't on board");
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
		System.out.println("start isSameValue Method");
		if (x < 1 || x > 5 || y < 1 || y > 4) {
			System.out.println(x + " " + y);
			return false;
		} else if (isFieldEmpty(y, x) == true) {
			return false;
		} else {
			ResultSet valueOfAdjacentDie = dbController.doQuery(
					"SELECT gamedie.eyes FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idplayer = '"
							+ owner.getIdPlayer() + "' AND playerframefield.idgame = '" + owner.getGameID()
							+ "' AND playerframefield.position_x = '" + x + "' AND playerframefield.position_y = '" + y
							+ "'");

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
		} else if(isFieldEmpty((yPosition), 1) == false) {
			return true;
		} else if(isFieldEmpty((yPosition), 2) == false){
			return true;
		} else if(isFieldEmpty((yPosition), 3) == false){
			return true;
		} else if(isFieldEmpty((yPosition), 4) == false){
			return true;
		} else if(isFieldEmpty((yPosition), 5) == false){
			return true;
		} else if(isFieldEmpty(1, (xPosition)) == false){
			return true;
		} else if(isFieldEmpty(2, (xPosition)) == false){
			return true;
		} else if(isFieldEmpty(3, (xPosition)) == false){
			return true;
		} else if(isFieldEmpty(4, (xPosition)) == false){
			return true;
		} 
		else {
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
