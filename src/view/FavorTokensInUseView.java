package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class FavorTokensInUseView extends Pane{
	
	
	
	public FavorTokensInUseView(String Username, int favortokenAmount) {
		
		Label label = new Label(Username);
		
		label.setTextFill(Color.BLACK);
		label.setFont(new Font("Arial", 25));
		label.setStyle("-fx-font-weight: bold");
		label.setTranslateY(200);
		label.setTranslateX(480);
		
		FavortokenView favortokenView = new FavortokenView(490,500);
		
		this.getChildren().addAll(label);
		
		int counter =0;
		int width =430;
		
		
		while (counter < favortokenAmount) {
			
			width = width +20;
			
			this.getChildren().add(new FavortokenView (width, 260));
			
			counter++;
		
		}
		
		
		
		
		
	}

}
