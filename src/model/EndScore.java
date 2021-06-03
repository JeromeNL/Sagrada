package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import controller.DatabaseController;
import controller.EndScoreController;

public class EndScore {

//	private int idGame;
//	private int idPlayer;
	private int array[];

	private Player owner;
	private DatabaseController dbController;
	private EndScoreController esdbController;

	public EndScore(Player owner, DatabaseController dbController) {
//		this.idGame = idGame;
//		this.idPlayer = idPlayer;
		array = new int[5];
		this.owner = owner;

		esdbController = new EndScoreController(dbController);
	}

	// ZOU MOETEN WERKEN

	public int publicObjectiveScore() {
		int totalPublicObjectiveScore = 0;
		for (int i = 0; i < 3; i++) {
			dbController.getToolcardIDs(owner.getGameID()).get(i);
			
			if (dbController.getToolcardIDs(owner.getGameID()).get(0) == 1) {
				totalPublicObjectiveScore += shadeVarietyObjectiveScore();
			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 2) {
				totalPublicObjectiveScore += mediumShadesObjectiveScore();
			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 3) {
				totalPublicObjectiveScore += colomnShadeVarietyObjectiveScore();
			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 4) {
				totalPublicObjectiveScore += colomnColorVarietyObjectiveScore();
			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 5) {
				totalPublicObjectiveScore += darkShadesObjectiveScore();
			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 6) {
				totalPublicObjectiveScore += colorVarietyObjectiveScore();
			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 7) {
				totalPublicObjectiveScore += rowColorVarietyObjectiveScore();
//			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 8) {
//				totalScore += rowColorVarietyObjectiveScore();
			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 9) {
				totalPublicObjectiveScore += lightShadesObjectiveScore();
			} else if (dbController.getToolcardIDs(owner.getGameID()).get(i) == 10) {
				totalPublicObjectiveScore += rowShadeVarietyObjectiveScore();
			}

		}
		return totalPublicObjectiveScore;
	}

	// ZOU MOETEN WERKEN!

	public int shadeVarietyObjectiveScore() {

		int shadeVarietyObjectiveScore = 0;

		array[0] = esdbController.amountOfDiesOfValue(owner, 1);
		array[1] = esdbController.amountOfDiesOfValue(owner, 2);
		array[2] = esdbController.amountOfDiesOfValue(owner, 3);
		array[3] = esdbController.amountOfDiesOfValue(owner, 4);
		array[4] = esdbController.amountOfDiesOfValue(owner, 5);

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

		if (esdbController.amountOfDiesOfValue(owner, 3) > esdbController.amountOfDiesOfValue(owner, 4)) {
			mediumShadesObjectiveScore = (esdbController.amountOfDiesOfValue(owner, 3) * 2);
		} else {
			mediumShadesObjectiveScore = (esdbController.amountOfDiesOfValue(owner, 4) * 2);
		}
		return mediumShadesObjectiveScore;

	}

	public int colomnColorVarietyObjectiveScore() {

		int score;
		int index;
		index = 1;
		score = 0;

		while (index < 6) {
			if (!(esdbController.colomnColorVarietyObjectiveScore(owner, "red", index) > 1)) {
				if (!(esdbController.colomnColorVarietyObjectiveScore(owner, "blue", index) > 1)) {
					if (!(esdbController.colomnColorVarietyObjectiveScore(owner, "yellow", index) > 1)) {
						if (!(esdbController.colomnColorVarietyObjectiveScore(owner, "purple", index) > 1)) {
							if (!(esdbController.colomnColorVarietyObjectiveScore(owner, "green", index) > 1)) {
								score += 6;
							}
						}
					}
				}

			}
			index++;
		}
		return score;
	}

	// NOG MAKEN
	public int colomnShadeVarietyObjectiveScore() {

		int score;
		int index;
		index = 1;
		score = 0;

		while (index < 6) {
			if (!(esdbController.colomnShadeVarietyObjectiveScore(owner, 1, index) > 1)) {
				if (!(esdbController.colomnShadeVarietyObjectiveScore(owner, 2, index) > 1)) {
					if (!(esdbController.colomnShadeVarietyObjectiveScore(owner, 3, index) > 1)) {
						if (!(esdbController.colomnShadeVarietyObjectiveScore(owner, 4, index) > 1)) {
							if (!(esdbController.colomnShadeVarietyObjectiveScore(owner, 5, index) > 1)) {
								score += 6;
							}
						}
					}
				}

			}
			index++;
		}
		return score;
	}

