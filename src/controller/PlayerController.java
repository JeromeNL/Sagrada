package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.GameColor;
import model.PlayerStats;
import model.PlayerStatus;

public class PlayerController {

	private DatabaseController dbController;
	private static MainController mainController;

	public PlayerController(DatabaseController dbController, MainController mainController) {
		this.dbController = dbController;
		this.mainController = mainController;
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


	public static ArrayList<PlayerStats> AllPlayerStats() throws SQLException {
		ArrayList<PlayerStats> stats = new ArrayList<>();
		DatabaseController db = new DatabaseController(mainController);
		ResultSet res = db.doQuery("SELECT p.username, p.idplayer, COALESCE(MAX(p.score), 0) as highscore,\n"
				+ "(SELECT dienumber FROM playerframefield WHERE idplayer = p.idplayer GROUP BY dienumber ORDER BY COUNT(*) DESC LIMIT 1) as mostUsedDieValue,\n"
				+ "(SELECT diecolor FROM playerframefield WHERE idplayer = p.idplayer GROUP BY diecolor ORDER BY COUNT(*) DESC LIMIT 1) as mostUsedDieColor,\n"
				+ "(SELECT COUNT(DISTINCT username) FROM player WHERE idgame IN (SELECT idgame FROM player WHERE username = p.username) AND username != p.username) as oponentCount,\n"
				+ "(SELECT COUNT(score) FROM player WHERE idgame IN (SELECT idgame FROM player WHERE username = p.username) AND playstatus = \"finished\" AND username = p.username AND ((SELECT MAX(score) FROM player WHERE idgame IN (SELECT idgame FROM player WHERE username = p.username) AND playstatus = \"finished\") - (SELECT MAX(score) FROM player WHERE idgame IN (SELECT idgame FROM player WHERE username = p.username) AND playstatus = \"finished\" AND username = p.username)) = 0  ORDER BY COUNT(*)) as wins,\n"
				+ "(SELECT COUNT(score) FROM player WHERE idgame IN (SELECT idgame FROM player WHERE username = p.username) AND playstatus = \"finished\" AND username = p.username AND ((SELECT MAX(score) FROM player WHERE idgame IN (SELECT idgame FROM player WHERE username = p.username) AND playstatus = \"finished\") - (SELECT MAX(score) FROM player WHERE idgame IN (SELECT idgame FROM player WHERE username = p.username) AND playstatus = \"finished\" AND username = p.username)) != 0  ORDER BY COUNT(*)) as losses\n"
				+ "FROM account\n" + "INNER JOIN player p\n" + "ON account.username = p.username\n"
				+ "GROUP BY p.username");
		while (res.next()) {
			stats.add(new PlayerStats(res.getString("username"), res.getInt("idplayer"), res.getInt("highscore"),
					res.getInt("mostUsedDieValue"), res.getString("mostUsedDieColor"), res.getInt("oponentCount"),
					res.getInt("wins"), res.getInt("losses")));
		}
		return stats;
	}

}
