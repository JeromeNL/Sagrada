package model;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.PatterncardController;

public class Patterncard {

	private int idPatterncard;
	private ArrayList<PatterncardField> fields;
	private PatterncardController patterncardController;
	private DatabaseController dbController;

	public Patterncard(int idPatterncard, DatabaseController dbController) {
		this.dbController = dbController;
		this.idPatterncard = idPatterncard;
		fields = new ArrayList<PatterncardField>();

		patterncardController.loadFields();
	}

	// Convert string color from database to gameColor.
	public GameColor stringToGameColor(String colorString) {
		GameColor gameColor = null;
		if (colorString.equals("red")) {
			gameColor = GameColor.RED;
		} else if (colorString.equals("blue")) {
			gameColor = GameColor.BLUE;
		} else if (colorString.equals("yellow")) {
			gameColor = GameColor.YELLOW;
		} else if (colorString.equals("purple")) {
			gameColor = GameColor.PURPLE;
		} else if (colorString.equals("green")) {
			gameColor = GameColor.GREEN;
		}
		return gameColor;
	}

	public int getIdPatterncard() {
		return idPatterncard;
	}

	public ArrayList<PatterncardField> getFields() {
		return fields;
	}
}
