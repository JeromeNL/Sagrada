package controller;

public class DieBagController {

	private DatabaseController dbController;

	public DieBagController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	public void addDieToDatabase(int idgame, int dienumber, String diecolor, int eyes, int roundtrack, int roundID) {
		String query = "INSERT INTO gamedie VALUES (" + idgame + ", " + dienumber + ", " + diecolor + ", " + eyes + ", "
				+ roundtrack + ", " + roundID + "\");";

		dbController.doUpdateQuery(query);
	}

}