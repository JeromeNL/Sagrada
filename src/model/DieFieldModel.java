package model;

import javafx.scene.paint.Color;

public class DieFieldModel {

	private int xPosition; // range: 1 to 5
	private int yPosition; // range: 1 to 4
	
	private int eyesCountRequirement; // 0 = no requirement
	private Color colorRequirement; // null is no requirement
	
	private int eyesCount; // 0 = no die on field
	private Color dieColor; // null = no die on field
	
	public DieFieldModel(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		dieColor = null;
		eyesCount = 0;
		colorRequirement = null;
		eyesCountRequirement = 0;
	}
	
	public DieFieldModel(int eyesCount, Color dieColor) {
		this.dieColor = dieColor;
		this.eyesCount = eyesCount;
	}
	
	public DieFieldModel(int xPosition, int yPosition, int eyesCountRequirement, Color colorRequirement) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		this.eyesCountRequirement = eyesCountRequirement;
		this.colorRequirement = colorRequirement;
		
		dieColor = null;
		eyesCount = 0;
	}
	
	public void placeDie(int eyesCount, Color dieColor) {
		this.eyesCount = eyesCount;
		this.dieColor = dieColor;
	}
	
	public void removeDie() {
		eyesCount = 0;
		dieColor = Color.WHITE;
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public int getEyesCount() {
		return eyesCount;
	}
	
	public Color getDieColor() {
		return dieColor;
	}
	
	public Color getColorRequirement() {
		return colorRequirement;
	}
	
	public int getEyesCountRequirement() {
		return eyesCountRequirement;
	}
	

}
