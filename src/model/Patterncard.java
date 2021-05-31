package model;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.PatterncardController;

public class Patterncard {

	private int idPatterncard;
	private ArrayList<PatterncardField> fields;
	private DatabaseController dbController;
	private Player owner;

	public Patterncard(int idPatterncard, DatabaseController dbController, Player owner) {

		this.dbController = dbController;
		this.idPatterncard = idPatterncard;
		this.owner = owner;
		fields = new ArrayList<PatterncardField>();

		loadFields(idPatterncard);
	}

	private void loadFields(int idPatterncard) {

		PatterncardController patterncardController = new PatterncardController(dbController);

		fields = patterncardController.loadFields(idPatterncard, owner);
	}

	public ArrayList<PatterncardField> getFields() {
		return fields;
	}

	public int getID() {
		return idPatterncard;
	}
}
