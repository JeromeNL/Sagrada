package controller;

import model.FavorToken;

public class FavorTokenController {

	private DatabaseController dbController;
	private FavorToken favortoken;

	// Adds the favortoken to the gamefavortoken table.
	public void addToDatabase() {
		// to-do: insert row into gamefavortoken table with idfavortoken and idgame
		String query = "INSERT INTO gamefavortoken VALUES (" + favortoken.getIdToken() + "," + favortoken.getIdGame()
				+ ",NULL,NULL,NULL);";
		dbController.doUpdateQuery(query);
	}

	// Updates the favortoken in the gamefavortoken table.
	public void updateDatabase() {
		// to-do: update row from gamefavortoken table so it contains the latest data
	}
}