	// MOET NOG GEMAAKT WORDEN
	public int rowColorVarietyObjectiveScore() {

		int score;
		int index;
		index = 1;
		score = 0;

		while (index < 5) {
			if (esdbController.rowColorVarietyObjectiveScore(owner, "red", index) == 1) {
				if (esdbController.rowColorVarietyObjectiveScore(owner, "blue", index) == 1) {
					if (esdbController.rowColorVarietyObjectiveScore(owner, "yellow", index) == 1) {
						if (esdbController.rowColorVarietyObjectiveScore(owner, "purple", index) == 1) {
							if (esdbController.rowColorVarietyObjectiveScore(owner, "green", index) == 1) {
								score += 6;
							}
						}
					}
				}

			}
			index++;
		}
		return score;

	}

	public int rowShadeVarietyObjectiveScore() {
		int score;
		int index;
		index = 1;
		score = 0;

		while (index < 5) {
			if (esdbController.rowShadeVarietyObjectiveScore(owner, 1, index) == 1) {
				if (esdbController.rowShadeVarietyObjectiveScore(owner, 2, index) == 1) {
					if (esdbController.rowShadeVarietyObjectiveScore(owner, 3, index) == 1) {
						if (esdbController.rowShadeVarietyObjectiveScore(owner, 4, index) == 1) {
							if (esdbController.rowShadeVarietyObjectiveScore(owner, 5, index) == 1) {
								score += 6;
							}
						}
					}
				}

			}
			index++;
		}
		return score;

	}

	// ZOU MOETEN WERKEN
	public int darkShadesObjectiveScore() {
		int darkShadesObjectiveScore = 0;

		if (esdbController.darkShadesObjectiveScore(owner, 5) > esdbController.darkShadesObjectiveScore(owner, 6)) {
			darkShadesObjectiveScore = (esdbController.darkShadesObjectiveScore(owner, 5) * 2);
		} else {
			darkShadesObjectiveScore = (esdbController.darkShadesObjectiveScore(owner, 6) * 2);
		}
		return darkShadesObjectiveScore;

	}

	// ZOU MOETEN WERKEN
	public int colorVarietyObjectiveScore() {
		int colorVarietyObjectiveScore = 0;

		int[] KleurArray; // declare an array of integers
		KleurArray = new int[5];
		KleurArray[0] = esdbController.amountOfDiesOfColor(owner, "RED");
		KleurArray[1] = esdbController.amountOfDiesOfColor(owner, "BLUE");
		KleurArray[2] = esdbController.amountOfDiesOfColor(owner, "GREEN");
		KleurArray[3] = esdbController.amountOfDiesOfColor(owner, "YELLOW");
		KleurArray[4] = esdbController.amountOfDiesOfColor(owner, "PURPLE");

		int max = 0;
		for (int i = 0; i < KleurArray.length; i++) {
			if (KleurArray[i] > max) {
				max = KleurArray[i];
			}
		}
		colorVarietyObjectiveScore = max;
		return colorVarietyObjectiveScore;
	}

	// ZOU MOETEN WERKEN
	public int lightShadesObjectiveScore() {
		int darkShadesObjectiveScore = 0;

		if (esdbController.darkShadesObjectiveScore(owner, 1) > esdbController.darkShadesObjectiveScore(owner, 2)) {
			darkShadesObjectiveScore = (esdbController.darkShadesObjectiveScore(owner, 1) * 2);
		} else {
			darkShadesObjectiveScore = (esdbController.darkShadesObjectiveScore(owner, 2) * 2);
		}
		return darkShadesObjectiveScore;
	}

	// ZOU MOETEN WERKEN
	public int privateObjectiveScore() {
		int privateObjectiveScore = esdbController.privateObjectiveScore(owner);
		return privateObjectiveScore;
	}

	// ZOU MOETEN WERKEN
	public int favorToken() {
		int favorToken = esdbController.favorToken(owner);
		return favorToken;
	}

	// ZOU MOETEN WERKEN
	public int emptyTileScore() {

		int emptyScore = ((20 - esdbController.emptyTileScore(owner)) * -1);
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
