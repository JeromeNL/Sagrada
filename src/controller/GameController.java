package controller;
import model.GameStats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class GameController {
	public static ArrayList<GameStats> AllGameStats() throws SQLException {
		ArrayList<GameStats> gameStats = new ArrayList<>();
		DatabaseController db = new DatabaseController();
		ResultSet res = db.doQuery(
				"SELECT game.idgame, GROUP_CONCAT(username) as username, COALESCE(current_roundID, 0) as round, creationdate, CASE WHEN playstatus IN ('challenger','accepted','challengee') THEN 'bezig' ELSE 'finished' END as gamestatus FROM game RIGHT JOIN player ON game.idgame = player.idgame GROUP BY idgame");
		while (res.next()) {
			gameStats.add(new GameStats(res.getString("username"), res.getInt("round"), res.getInt("idgame"),
					res.getString("creationdate"), res.getString("gamestatus")));
		}
		return gameStats;
	}
}
