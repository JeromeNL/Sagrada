package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Player;

public class EndScoreController {

	private DatabaseController dbController;

	public EndScoreController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	public int amountOfDiesOfValue(Player owner, int eyes) {

		ResultSet total = dbController.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '"
						+ owner.getGameID() + "'  AND playerframefield.idplayer = '" + owner.getIdPlayer()
						+ "'AND gamedie.eyes = '" + eyes + "'");
		int intResult = 0;
		try {
			if (total.next()) {
				intResult = total.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return intResult;
	}

	public int colomnColorVarietyObjectiveScore(Player owner, String color, int position_x) {

		ResultSet total = dbController.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='"
				+ owner.getGameID() + "'AND idplayer ='" + owner.getIdPlayer() + "'AND position_x ='" + position_x
				+ "'AND diecolor = '" + color.toUpperCase() + "'");
		int intResult = 0;
		try {
			if (total.next()) {
				intResult = total.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return intResult;
	}

	public int rowColorVarietyObjectiveScore(Player owner, String color, int position_y) {

		ResultSet total = dbController.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='"
				+ owner.getGameID() + "'AND idplayer ='" + owner.getIdPlayer() + "'AND position_x ='" + position_y
				+ "'AND diecolor = '" + color.toUpperCase() + "'");
		int intResult = 0;
		try {
			if (total.next()) {
				intResult = total.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return intResult;
	}

	public int colomnShadeVarietyObjectiveScore(Player owner, int eyes, int position_x) {

		ResultSet total = dbController.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '"
						+ owner.getGameID() + "' AND playerframefield.idplayer = '" + owner.getIdPlayer()
						+ "' AND gamedie.eyes = '" + eyes + "' AND playerframefield.position_y = '" + position_x + "'");
		int intResult = 0;
		try {
			if (total.next()) {
				intResult = total.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return intResult;
	}

	public int rowShadeVarietyObjectiveScore(Player owner, int eyes, int position_y) {

		ResultSet total = dbController.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '"
						+ owner.getGameID() + "'  AND playerframefield.idplayer = '" + owner.getIdPlayer()
						+ "' AND gamedie.eyes = '" + eyes + "' AND playerframefield.position_y = '" + position_y + "'");
		int intResult = 0;
		try {
			if (total.next()) {
				intResult = total.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return intResult;
	}

	public int darkShadesObjectiveScore(Player owner, int eyes) {

		ResultSet total = dbController.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '"
						+ owner.getGameID() + "'  AND playerframefield.idplayer = '" + owner.getIdPlayer()
						+ "'AND gamedie.eyes = '" + eyes + "'");
		int intResult = 0;
		try {
			if (total.next()) {
				intResult = total.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return intResult;
	}

	public int amountOfDiesOfColor(Player owner, String color) {
		ResultSet total = dbController
				.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + owner.getGameID()
						+ "'AND idplayer ='" + owner.getIdPlayer() + "'AND diecolor = '" + color.toUpperCase() + "'");
		int intResult = 0;
		try {
			if (total.next()) {
				intResult = total.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return intResult;
	}

	public int favorToken(Player owner) {

		int favorToken = 0;
		ResultSet amountOfFavorTokens = dbController
				.doQuery("SELECT count(gametoolcard) FROM gamefavortoken WHERE idgame ='" + owner.getGameID()
						+ "' AND idplayer = '" + owner.getIdPlayer() + "' AND gametoolcard = NULL");

		try {
			if (amountOfFavorTokens.next()) {
				favorToken = amountOfFavorTokens.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return favorToken;

	}

	public int emptyTileScore(Player owner) {

		ResultSet totalTilesFilled = dbController
				.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + owner.getGameID() + "'AND idplayer ='" + owner.getIdPlayer() + "'");
		int intResult = 0;
		try {
			if (totalTilesFilled.next()) {
				intResult = totalTilesFilled.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return intResult;
	}

	public int privateObjectiveScore(Player owner) {
		int privateObjectiveScore = 0;

		ResultSet privateColor = dbController
				.doQuery("SELECT private_objectivecard_color FROM player WHERE idplayer = '" + owner.getIdPlayer() + "' AND idgame ='" + owner.getGameID()+"'");

		privateColor.toString();
		ResultSet privateScore = dbController.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.diecolor = gamedie.diecolor AND playerframefield.dienumber = gamedie.dienumber WHERE playerframefield.idgame = '" + owner.getGameID() + "' AND playerframefield.idplayer = '" + owner.getIdPlayer() + "' AND playerframefield.diecolor = '" + privateColor + "'");

		try {
			if (privateScore.next()) {
				privateObjectiveScore = privateScore.getInt(1);
				System.out.println("DBCONTROLLER --- PRIVATE: "+ privateObjectiveScore);
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return privateObjectiveScore;
	}

}