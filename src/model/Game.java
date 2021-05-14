package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import controller.DatabaseController;

public class Game {
	private int blueCounter;
	private int greenCounter;
	private int yellowCounter;
	private int redCounter;
	private int purpleCounter;
	
	
	private DatabaseController dbController;

	private int idGame;

	private Round[] rounds; // 20 round IDs in database (so 20 Round objects)
	private int currentRoundID;

	private Die[] dies; // 90 dies
	private FavorToken[] favorTokens; // 24 favor tokens per game.
	private Toolcard[] toolcards; // 3 toolcards per game.
	private ObjectiveCard[] objectiveCards; // 3 public objective cards per game.
	private ArrayList<Player> players; // 1 to 4 players per game
	private DiesInSupply diesInSupply;

	private String usernameCreator;

	public Game(String usernameCreator, DatabaseController dbController) {
		
		blueCounter = 0;
		greenCounter = 0;
		yellowCounter = 0;
		redCounter = 0;
		purpleCounter = 0;
		
		this.usernameCreator = usernameCreator;
		this.dbController = dbController;

		rounds = new Round[20];
		currentRoundID = 1;
		dies = new Die[90];
		favorTokens = new FavorToken[24];
		toolcards = new Toolcard[3];
		objectiveCards = new ObjectiveCard[3];
		players = new ArrayList<Player>();
		diesInSupply = new DiesInSupply();

		setupGame();
	}

	private void setupGame() {
		addToDatabase();
		addCreatorPlayer(usernameCreator);
		createRounds();
		createDies();
		createFavorTokens();
		createDiesInSupply();
	}

	private void createDiesInSupply() {
		diesInSupply.addDie(new Die(GameColor.PURPLE, 3, 1));
		diesInSupply.addDie(new Die(GameColor.YELLOW, 2, 2));
		diesInSupply.addDie(new Die(GameColor.BLUE, 6, 3));
		diesInSupply.addDie(new Die(GameColor.RED, 1, 4));
		diesInSupply.addDie(new Die(GameColor.GREEN, 5, 5));		
	}

	// Set up round objects with correct roundID, roundnr and clockwise boolean
	// according to the database table round.
	private void createRounds() {
		for (int i = 1; i <= rounds.length; i++) {
			int roundID = i;
			int roundnr = Math.round(((float) roundID / 2));
			boolean clockwise = false;
			if (roundID % 2 != 0) {
				clockwise = true;
			}

			rounds[i - 1] = new Round(roundID, roundnr, clockwise);
		}
	}

	// Set up 90 dies to be created in the game.
	private void createDies() {
		// to-do: logic to create 90 dies that can be used in the game.
		// Die die = new Die(color, eyesCount, dieID);
	}

	// Create 24 favortokens to be used in the game.
	private void createFavorTokens() {
		for (int i = 0; i < favorTokens.length; i++) {
			int idToken = i + 1;
			favorTokens[i] = new FavorToken(idToken, idGame, dbController);
		}
	}

	// Add the creator of the game to the players of the game.
	private void addCreatorPlayer(String usernameCreator) {
		// to-do: add check if username exists.
		
		boolean isCreator = true;
		GameColor privateObjectiveCardColor = getObjectiveCardColor();
		Player creator = new Player(usernameCreator, isCreator, idGame, privateObjectiveCardColor, dbController);
		players.add(creator);
	}

	// Add / invite a new player to the game.
	public void invitePlayer(String username) {
		// to-do: add check if username exists.
		
		// Check if there is room for more players.
		if (players.size() < 4) {
			GameColor privateObjectiveCardColor = getObjectiveCardColor();
			Player newPlayer = new Player(username, false, idGame, privateObjectiveCardColor, dbController);
			players.add(newPlayer);
		}
	}

	// Gives an available GameColor to be used as the private objectivecard color.
	private GameColor getObjectiveCardColor() {
		// to-do: remember which gameColors have been used so nobody has the same
		// private objecticard color;
		
		Random rand = new Random(); // instance of random class
		int int_random = rand.nextInt(5);
		String s = "";
		
		switch (int_random) {
		case 0:
			if (blueCounter < 1) {
				s = "BLUE";
				System.out.println("blue");
				blueCounter++;
			} else {
				int_random = rand.nextInt(5);
			}
			break;

		case 1:
			if (redCounter < 1) {
				s = "RED";
				System.out.println("red");
				redCounter++;
			} else {
				int_random = rand.nextInt(5);
			}
			break;

		case 2:
			if (greenCounter < 1) {
				s = "GREEN";
				System.out.println("green");
				greenCounter++;
			} else {
				int_random = rand.nextInt(5);
			}
			break;

		case 3:
			if (yellowCounter < 1) {
				s = "YELLOW";
				System.out.println("yellow");
				yellowCounter++;
			} else {
				int_random = rand.nextInt(5);
			}
			break;

		case 4:
			if (purpleCounter < 1) {
				s = "PURPLE";
				System.out.println("purple");
				purpleCounter++;
			} else {
				int_random = rand.nextInt(5);
			}
			break;

		default:
			break;

		}
	
		
		return GameColor.valueOf(s); // this needs to be changed later!
	}

	public void setNextRound() {
		currentRoundID++;
	}

	// Gets an available gameID and then adds a new row to the game table in the
	// database.
	private void addToDatabase() {
		// Get an available gameID
		ResultSet rs = dbController.doQuery("SELECT idgame FROM game ORDER BY idgame DESC LIMIT 1;");
		try {
			while (rs.next()) {
				int newGameID = rs.getInt(1) + 1;
				
				boolean increasingID = true;
				while (increasingID) {
					// Add a new row to the game table.
					String query = "INSERT INTO game VALUES (" + newGameID + ",NULL,NULL,CURRENT_TIMESTAMP);";
					int result = dbController.doUpdateQuery(query);
					if (result == 1) {
						increasingID = false;
						idGame = newGameID;
						System.out.println(getClass() + " - New game created with id " + idGame); // for testing purposes
					} else {
						newGameID++;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong while adding a new game to the database.");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public DiesInSupply getDiesInSupply() {
		return diesInSupply;
	}
}
