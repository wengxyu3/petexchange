package src;

import java.time.format.DateTimeFormatter;

import backEnd.Post;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class PostHUD extends BorderPane {
	Post post;
	private int displayIndex;
	// Pane parentPane;
	Button deleteButton;

	PostHUD(Post inputPost, int indexInput) {
		super();
		post = inputPost;
		// parentPane = parentInput;
		displayIndex = indexInput;
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
		String toPost = post.getUsername() + "    " + post.getDateTime().format(formatter) + "\n" + post.getMessage()
				+ displayIndex;

		TextArea postText = new TextArea();

		postText.setText(toPost);
		postText.setEditable(false);
		postText.setWrapText(true);

		this.setCenter(postText);

		deleteButton = new Button("Delete");
//		deleteButton.setOnAction(e -> {
//			// TODO fix this
//			parentPane.getChildren().remove(displayIndex);
//			System.out.print("deleted " + displayIndex);
//		});
		this.setRight(deleteButton);

	}

	void displayIndexMinus1() {
		displayIndex--;
	}

	int getDisplayIndex() {
		return displayIndex;
	}

	void setDisplayIndex(int input) {
		displayIndex = input;
	}
}
