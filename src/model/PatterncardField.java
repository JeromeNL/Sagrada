package model;

import controller.DatabaseController;

public class PatterncardField {

	private int xPosition; // range: 1 to 5
	private int yPosition; // range: 1 to 4

	private int eyesCountRequirement; // 0 = no requirement
	private GameColor colorRequirement; // null is no requirement

	private Die dieOnField;
	private DatabaseController dbController;
	
	private Player owner;

//	public PatterncardField(int xPosition, int yPosition, DatabaseController dbController, Player owner) {
//		this.xPosition = xPosition;
//		this.yPosition = yPosition;
//		this.dbController = dbController;
//		this.owner = owner;
//		
//	}

	public PatterncardField(int xPosition, int yPosition, int eyesCountRequirement, GameColor colorRequirement, DatabaseController dbController, Player owner) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;

		this.eyesCountRequirement = eyesCountRequirement;
		this.colorRequirement = colorRequirement;
		this.dbController = dbController;
		this.owner = owner;
		
		dieOnField = dbController.getDie(owner.getIdPlayer(), xPosition, yPosition);
	}

	public void placeDie(Die die) {
		this.dieOnField = die;
		dbController.placeDie(owner.getIdPlayer(), owner.getGameID(), die, xPosition, yPosition);
	}
	
	// Removes the die from the database. 
	private void removeDieFromDatabase() {
		String query = "UPDATE playerframefield SET dienumber = NULL, diecolor = NULL WHERE position_x = " + xPosition + " AND position_y = " + yPosition + ";";

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
