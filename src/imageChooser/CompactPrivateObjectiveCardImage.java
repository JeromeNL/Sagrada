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
		combinedURL = "/Images/In Game Objective Private/" + imageColor + ".png";
		imageURL = combinedURL.toString();
		addCards();
	}

	public void addCards() {
		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
		ImageView imageView = new ImageView(toolCardImage);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(90);

		getChildren().addAll(imageView);
	}
	
	public void hide() {
		getChildren().clear();
	}

}
