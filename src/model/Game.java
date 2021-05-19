package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DatabaseController;

public class Game {
	
	private DatabaseController dbController;

	private int idGame;

	private FavorToken[] favorTokens; // 24 favor tokens per game.
	private Toolcard[] toolcards; // 3 toolcards per game.
	private ObjectiveCard[] objectiveCards; // 3 public objective cards per game.
	private ArrayList<Player> players; // 1 to 4 players per game
	private DiesInSupply diesInSupply;

	private String usernameCreator;
	
	// Constructor to load an existing game.
	public Game(int idGame, DatabaseController dbController) {
		this.idGame = idGame;
		this.dbController = dbController;

		favorTokens = new FavorToken[24];
		toolcards = new Toolcard[3];
		objectiveCards = new ObjectiveCard[3];
		players = new ArrayList<Player>();
		diesInSupply = new DiesInSupply();
		
		loadGame();
	}

	// Constructor to create a new game.
	public Game(String usernameCreator, DatabaseController dbController) {
		this.usernameCreator = usernameCreator;
		this.dbController = dbController;

		favorTokens = new FavorToken[24];
		toolcards = new Toolcard[3];
		objectiveCards = new ObjectiveCard[3];
		players = new ArrayList<Player>();
		diesInSupply = new DiesInSupply();

		setupGame();
	}

	// Set up a new game.
	private void setupGame() {
		addToDatabase();
		addCreatorPlayer(usernameCreator);
		createDies();
		createFavorTokens();
		dbController.setRoundID(idGame, 1);
	}
	
	public void startGame() {
		createDiesInSupply();
		loadDiesInSupply();
	}
	
	// Load an existing game.
	private void loadGame() {
		loadPlayers();
		loadDiesInSupply();
	}

	private void loadDiesInSupply() {
		diesInSupply = dbController.getDiesInSupply(idGame, dbController.getRoundID(idGame));
	}

	private void createDiesInSupply() {
		int amount = players.size() * 2 + 1;
		dbController.createDiesInSupply(idGame, dbController.getRoundID(idGame), amount);
	}

	// Set up 90 dies to be created in the game.
	private void createDies() {
		dbController.createGameDies(idGame);
	}
	
	private Die[] getDies() {
		return dbController.getDies(idGame);
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
	
	private void loadPlayers() {
		players = dbController.getPlayers(idGame);
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
		return GameColor.BLUE; // this needs to be changed later!
	}

	public void setNextRound() {
		int currentRound = dbController.getRoundID(idGame);
		int nextRound = currentRound + 1;
		dbController.setRoundID(idGame, nextRound);
	}
	
	public ArrayList<String> getPlayerOrder() {
		return dbController.getPlayerOrder(idGame);
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
	
	public int getGameID() {
		return idGame;
	}
	
	public void setNextTurn() {
		
	}
}
