package imageChooser;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CompactPublicObjectiveCardImage extends Pane {


	private String imageURL;
	private String combinedURL;
	private int imageNumber;

	public CompactPublicObjectiveCardImage(int newImageNumber) {
		super();

		imageNumber = newImageNumber;
		combinedURL = "/ingame_public_" + imageNumber + ".png";
		imageURL = combinedURL.toString();
		addCards();
		
	}

	public void addCards() {

		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
		ImageView imageView = new ImageView(toolCardImage);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(90);
		getChildren().addAll(imageView);
		setMinHeight(imageView.getFitHeight());

	}

}
