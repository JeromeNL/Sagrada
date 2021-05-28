package controller;

public class FavorTokenController {

	private DatabaseController dbController;

	public FavorTokenController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	// Adds the favortoken to the gamefavortoken table.
	public void addToDatabase(int idToken, int idGame) {
		// to-do: insert row into gamefavortoken table with idfavortoken and idgame
		String query = "INSERT INTO gamefavortoken VALUES (" + idToken + "," + idGame + ",NULL,NULL,NULL);";
		dbController.doUpdateQuery(query);
	}

	// Updates the favortoken in the gamefavortoken table.
	public void updateDatabase() {
		// to-do: update row from gamefavortoken table so it contains the latest data
	}
}
