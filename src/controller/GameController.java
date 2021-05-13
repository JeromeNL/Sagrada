package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Game;

public class GameController {

	private DatabaseController dbController;
	private Game game;

	// Gets an available gameID and then adds a new row to the game table in the
	// database.
	public void addToDatabase() {
		// Get an available gameID
		ResultSet rs = dbController.doQuery("SELECT idgame FROM game ORDER BY idgame DESC LIMIT 1;");
		try {
			while (rs.next()) {
				int newGameID = rs.getInt(1) + 1;

				boolean increasingID = true;
				while (increasingID) {
					// Add a new row to the game table.
					String query = "INSERT INTO game VALUES (" + newGameID + ",NULL,NULL,CURRENT_TIMESTAMP);";
					int result = dbController.doUpdateQuery(query);
					if (result == 1) {
						increasingID = false;
						newGameID = game.getidGame();
						System.out.println(getClass() + " - New game created with id " + game.getidGame()); // for
																											// testing
																											// purposes
					} else {
						newGameID++;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong while adding a new game to the database.");
			e.printStackTrace();
		}
	}

}
