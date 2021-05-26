package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class FavorTokensInUseView extends Pane{
	
	
	
	public FavorTokensInUseView(String Username, int favortokenAmount) {
		
		Label label = new Label(Username);
		
		label.setTextFill(Color.BLUE);
		label.setFont(new Font("Arial", 25));
		label.setStyle("-fx-font-weight: bold");
		label.setTranslateY(200);
		label.setTranslateX(470);
		
		FavortokenView favortokenView = new FavortokenView(260,500);
		
		this.getChildren().addAll(label);
		
		int counter =0;
		int width =410;
		
		
		while (counter < favortokenAmount) {
			
			width = width +20;
			
			System.out.println("test");
			this.getChildren().add(new FavortokenView (width, 260));
			
			counter++;
		
		}
		
		
		
		
		
	}

}
