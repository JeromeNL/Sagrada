package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DatabaseController;

public class EndScore {

	private int idGame;
	private int idPlayer;
	

	public EndScore(int idGame, int idPlayer) {
		this.idGame = idGame;
		this.idPlayer = idPlayer;
	}
	
	public int publicObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	public int privateObjectiveScore() {
		int privateObjectiveScore = 0;
		DatabaseController db = new DatabaseController();
		ResultSet privateColor = db.doQuery("SELECT private_objectivecard_color FROM player WHERE idplayer = '" + idPlayer + "' AND idgame ='" + idGame);
		
		ResultSet privateScore = db.doQuery("SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor AND playerframefield.idgame = gamedie.idgame WHERE playerframefield.idgame = '" +  idGame + "' AND playerframefield.idplayer = '" + idPlayer + "' AND playerframefield.diecolor = '" + privateColor + "'");
		
		try {
			privateScore.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return privateObjectiveScore;
	}

	public int favorToken() {
		int favorToken = 0;
		DatabaseController db = new DatabaseController();
		ResultSet amountOfFavorTokens = db.doQuery("SELECT count(gametoolcard) FROM gamefavortoken WHERE idgame ='" + idGame + "' AND idplayer = '" + idPlayer + "' AND gametoolcard = NULL");
		
		try {
			favorToken = amountOfFavorTokens.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return favorToken;

	}

	public int emptyTileScore() {
		int emptyScore = 0;
		DatabaseController db = new DatabaseController();

		ResultSet totalTilesFilled = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer);
		int intResult = 0;
		try {
			intResult = totalTilesFilled.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		emptyScore = 20 - intResult;
		// query voor opvragen vakjes van speler.
		// controleren hoeveel er null zijn (diecolor of dienumber)
		// aantal null == aantal punten erbij

		return emptyScore;
	}

	public int totalEndScore() {
		int totalScore = publicObjectiveScore() + privateObjectiveScore() + favorToken() + emptyTileScore();
		return totalScore;
	}

}
