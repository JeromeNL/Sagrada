package imageChooser;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PublicObjectiveCardImage extends Pane {

	private int widthCard = 200;
	private int heightCard = 300;
	private String imageURL;
	private String combinedURL;
	private int imageNumber;

	public PublicObjectiveCardImage(int newImageNumber) {
		super();
		
		imageNumber = newImageNumber;
		combinedURL = "/public_objective_" + imageNumber + ".png";
		imageURL = combinedURL.toString();
		addCards();

	}

	public void addCards() {

		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
		ImageView imageView = new ImageView(toolCardImage);
		imageView.setFitHeight(heightCard);
		imageView.setFitWidth(widthCard);
		getChildren().addAll(imageView);

	}

}
