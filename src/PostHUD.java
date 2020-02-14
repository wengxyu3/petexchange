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

	PostHUD(Post inputPost) {
		super();
		post = inputPost;
		// parentPane = parentInput;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

		String toPost = post.getUsername() + "    " + post.getDateTime().format(formatter) + "\n" + post.getMessage();

		TextArea postText = new TextArea();

		postText.setText(toPost);
		postText.setEditable(false);
		postText.setWrapText(true);

		this.setCenter(postText);

		deleteButton = new Button("Delete");
		this.setRight(deleteButton);

	}

	PostHUD(Post inputPost, int indexInput) {
		super();
		post = inputPost;
		// parentPane = parentInput;
		displayIndex = indexInput;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

		String toPost = post.getUsername() + "    " + post.getDateTime().format(formatter) + "\n" + post.getMessage();

		TextArea postText = new TextArea();

		postText.setText(toPost);
		postText.setEditable(false);
		postText.setWrapText(true);

		this.setCenter(postText);

		deleteButton = new Button("Delete");
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
