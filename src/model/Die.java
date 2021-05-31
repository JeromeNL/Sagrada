package model;

import javafx.scene.paint.Color;

public class Die {

	private final int dieID;
	private final GameColor color;
	private final int eyesCount;

	public Die(GameColor color, int eyesCount, int dieID) {
		this.color = color;
		this.eyesCount = eyesCount;
		this.dieID = dieID;
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
}
