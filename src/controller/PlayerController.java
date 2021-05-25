package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.GameColor;
import model.PlayerStatus;

public class PlayerController {

	private DatabaseController dbController;

	public PlayerController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	// Add a playerframefield row in the database.
	public void createPlayerFrameField(int idPlayer, int idGame, int position_x, int position_y) {

		String query = "INSERT INTO playerframefield VALUES (" + idPlayer + "," + position_x + "," + position_y + ","
				+ idGame + ",NULL,NULL);";

		dbController.doUpdateQuery(query);
	}

	public int getAvailablePlayerId() {

		ResultSet rs = dbController.doQuery("SELECT idplayer FROM player ORDER BY idplayer DESC LIMIT 1;");

		int newIdPlayer = 0;

		try {
			while (rs.next()) {
				newIdPlayer = rs.getInt(1) + 1;
			}

		} catch (SQLException e) {
			System.out.println("Something went wrong while getting a playerID.");
			e.printStackTrace();
		}
		return newIdPlayer;

	}

	public int addRowToPlayerTable(int newIdPlayer, String username, int idGame, PlayerStatus status,
			GameColor privateObjectiveCardColor) {

		// Add a new row to the player table.
		String query = "INSERT INTO player VALUES (" + newIdPlayer + ",\"" + username + "\"," + idGame + ",\"" + status
				+ "\", NULL, \"" + privateObjectiveCardColor + "\", NULL, NULL);";

		int result = dbController.doUpdateQuery(query);

		return result;
	}

}
