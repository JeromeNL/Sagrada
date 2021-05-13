package controller;

public class DieBagController {

	private DatabaseController databaseController;

	public void insertDie() {

		databaseController = new DatabaseController();
		databaseController.doUpdateQuery("SELECT * FROM public_objectivecard where points = 5");

	}

	public void addDieToDatabase(int idgame, int dienumber, String diecolor, int eyes, int roundtrack, int roundID) {
		String query = "INSERT INTO gamedie VALUES (" + idgame + ", " + dienumber + ", " + diecolor + ", " + eyes + ", "
				+ roundtrack + ", " + roundID + "\");";

		databaseController.doUpdateQuery(query);
	}

}
