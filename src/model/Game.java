package model;

import java.util.ArrayList;
import controller.DatabaseController;
import controller.GameController;

public class Game {

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
	private GameController gameController;

	private String usernameCreator;

	public Game(String usernameCreator, DatabaseController dbController) {
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
		gameController.addToDatabase();
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
		return GameColor.BLUE; // this needs to be changed later!
	}

	public void setNextRound() {
		currentRoundID++;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public DiesInSupply getDiesInSupply() {
		return diesInSupply;
	}

	public int getidGame() {
		return idGame;
	}
}
