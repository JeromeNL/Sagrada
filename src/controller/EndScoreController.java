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

		ResultSet Total = dbController.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '"
						+ owner.getGameID() + "'  AND playerframefield.idplayer = '" + owner.getIdPlayer()
						+ "'AND gamedie.eyes = '" + eyes + "'");
		int intResult = 0;
		try {
			intResult = Total.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return intResult;
	}
	
	public int amountOfDiesOfColorInRow(Player owner, int color, int position_x) {

		ResultSet Total = dbController.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '"
						+ owner.getGameID() + "'  AND playerframefield.idplayer = '" + owner.getIdPlayer()
						+ "'AND gamedie.eyes = '" + eyes + "'");
		int intResult = 0;
		try {
			intResult = Total.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return intResult;
	}

	public int colomnColorVarietyObjectiveScore(Player owner, String color, int position_x) {

		ResultSet Total = dbController.doQuery(
				"SELECT count(dienumber) from playerframefield WHERE idgame ='" + owner.getGameID() + "'AND idplayer ='"
						+ owner.getIdPlayer() + "'AND position_x ='" + position_x + "'AND diecolor = '" + color.toUpperCase() + "'");
		int intResult = 0;
		try {
			intResult = Total.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return intResult;
	}
	
	public int rowColorVarietyObjectiveScore(Player owner, String color, int position_y) {

		ResultSet Total = dbController.doQuery(
				"SELECT count(dienumber) from playerframefield WHERE idgame ='" + owner.getGameID() + "'AND idplayer ='"
						+ owner.getIdPlayer() + "'AND position_x ='" + position_y + "'AND diecolor = '" + color.toUpperCase() + "'");
		int intResult = 0;
		try {
			intResult = Total.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return intResult;
	}

	public int darkShadesObjectiveScore(Player owner, int eyes) {
		
		
		ResultSet total = dbController.doQuery("SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
				+ owner.getGameID() + "'  AND playerframefield.idplayer = '" + owner.getIdPlayer() + "'AND gamedie.eyes = '"+ eyes+"'");
		int intResult = 0;
		try {
			intResult = total.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return intResult;	
}
	public int amountOfDiesOfColor(Player owner, String color) {
		ResultSet total = dbController.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + owner.getGameID()
		+ "'AND idplayer ='" + owner.getIdPlayer() + "'AND diecolor = '"+ color.toUpperCase() +"'");
	int intResult = 0;
	try {
		intResult = total.getInt(0);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return intResult;
	}
	
	public int favorToken(Player owner) {
	
		int favorToken = 0;
		ResultSet amountOfFavorTokens = dbController.doQuery("SELECT count(gametoolcard) FROM gamefavortoken WHERE idgame ='"
				+ owner.getGameID() + "' AND idplayer = '" + owner.getIdPlayer() + "' AND gametoolcard = NULL");

		try {
			favorToken = amountOfFavorTokens.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return favorToken;

	}
	
	public int emptyTileScore(Player owner) {
		
		ResultSet totalTilesFilled = dbController.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='"
				+ owner.getGameID() + "'AND idplayer ='" + owner.getIdPlayer());
		int intResult = 0;
		try {
			intResult = totalTilesFilled.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return intResult;
	}
	
	public int privateObjectiveScore(Player owner) {
		int privateObjectiveScore = 0;
		
		ResultSet privateColor = dbController.doQuery("SELECT private_objectivecard_color FROM player WHERE idplayer = '"
				+ owner.getIdPlayer() + "' AND idgame ='" + owner.getGameID());

		ResultSet privateScore = dbController.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor AND playerframefield.idgame = gamedie.idgame WHERE playerframefield.idgame = '"
						+ owner.getGameID() + "' AND playerframefield.idplayer = '" + owner.getIdPlayer()
						+ "' AND playerframefield.diecolor = '" + privateColor + "'");

		try {
			privateScore.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return privateObjectiveScore;
	}
	
}