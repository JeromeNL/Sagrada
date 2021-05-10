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
	
	// Get the String representation of a color to use when talking to the database.
	public String getStringColor() {
		String stringColor = "";
		if (color.equals(Color.INDIANRED)) {
			stringColor = "red";
		} else if (color.equals(Color.LIGHTBLUE)) {
			stringColor = "blue";
		} else if (color.equals(Color.LIGHTYELLOW)) {
			stringColor = "yellow";
		} else if (color.equals(Color.MEDIUMPURPLE)) {
			stringColor = "purple";
		} else if (color.equals(Color.LIGHTGREEN)) {
			stringColor = "green";
		}
		return stringColor;
	}

	public int getEyesCount() {
		return eyesCount;
	}
}
