package src;

import java.time.LocalDateTime;
import java.util.ArrayList;

import backEnd.Post;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class BulletinPane extends BorderPane {
	// TODO replace this with BulletinBoard
	ArrayList<Post> posts;
	GridPane postPane;

	BulletinPane() {
		// TODO replace with bullitenBoard stuff
		posts = new ArrayList<>();
		postPane = new GridPane();

		updateScene(new Post(0, "abc", "afdfsfds", LocalDateTime.now()));
		updateScene(new Post(1, "cdf", "dfdsfdasfas", LocalDateTime.now()));

//		for (int i = 0; i < posts.size(); i++)
//			updateScene(posts.get(i));

		ScrollPane postScrollPane = new ScrollPane();
		postScrollPane.setContent(postPane);

		TextField textAddField = new TextField();
		textAddField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				// TODO replace values with username/message
				updateScene(new Post(posts.size(), "a", textAddField.getText(), LocalDateTime.now()));
				textAddField.clear();
			}
		});

		this.setCenter(postScrollPane);
		this.setBottom(textAddField);
	}

	void updateScene(Post input) {
		posts.add(input);
		postPane.add(new PostHUD(input, input.getId(), postPane), 0, posts.size());
	}
}
