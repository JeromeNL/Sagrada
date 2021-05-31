package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FavortokenView extends Circle {

	public FavortokenView(int x, int y) {
		
		this.setRadius(15);
		this.setFill(Color.PINK);
		setTranslateY(y);
		setTranslateX(x);
		setStroke(Color.BLACK);
		setStrokeWidth(5);
		
		
	}
	
	
	
}
