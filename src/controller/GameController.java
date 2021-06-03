package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.GameStats;

public class GameController {

	private DatabaseController dbController;
	private MainController mainController;

	public GameController(DatabaseController dbController, MainController mainController) {
		this.dbController = dbController;
		this.mainController = mainController;
	}

	public int getAvailableGameID() {

		ResultSet rs = dbController.doQuery("SELECT idgame FROM game ORDER BY idgame DESC LIMIT 1;");

		int availableGameID = 1;

		try {
			while (rs.next()) {
				availableGameID += rs.getInt(1);

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

	public ArrayList<GameStats> AllGameStats() throws SQLException {
		ArrayList<GameStats> gameStats = new ArrayList<>();
		DatabaseController db = new DatabaseController(mainController);
		ResultSet res = db.doQuery(
				"SELECT game.idgame, GROUP_CONCAT(username) as username, COALESCE(current_roundID, 0) as round, creationdate, CASE WHEN playstatus IN ('challenger','accepted','challengee') THEN 'bezig' ELSE 'finished' END as gamestatus FROM game RIGHT JOIN player ON game.idgame = player.idgame GROUP BY idgame");
		while (res.next()) {
			gameStats.add(new GameStats(res.getString("username"), res.getInt("round"), res.getInt("idgame"),
					res.getString("creationdate"), res.getString("gamestatus")));
		}
		return gameStats;
	}

}
