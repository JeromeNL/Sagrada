package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import controller.DatabaseController;

public class EndScore {

	private int idGame;
	private int idPlayer;
	private int array[];

	public EndScore(int idGame, int idPlayer) {
		this.idGame = idGame;
		this.idPlayer = idPlayer;
		array = new int[5];

	}


	// ZOU MOETEN WERKEN

	public int publicObjectiveScore() {
		int publicObjectiveScore = mediumShadesObjectiveScore() + shadeVarietyObjectiveScore()
				+ colomnShadeVarietyObjectiveScore() + colomnColorVarietyObjectiveScore() + darkShadesObjectiveScore()
				+ colorVarietyObjectiveScore() + rowColorVarietyObjectiveScore() + diagonalsObjectiveScore()
				+ lightShadesObjectiveScore() + rowShadeVarietyObjectiveScore();
		return publicObjectiveScore;
	}


	// ZOU MOETEN WERKEN!

	public int shadeVarietyObjectiveScore() {

		int shadeVarietyObjectiveScore = 0;
		DatabaseController db = new DatabaseController(null);

		ResultSet TotalOnes = db.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '"
						+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 1");
		int intResultOnes = 0;
		try {
			intResultOnes = TotalOnes.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet TotalTwos = db.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '"
						+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 2");
		int intResultTwos = 0;
		try {
			intResultTwos = TotalTwos.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet TotalThrees = db.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
						+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 3");
		int intResultThrees = 0;
		try {
			intResultThrees = TotalThrees.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet TotalFours = db.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
						+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 4");
		int intResultFours = 0;
		try {
			intResultFours = TotalFours.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet TotalFives = db.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
						+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 5");
		int intResultFives = 0;
		try {
			intResultFives = TotalFives.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		array[0] = intResultOnes;
		array[1] = intResultTwos;
		array[2] = intResultThrees;
		array[3] = intResultFours;
		array[4] = intResultFives;

		int minValue = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < minValue) {
				minValue = array[i];
			}
		}

		shadeVarietyObjectiveScore = (minValue * 5);

		return shadeVarietyObjectiveScore;
	}

	// ZOU MOETEN WERKEN
	public int mediumShadesObjectiveScore() {
		int mediumShadesObjectiveScore = 0;
		DatabaseController db = new DatabaseController(null);

		ResultSet totalThrees = db.doQuery("SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
				+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 3");
		int intResultThrees = 0;
		try {
			intResultThrees = totalThrees.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet TotalFours = db.doQuery("SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
				+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 4");
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

	// NOG MAKEN
	public int colomnShadeVarietyObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	// NOG MAKEN
	public int colomnColorVarietyObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	// ZOU MOETEN WERKEN
	public int darkShadesObjectiveScore() {
		int darkShadesObjectiveScore = 0;
		DatabaseController db = new DatabaseController(null);

		ResultSet totalFives = db.doQuery("SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
				+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 5");
		int intResultFives = 0;
		try {
			intResultFives = totalFives.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet totalSixes = db.doQuery("\"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = ' "
				 + idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 6");
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

	// ZOU MOETEN WERKEN
	public int colorVarietyObjectiveScore() {
		int colorVarietyObjectiveScore = 0;
		DatabaseController db = new DatabaseController(null);

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
			intResultYellows = totalYellows.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet totalPurples = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer + "'AND diecolor = YELLOW");
		int intResultPurples = 0;
		try {
			intResultPurples = totalPurples.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int[] KleurArray;	        // declare an array of integers
		KleurArray = new int[5];
		KleurArray[0] = intResultReds;
		KleurArray[1] = intResultBlues;
		KleurArray[2] = intResultGreens;
		KleurArray[3] = intResultYellows;
		KleurArray[4] = intResultPurples;
		int max = 0;
		for (int i = 0; i < KleurArray.length; i++) {
			  if (KleurArray[i] > max) {
			     max = KleurArray[i];
			  }
			}
		colorVarietyObjectiveScore = max;
		return colorVarietyObjectiveScore;
	}

	// MOET NOG GEMAAKT WORDEN
	public int rowColorVarietyObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	// MOET NOG GEMAAKT WORDEN
	public int diagonalsObjectiveScore() {
		int publicObjectiveScore = 0;
		return publicObjectiveScore;

	}

	// ZOU MOETEN WERKEN
	public int lightShadesObjectiveScore() {
		int lightShadesObjectiveScore = 0;
		DatabaseController db = new DatabaseController(null);

		ResultSet TotalOnes = db.doQuery("SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
				+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 1");
		int intResultOnes = 0;
		try {
			intResultOnes = TotalOnes.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet TotalTwos = db.doQuery("SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idgame = '" 
				+ idGame + "'  AND playerframefield.idplayer = '" + idPlayer + "'AND gamedie.eyes = 2");
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

	// MOET NOG GEMAAKT WORDEN
	public int rowShadeVarietyObjectiveScore() {
		int rowShadeVarietyObjectiveScore = 0;
		return rowShadeVarietyObjectiveScore;

	}
	
	// ZOU MOETEN WERKEN
	public int privateObjectiveScore() {
		int privateObjectiveScore = 0;
		DatabaseController db = new DatabaseController(null);
		ResultSet privateColor = db.doQuery("SELECT private_objectivecard_color FROM player WHERE idplayer = '"
				+ idPlayer + "' AND idgame ='" + idGame);

		ResultSet privateScore = db.doQuery(
				"SELECT COUNT(*) FROM playerframefield INNER JOIN gamedie ON playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor AND playerframefield.idgame = gamedie.idgame WHERE playerframefield.idgame = '"
						+ idGame + "' AND playerframefield.idplayer = '" + idPlayer
						+ "' AND playerframefield.diecolor = '" + privateColor + "'");

		try {
			privateScore.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return privateObjectiveScore;
	}

	// ZOU MOETEN WERKEN
	public int favorToken() {
		int favorToken = 0;
		DatabaseController db = new DatabaseController(null);
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

	// ZOU MOETEN WERKEN
	public int emptyTileScore() {
		int emptyScore = 0;
		DatabaseController db = new DatabaseController(null);

		ResultSet totalTilesFilled = db.doQuery("SELECT count(dienumber) from playerframefield WHERE idgame ='" + idGame
				+ "'AND idplayer ='" + idPlayer);
		int intResult = 0;
		try {
			intResult = totalTilesFilled.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		emptyScore = ((20 - intResult) * -1);
		// query voor opvragen vakjes van speler.
		// controleren hoeveel er null zijn (diecolor of dienumber)
		// aantal null == aantal punten erbij

		return emptyScore;
	}
	
	// ZOU MOETEN WERKEN
	public int totalEndScore() {
		int totalScore = publicObjectiveScore() + privateObjectiveScore() + favorToken() + emptyTileScore();
		return totalScore;
	}
	
	// ZOU MOETEN WERKEN
	public int scoreDuringGame() {
		int scoreDuringGame = publicObjectiveScore();
		return scoreDuringGame;
	}

}
