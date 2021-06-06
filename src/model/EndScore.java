package model;

import java.util.ArrayList;

import controller.DatabaseController;
import controller.EndScoreController;
import controller.MainController;

public class EndScore {

	private int array[];

	private Player owner;
	private DatabaseController dbController;
	private EndScoreController esdbController;
	private MainController mainController;

	public EndScore(Player owner, DatabaseController dbController, MainController newMainController) {
		array = new int[5];
		this.owner = owner;
		this.dbController = dbController;
		mainController = newMainController;
		esdbController = new EndScoreController(dbController);
	}

	public int publicObjectiveScore() {
		int totalPublicObjectiveScore = 0;

		System.out.println("TEST TEST TEST " + mainController.getCurrentGame().getIdGame());

		ArrayList<Integer> ids = dbController.getPublicObjectiveIDs(mainController.getCurrentGame().getIdGame());

		for (int id = 0; id < 3; id++) {
			System.out.println("GET ID FROM GETPUBLICOBJECTIVECARDID: "
					+ dbController.getPublicObjectiveIDs(owner.getGameID()).get(id));

			if (ids.get(id) == 1) {
				totalPublicObjectiveScore += shadeVarietyObjectiveScore();

			} else if (ids.get(id) == 2) {
				totalPublicObjectiveScore += mediumShadesObjectiveScore();
			} else if (ids.get(id) == 3) {
				totalPublicObjectiveScore += colomnShadeVarietyObjectiveScore();
			} else if (ids.get(id) == 4) {
				totalPublicObjectiveScore += colomnColorVarietyObjectiveScore();
			} else if (ids.get(id) == 5) {
				totalPublicObjectiveScore += darkShadesObjectiveScore();
			} else if (ids.get(id) == 6) {
				totalPublicObjectiveScore += colorVarietyObjectiveScore();
			} else if (ids.get(id) == 7) {
				totalPublicObjectiveScore += rowColorVarietyObjectiveScore();
			} else if (ids.get(id) == 9) {
				totalPublicObjectiveScore += lightShadesObjectiveScore();
			} else if (ids.get(id) == 10) {
				totalPublicObjectiveScore += rowShadeVarietyObjectiveScore();
			} else {

			}
		}
		System.out.println("totalPublic " + totalPublicObjectiveScore);
		return totalPublicObjectiveScore;
	}

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
		System.out.println("PUBLIC shadeVariety: " + shadeVarietyObjectiveScore);
		return shadeVarietyObjectiveScore;
	}

	public int mediumShadesObjectiveScore() {
		int mediumShadesObjectiveScore = 0;

		if (esdbController.amountOfDiesOfValue(owner, 3) > esdbController.amountOfDiesOfValue(owner, 4)) {
			mediumShadesObjectiveScore = (esdbController.amountOfDiesOfValue(owner, 4) * 2);
		} else {
			mediumShadesObjectiveScore = (esdbController.amountOfDiesOfValue(owner, 3) * 2);
		}
		System.out.println("PUBLIC mediumShades: " + mediumShadesObjectiveScore);
		return mediumShadesObjectiveScore;

	}

	public int colomnColorVarietyObjectiveScore() {

		int score;
		int index;
		int amountOfDie = 0;
		index = 1;
		score = 0;

		while (index < 6) {

			if ((esdbController.colomnColorVarietyObjectiveScore(owner, "red", index) < 2)) {
				if (esdbController.colomnColorVarietyObjectiveScore(owner, "red", index) == 1) {
					amountOfDie++;
				}

				if ((esdbController.colomnColorVarietyObjectiveScore(owner, "blue", index) < 2)) {
					if (esdbController.colomnColorVarietyObjectiveScore(owner, "blue", index) == 1) {
						amountOfDie++;
					}

					if ((esdbController.colomnColorVarietyObjectiveScore(owner, "yellow", index) < 2)) {
						if (esdbController.colomnColorVarietyObjectiveScore(owner, "yellow", index) == 1) {
							amountOfDie++;
						}

						if ((esdbController.colomnColorVarietyObjectiveScore(owner, "purple", index) < 2)) {
							if (esdbController.colomnColorVarietyObjectiveScore(owner, "purple", index) == 1) {
								amountOfDie++;
							}

							if ((esdbController.colomnColorVarietyObjectiveScore(owner, "green", index) < 2)) {
								if (esdbController.colomnColorVarietyObjectiveScore(owner, "green", index) == 1) {
									amountOfDie++;
								}

							}
						}
					}
				}

			}
			if (amountOfDie == 4) {

				score += 5;

				amountOfDie = 0;
			}
			index++;
		}
		System.out.println("PUBLIC colomnColorVariety: " + score);
		return score;
	}

	public int colomnShadeVarietyObjectiveScore() {

		int score;
		int index;
		int amountOfDie = 0;
		index = 1;
		score = 0;

		while (index < 6) {
			if ((esdbController.colomnShadeVarietyObjectiveScore(owner, 1, index) < 2)) {

				if (esdbController.colomnShadeVarietyObjectiveScore(owner, 1, index) == 1) {
					amountOfDie++;
				}

				if ((esdbController.colomnShadeVarietyObjectiveScore(owner, 2, index) < 2)) {

					if (esdbController.colomnShadeVarietyObjectiveScore(owner, 2, index) == 1) {
						amountOfDie++;
					}

					if ((esdbController.colomnShadeVarietyObjectiveScore(owner, 3, index) < 2)) {

						if (esdbController.colomnShadeVarietyObjectiveScore(owner, 3, index) == 1) {
							amountOfDie++;
						}

						if ((esdbController.colomnShadeVarietyObjectiveScore(owner, 4, index) < 2)) {

							if (esdbController.colomnShadeVarietyObjectiveScore(owner, 4, index) == 1) {
								amountOfDie++;
							}

							if ((esdbController.colomnShadeVarietyObjectiveScore(owner, 5, index) < 2)) {

								if (esdbController.colomnShadeVarietyObjectiveScore(owner, 5, index) == 1) {
									amountOfDie++;
								}

								if ((esdbController.colomnShadeVarietyObjectiveScore(owner, 6, index) < 2)) {

									if (esdbController.colomnShadeVarietyObjectiveScore(owner, 6, index) == 1) {
										amountOfDie++;
									}

								}

							}

						}

					}

				}

			}
			if (amountOfDie == 4) {

				score += 4;
				amountOfDie = 0;
			}
			index++;
		}
		System.out.println("PUBLIC colomnShadeVariety: " + score);
		return score;

	}

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
		System.out.println("PUBLIC RowColorVariety: " + score);
		return score;

	}

	public int rowShadeVarietyObjectiveScore() {
		int score;
		int index;
		int amountOfDie = 0;
		index = 1;
		score = 0;

		while (index < 5) {
			if (esdbController.rowShadeVarietyObjectiveScore(owner, 1, index) < 2) {

				if (esdbController.rowShadeVarietyObjectiveScore(owner, 1, index) == 1) {
					amountOfDie++;
				}

				if (esdbController.rowShadeVarietyObjectiveScore(owner, 2, index) < 2) {

					if (esdbController.rowShadeVarietyObjectiveScore(owner, 2, index) == 1) {
						amountOfDie++;
					}

					if (esdbController.rowShadeVarietyObjectiveScore(owner, 3, index) < 2) {

						if (esdbController.rowShadeVarietyObjectiveScore(owner, 3, index) == 1) {
							amountOfDie++;
						}

						if (esdbController.rowShadeVarietyObjectiveScore(owner, 4, index) < 2) {

							if (esdbController.rowShadeVarietyObjectiveScore(owner, 4, index) == 1) {
								amountOfDie++;
							}

							if (esdbController.rowShadeVarietyObjectiveScore(owner, 5, index) < 2) {

								if (esdbController.rowShadeVarietyObjectiveScore(owner, 5, index) == 1) {
									amountOfDie++;
								}

								if (esdbController.rowShadeVarietyObjectiveScore(owner, 6, index) < 2) {

									if (esdbController.rowShadeVarietyObjectiveScore(owner, 6, index) == 1) {
										amountOfDie++;
									}

								}
							}
						}
					}

				}

			}
			index++;
			if (amountOfDie == 5) {

				score += 5;

			}
		}

		System.out.println("PUBLIC rowShadesVariety: " + score);

		return score;
	}

	public int darkShadesObjectiveScore() {
		int darkShadesObjectiveScore = 0;

		if (esdbController.darkShadesObjectiveScore(owner, 5) > esdbController.darkShadesObjectiveScore(owner, 6)) {
			darkShadesObjectiveScore = (esdbController.darkShadesObjectiveScore(owner, 6) * 2);
		} else {
			darkShadesObjectiveScore = (esdbController.darkShadesObjectiveScore(owner, 5) * 2);
		}
		System.out.println("PUBLIC darkShades: " + darkShadesObjectiveScore);
		return darkShadesObjectiveScore;

	}

	public int colorVarietyObjectiveScore() {
		int colorVarietyObjectiveScore = 0;

		int[] colorArray; // declare an array of integers
		colorArray = new int[5];
		colorArray[0] = esdbController.amountOfDiesOfColor(owner, "RED");
		colorArray[1] = esdbController.amountOfDiesOfColor(owner, "BLUE");
		colorArray[2] = esdbController.amountOfDiesOfColor(owner, "GREEN");
		colorArray[3] = esdbController.amountOfDiesOfColor(owner, "YELLOW");
		colorArray[4] = esdbController.amountOfDiesOfColor(owner, "PURPLE");

		int min = colorArray[0];

		for (int i = 0; i < colorArray.length; i++) {
			if (colorArray[i] < min) {
				min = colorArray[i];
			}
		}

		colorVarietyObjectiveScore = (min * 4);
		System.out.println("PUBLIC Color variety: " + colorVarietyObjectiveScore);
		return colorVarietyObjectiveScore;
	}

	// WERKT 6-6-2021
	public int lightShadesObjectiveScore() {
		int lightShadesObjectiveScore = 0;

		if (esdbController.darkShadesObjectiveScore(owner, 1) > esdbController.darkShadesObjectiveScore(owner, 2)) {
			lightShadesObjectiveScore = (esdbController.darkShadesObjectiveScore(owner, 2) * 2);
		} else {
			lightShadesObjectiveScore = (esdbController.darkShadesObjectiveScore(owner, 1) * 2);
		}
		System.out.println("PUBLIC lightShades: " + lightShadesObjectiveScore);
		return lightShadesObjectiveScore;
	}

	public int privateObjectiveScore() {
		int privateObjectiveScore = esdbController.privateObjectiveScore(owner);

		System.out.println("PRIVATE privateObjectiveScore: " + privateObjectiveScore);
		return privateObjectiveScore;
	}

	public int countAllDieEyes() {
		int allCountedEyes = esdbController.countAllDieEyes(owner);

		System.out.println(allCountedEyes);
		return allCountedEyes;
	}

	public int emptyTileScore() {

		int emptyScore = ((20 - esdbController.emptyTileScore(owner)) * -1);
		// query voor opvragen vakjes van speler.
		// controleren hoeveel er null zijn (diecolor of dienumber)
		// aantal null == aantal punten erbij
		System.out.println("EMPTY TILES SCORE (should be negative): " + emptyScore);
		return emptyScore;
	}

	public int totalEndScore() {
		int totalScore = publicObjectiveScore() + privateObjectiveScore() + emptyTileScore() + countAllDieEyes();
		System.out.println("Total endscore: " + totalScore);
		return totalScore;
	}

	public int scoreDuringGame() {
		int scoreDuringGame = publicObjectiveScore() + countAllDieEyes();
		return scoreDuringGame;
	}

}
