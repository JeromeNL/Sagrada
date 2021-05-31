package model;

public class ChoosePatternCardModel {
	
	private int idPatternCard;
	private int difficulty;
	private int counterRand;
	
	
	public ChoosePatternCardModel(int idPatternCard, int difficulty, int counterRand) {
		this.idPatternCard = idPatternCard;
		this.difficulty = difficulty;
		this.counterRand = counterRand;
		
	}
	
	public int getIdPatternCard() {
		return idPatternCard;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public int getCounterRand() {
		return counterRand;
	}

}
