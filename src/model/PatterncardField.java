package model;

import javafx.scene.paint.Color;

public class PatterncardField {

	private int xPosition; // range: 1 to 5
	private int yPosition; // range: 1 to 4
	
	private int eyesCountRequirement; // 0 = no requirement
	private Color colorRequirement; // null is no requirement
	
	private Die dieOnField;
	
	public PatterncardField(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public PatterncardField(int xPosition, int yPosition, int eyesCountRequirement, Color colorRequirement) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		this.eyesCountRequirement = eyesCountRequirement;
		this.colorRequirement = colorRequirement;
	}
	
	public void placeDie(Die die) {
		this.dieOnField = die;
	}
	
	public void removeDie() {
		dieOnField = null;
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public Die getDie() {
		return dieOnField;
	}
	
	public boolean hasDie() {
		return dieOnField != null;
	}
	
	public boolean hasColorRequirement() {
		return colorRequirement != null;
	}
	
	public boolean hasEyesCountRequirement() {
		return eyesCountRequirement != 0;
	}
	
	public Color getColorRequirement() {
		return colorRequirement;
	}
	
	public int getEyesCountRequirement() {
		return eyesCountRequirement;
	}
	

}
