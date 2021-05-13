package model;

import controller.DatabaseController;
import controller.PatterncardFieldsController;

public class PatterncardField {

	private int xPosition; // range: 1 to 5
	private int yPosition; // range: 1 to 4

	private int eyesCountRequirement; // 0 = no requirement
	private GameColor colorRequirement; // null is no requirement

	private Die dieOnField;
	private DatabaseController dbController;

	PatterncardFieldsController patterncardFieldsController;

	public PatterncardField(int xPosition, int yPosition, DatabaseController dbController) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.dbController = dbController;
	}

	public PatterncardField(int xPosition, int yPosition, int eyesCountRequirement, GameColor colorRequirement,
			DatabaseController dbController) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;

		this.eyesCountRequirement = eyesCountRequirement;
		this.colorRequirement = colorRequirement;
		this.dbController = dbController;
	}

	public void placeDie(Die die) {
		this.dieOnField = die;

//		patterncardFieldsController.addDieToDatabase(playerID, gameID, die);
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
