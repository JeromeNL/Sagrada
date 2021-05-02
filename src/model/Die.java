package model;

import javafx.scene.paint.Color;

public class Die {

	private final Color color;
	private final int eyesCount;
	
	public Die(Color color, int eyesCount) {
		this.color = color;
		this.eyesCount = eyesCount;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getEyesCount() {
		return eyesCount;
	}
	
}
