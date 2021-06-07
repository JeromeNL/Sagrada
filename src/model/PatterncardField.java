package model;

import java.sql.SQLException;

import controller.DatabaseController;
import controller.PatterncardFieldsController;

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
	private PatterncardFieldsController pdbController;

	private Player owner;

	public PatterncardField(int xPosition, int yPosition, int eyesCountRequirement, GameColor colorRequirement,
			DatabaseController dbController, Player owner) {

		this.xPosition = xPosition;
		this.yPosition = yPosition;

		this.eyesCountRequirement = eyesCountRequirement;
		this.colorRequirement = colorRequirement;
		this.dbController = dbController;
		this.owner = owner;

		dieOnField = dbController.getDie(owner.getIdPlayer(), xPosition, yPosition);
		pdbController = new PatterncardFieldsController(dbController);

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
			return false;
		}

		// 2nd check: Is the color/value requirement correct?
		if (!isCorrectNumber(eyesCount) || !isCorrectColor(dieColor)) {
			return false;
		}

		// 3.5rd check: IS THIS THE FIRST TURN?
		if (isFirstTurn(owner)) {
			// 5th check: Is the die in the corner or on the edge?
			if (!firstDieIsOnEdge()) {
				return false;
			}
		}

		else {

			// 3rd check: Is the field adjacent to a die of the same color or value?
			if (!hasOtherValueAndColorSurrounding()) {

				return false;
			}

			// 4th check: Is the field adjacent to a field with a die already on it?
			if (!isAdjacentToDie()) {
				return false;
			}

		}

		return true;
	}

	// All checks

	private boolean isFirstTurn(Player owner) throws SQLException {

		if (pdbController.isFirstTurn(owner) == 0) {
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

			if (isAdjacentFieldSameValue(yPosition, leftX) == false
					&& isAdjacentFieldSameValue(yPosition, rightX) == false
					&& isAdjacentFieldSameValue(topY, xPosition) == false
					&& isAdjacentFieldSameValue(bottomY, xPosition) == false) {

				return true;
			} else {
				return false;
			}
		}

		else {
			return false;
		}

	}

	private boolean isAdjacentFieldSameColor(int y, int x) throws SQLException {
		if (x < 1 || x > 5 || y < 1 || y > 4) {
			return false;
		} else if (isFieldEmpty(y, x) == true) {
			return false;
		} else {
			String color = pdbController.isAdjacentFieldSameColor(owner, y, x);
			if (color.equals(selectedDieColor.toString().toLowerCase())) {
				return true;
			} else {
				return false;
			}
		}

	}

	private boolean isAdjacentFieldSameValue(int y, int x) throws SQLException {
		if (x < 1 || x > 5 || y < 1 || y > 4) {
			return false;
		} else if (isFieldEmpty(y, x) == true) {
			return false;
		} else {
			if (pdbController.isAdjacentFieldSameValue(owner, y, x) == selectedDieEyes) {
				return true;
			} else {
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
		} else if (isFieldEmpty((yPosition), 1) == false) {
			return true;
		} else if (isFieldEmpty((yPosition), 2) == false) {
			return true;
		} else if (isFieldEmpty((yPosition), 3) == false) {
			return true;
		} else if (isFieldEmpty((yPosition), 4) == false) {
			return true;
		} else if (isFieldEmpty((yPosition), 5) == false) {
			return true;
		} else if (isFieldEmpty(1, (xPosition)) == false) {
			return true;
		} else if (isFieldEmpty(2, (xPosition)) == false) {
			return true;
		} else if (isFieldEmpty(3, (xPosition)) == false) {
			return true;
		} else if (isFieldEmpty(4, (xPosition)) == false) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFieldEmpty(int y, int x) throws SQLException {

		if (x < 1 || x > 5 || y < 1 || y > 4) {
			return true;
		} else {

			if (pdbController.isFieldEmpty(owner, y, x) == 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	private boolean firstDieIsOnEdge() throws SQLException {
		if (isFirstTurn(owner)) {
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
