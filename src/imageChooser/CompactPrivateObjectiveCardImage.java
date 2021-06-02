package imageChooser;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CompactPrivateObjectiveCardImage extends Pane {

	private String imageURL;
	private String combinedURL;
	private String imageColor;

	public CompactPrivateObjectiveCardImage(String newImageColor) {
		super();
		
		imageColor = newImageColor;
		combinedURL = "/Images/Compact Private Objectives GameView/" + imageColor + ".png";
		imageURL = combinedURL.toString();
		addCards();
	}

	public void addCards() {
//		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
		ImageView imageView = new ImageView(toolCardImage);
		imageView.setFitWidth(350);
		imageView.setPreserveRatio(true);

		getChildren().addAll(imageView);

	}
	
	public void hide() {
		getChildren().clear();
	}

}
