package imageChooser;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PrivateObjectiveCardImage extends Pane {


	private int widthCard = 419;
	private int heightCard = 606;
	private String imageURL;
	private String combinedURL;
	private String imageColor;

	public PrivateObjectiveCardImage(String newImageColor) {
		super();
		
		imageColor = newImageColor;
		combinedURL = "/private_objective_" + imageColor.toLowerCase() + ".png";
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
