package model;

public class Die {

	private final int dieID;
	private final GameColor color;
	private final int eyesCount;
	private int roundtrackNumber;

	public Die(GameColor color, int eyesCount, int dieID) {
		this.color = color;
		this.eyesCount = eyesCount;
		this.dieID = dieID;
	}

	public Die(GameColor color, int eyesCount, int dieID, int roundtrackNumber) {
		this.color = color;
		this.eyesCount = eyesCount;
		this.dieID = dieID;
		this.roundtrackNumber = roundtrackNumber;
	}
	
	public GameColor getColor() {
		return color;
	}

	public int getDieID() {
		return dieID;
	}

	public int getEyesCount() {
		return eyesCount;
	}
	
	public int getRoundtrackNumber(){
		return roundtrackNumber;
	}
}
