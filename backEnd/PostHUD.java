package backEnd;

import java.time.format.DateTimeFormatter;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class PostHUD extends GridPane {
	Post post;

	public PostHUD(Post inputPost) {
		super();
		post = inputPost;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		/*
		 * TextField dateTimeField = new
		 * TextField(post.getDateTime().format(formatter)); TextField senderField = new
		 * TextField(post.getUsername()); TextField messageField = new
		 * TextField(post.getMessage());
		 *
		 * // TODO add first row HBox firstRowPane = new HBox();
		 * firstRowPane.getChildren().addAll(senderField, dateTimeField);
		 *
		 * HBox secondRowPane = new HBox();
		 * secondRowPane.getChildren().add(messageField);
		 *
		 * this.add(firstRowPane, 0, 0); this.add(secondRowPane, 0, 1);
		 */
		String toPost = post.getUsername() + "    " + post.getDateTime().format(formatter) + "\n" + post.getMessage();

		TextArea postText = new TextArea();

		postText.setText(toPost);
		postText.setEditable(false);
		postText.setWrapText(true);

		this.add(postText, 0, 0);

	}
}
