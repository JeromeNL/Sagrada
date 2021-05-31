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
		int publicObjectiveScore = mediumShadesObjectiveScore() + shadeVarietyObjectiveScore() + colomnShadeVarietyObjectiveScore() + colomnColorVarietyObjectiveScore() + darkShadesObjectiveScore() + colorVarietyObjectiveScore() + rowColorVarietyObjectiveScore() + diagonalsObjectiveScore() + lightShadesObjectiveScore() + rowShadeVarietyObjectiveScore();
		return publicObjectiveScore;
	}
	
	public int shadeVarietyObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	public int mediumShadesObjectiveScore() {
		int mediumShadesObjectiveScore = 0;
		DatabaseController db = new DatabaseController();

		ResultSet totalThrees = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND dienumber = 3");
		int intResultThrees = 0;
		try {
			intResultThrees = totalThrees.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet TotalFours = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND dienumber = 4");
		int intResultFours = 0;
		try {
			intResultFours = TotalFours.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (intResultFours > intResultThrees) {
			mediumShadesObjectiveScore = (intResultThrees * 2);
		} else {
			mediumShadesObjectiveScore = (intResultFours * 2);
		}
		return mediumShadesObjectiveScore;

	}

	public int colomnShadeVarietyObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	public int colomnColorVarietyObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	public int darkShadesObjectiveScore() {
		int darkShadesObjectiveScore = 0;
		DatabaseController db = new DatabaseController();

		ResultSet totalFives = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND dienumber = 5");
		int intResultFives = 0;
		try {
			intResultFives = totalFives.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet totalSixes = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND dienumber = 6");
		int intResultSixes = 0;
		try {
			intResultSixes = totalSixes.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (intResultSixes > intResultFives) {
			darkShadesObjectiveScore = (intResultFives * 2);
		} else {
			darkShadesObjectiveScore = (intResultSixes * 2);
		}
		return darkShadesObjectiveScore;

	}

	public int colorVarietyObjectiveScore() {
		int colorVarietyObjectiveScore = 0;
		DatabaseController db = new DatabaseController();

		ResultSet totalReds = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND diecolor = RED");
		int intResultReds = 0;
		try {
			intResultReds = totalReds.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet totalBlues = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND diecolor = BLUE");
		int intResultBlues = 0;
		try {
			intResultBlues = totalBlues.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet totalGreens = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND diecolor = GREEN");
		int intResultGreens = 0;
		try {
			intResultGreens = totalGreens.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet totalYellows = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND diecolor = YELLOW");
		int intResultYellows = 0;
		try {
			intResultGreens = totalYellows.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		

		
	}

	public int rowColorVarietyObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	public int diagonalsObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	public int lightShadesObjectiveScore() {
		int lightShadesObjectiveScore = 0;
		DatabaseController db = new DatabaseController();

		ResultSet TotalOnes = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND dienumber = 1");
		int intResultOnes = 0;
		try {
			intResultOnes = TotalOnes.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet TotalTwos = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND dienumber = 2");
		int intResultTwos = 0;
		try {
			intResultTwos = TotalTwos.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (intResultTwos > intResultOnes) {
			lightShadesObjectiveScore = (intResultOnes * 2);
		} else {
			lightShadesObjectiveScore = (intResultTwos * 2);
		}
		return lightShadesObjectiveScore;
	}

	public int rowShadeVarietyObjectiveScore() {
		int rowShadeVarietyObjectiveScore = 0;
		return rowShadeVarietyObjectiveScore;

	}

	public int privateObjectiveScore() {
		int privateObjectiveScore = 0;
		DatabaseController db = new DatabaseController();
		ResultSet privateColor = db.doQuery("SELECT private_objectivecard_color FROM player WHERE idplayer = '"
				+ idPlayer + "' AND idgame ='" + idGame);

		ResultSet privateScore = db.doQuery("SELECT sum(dienumber) FROM playerframefield WHERE idgame = '" + 487
				+ "'AND idplayer ='" + 962 + "' AND diecolor ='" + privateColor);

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
		ResultSet amountOfFavorTokens = db.doQuery("SELECT count(gametoolcard) FROM gamefavortoken WHERE idgame ='"
				+ idGame + "' AND idplayer = '" + idPlayer + "' AND gametoolcard = NULL");

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
