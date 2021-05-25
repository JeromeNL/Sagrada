package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameController {

	private DatabaseController dbController;

	public GameController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	public int getAvailableGameID() {

		ResultSet rs = dbController.doQuery("SELECT idgame FROM game ORDER BY idgame DESC LIMIT 1;");

		int availableGameID = 0;

		try {
			while (rs.next()) {
				availableGameID = rs.getInt(1) + 1;

			}

		} catch (SQLException e) {
			System.out.println("Something went wrong while getting a available gameID.");
			e.printStackTrace();
		}
		return availableGameID;

	}

	public int addRowToGameTable(int newIdGame) {

		// Add a new row to the game table.
		String query = "INSERT INTO game VALUES (" + newIdGame + ",NULL,NULL,CURRENT_TIMESTAMP);";
		int result = dbController.doUpdateQuery(query);

		return result;
	}

}
