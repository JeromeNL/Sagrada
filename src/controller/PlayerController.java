package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Player;

public class PlayerController {

	private DatabaseController dbController;
	private Player player;

	// Create all the playerframefield rows in the database.
	public void createPlayerFrameField() {
		for (int position_y = 1; position_y <= 4; position_y++) {
			for (int position_x = 1; position_x <= 5; position_x++) {
				String query = "INSERT INTO playerframefield VALUES (" + player.getIdPlayer() + "," + position_x + ","
						+ position_y + "," + player.getIdGame() + ",NULL,NULL);";
				dbController.doUpdateQuery(query);
			}
		}
	}

	// Adds a new user to the database.
	public void addToDatabase() {
		// Get an available gameID
		ResultSet rs = dbController.doQuery("SELECT idplayer FROM player ORDER BY idplayer DESC LIMIT 1;");
		try {
			int newPlayerID = 1;
			if (rs.next()) {
				newPlayerID = rs.getInt(1) + 1;
			}

			boolean increasingID = true;
			while (increasingID) {
				// Add a new row to the game table.
				String query = "INSERT INTO player VALUES (" + newPlayerID + ",\"" + player.getUsername() + "\","
						+ player.getIdGame() + ",\"" + player.getStatus() + "\", NULL, \""
						+ player.getPrivateObjectiveCardColor() + "\", NULL, NULL);";

				int result = dbController.doUpdateQuery(query);
				if (result == 1) {
					increasingID = false;
					newPlayerID = player.getIdPlayer();
					System.out.println(getClass() + " - New player created with id " + player.getIdPlayer()); // for
																												// testing
					// purposes
				} else {
					newPlayerID++;
				}
			}

		} catch (SQLException e) {
			System.out.println("Something went wrong while adding a new player of a game to the database.");
			e.printStackTrace();
		}
	}

}
