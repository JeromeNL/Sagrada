package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ChoosePatternCardModel;


public class ChoosePatternCardController {
	
	private int counterRand;
	private ArrayList<ChoosePatternCardModel> patternCards;
	private DatabaseController dbController;
	

	public ChoosePatternCardController(DatabaseController dbController) {
		this.dbController = dbController;
		// query kiest 1 random van de 24 van idpatterncard ALS de volgende kaart
		// NIET dezelfde difficulty heeft -> kies
		patternCards = new ArrayList<ChoosePatternCardModel>();

		ResultSet rs = dbController
				.doQuery("SELECT * " + "FROM patterncard " + "GROUP BY difficulty " + "ORDER BY RAND()");

		try {
			counterRand = 0;
			while (rs.next()) {
				counterRand += 1;
				int idpatterncard = rs.getInt("idpatterncard");
				System.out.println("idpatterncard " + rs.getInt("idpatterncard"));

				int difficulty = rs.getInt("difficulty");
				System.out.println("difficulty " + rs.getInt("difficulty"));

				System.out.println(counterRand);

				ChoosePatternCardModel newChoosePatternCardModel = new ChoosePatternCardModel(idpatterncard, difficulty, counterRand);
				patternCards.add(newChoosePatternCardModel);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<ChoosePatternCardModel> getPatternCard(){
		return patternCards;
	}


}
