package imageChooser;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ToolcardCardImage extends Pane {

	private int widthCard = 200;
	private int heightCard = 300;
	private String imageURL;
	private String combinedURL;
	private int imageNumber;

	public ToolcardCardImage(int newImageNumber) {
		super();
		
		imageNumber = newImageNumber;
		combinedURL = "/toolcard_" + imageNumber + ".png";
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
