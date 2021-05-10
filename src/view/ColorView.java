package view;

import javafx.scene.paint.Color;
import model.GameColor;

public class ColorView {

	public static Color toFXColor(GameColor gameColor) {
		Color color = null;

		if (gameColor == GameColor.BLUE) { //
			color = Color.LIGHTBLUE;
		} else if (gameColor == GameColor.YELLOW) {  //
			color = Color.LIGHTYELLOW;
		} else if (gameColor == GameColor.PURPLE) {
			color = Color.MEDIUMPURPLE;
		} else if (gameColor == GameColor.GREEN) {
			color = Color.LIGHTGREEN;
		} else if (gameColor == GameColor.RED) { //
			color = Color.INDIANRED;
		}

		return color;
	}
	
	public static GameColor toGameColor(Color color) {
		GameColor gameColor = null;		
		if (color == Color.INDIANRED) {
			gameColor = GameColor.RED;
			
		} else if (color == Color.LIGHTBLUE) {
			gameColor = GameColor.BLUE;
			
		} else if (color == Color.LIGHTYELLOW) {
			gameColor = GameColor.YELLOW;
			
		} else if (color == Color.MEDIUMPURPLE) {
			gameColor = GameColor.PURPLE;
			
		} else if (color == Color.LIGHTGREEN) {
			gameColor = GameColor.GREEN;
		}
		return gameColor;
	}

}
