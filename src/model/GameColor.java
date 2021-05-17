package model;

import javafx.scene.paint.Color;

public enum GameColor {
	BLUE, GREEN, PURPLE, RED, YELLOW;

	// Convert a GameColor to a JavaFX color.
	public Color toFXColor() {
		if (this == BLUE) { //
			return Color.LIGHTBLUE;
		} else if (this == YELLOW) { //
			return Color.LIGHTYELLOW;
		} else if (this == PURPLE) {
			return Color.MEDIUMPURPLE;
		} else if (this == GREEN) {
			return Color.LIGHTGREEN;
		} else if (this == RED) { //
			return Color.INDIANRED;
		}
		return null;
	}

	// Convert a JavaFX to a GameColor.
	public GameColor toGameColor(Color color) {
		if (color == Color.INDIANRED) {
			return GameColor.RED;

		} else if (color == Color.LIGHTBLUE) {
			return GameColor.BLUE;

		} else if (color == Color.LIGHTYELLOW) {
			return GameColor.YELLOW;

		} else if (color == Color.MEDIUMPURPLE) {
			return GameColor.PURPLE;

		} else if (color == Color.LIGHTGREEN) {
			return GameColor.GREEN;
		}
		return null;
	}
}
