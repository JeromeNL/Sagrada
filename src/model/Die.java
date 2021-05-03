package model;

import javafx.scene.paint.Color;

public class Die {

	private final int dieID;
	private final Color color;
	private final int eyesCount;
	
	public Die(Color color, int eyesCount, int dieID) {
		this.color = color;
		this.eyesCount = eyesCount;
		this.dieID = dieID;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getDieID() {
		return dieID;
	}
	
	public int getEyesCount() {
		return eyesCount;
	}
}
