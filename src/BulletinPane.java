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
	// ArrayList<Post> posts;
	GridPane postPane;
	ArrayList<PostHUD> postHUDs;

	BulletinPane() {
		// TODO replace with bullitenBoard stuff
		postHUDs = new ArrayList<>();
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
				updateScene(new Post(postHUDs.size(), "a", textAddField.getText(), LocalDateTime.now()));
				textAddField.clear();
			}
		});

		this.setCenter(postScrollPane);
		this.setBottom(textAddField);
	}

	void updateScene(Post input) {
		// posts.add(input);
		// creates new postHUD with displayIndex of the last postHUD
		// TODO set the displayIndex to the index of the last VISIBLE postHUD
		PostHUD postHUD1 = null;
//		try {
//			postHUD = new PostHUD(input, postHUDs.get(postHUDs.size() - 1).getDisplayIndex() + 1);
//		} catch (Exception e) {
//			postHUD = new PostHUD(input, 0);
//		}
		if (postHUDs.size() == 0)
			postHUD1 = new PostHUD(input, 0);
		else
			for (int i = postHUDs.size() - 1; i >= 0; i--)
				if (!postHUDs.get(i).post.getDeleteFlag()) {
					postHUD1 = new PostHUD(input, postHUDs.get(i).getDisplayIndex() + 1);
					break;
				} else if (i == 0)
					postHUD1 = new PostHUD(input, 0);
		PostHUD postHUD = postHUD1;

		postHUD.deleteButton.setOnAction(e -> {
			// deletes postHUD
			// TODO
			postHUDs.get(postHUDs.indexOf(postHUD)).post.setDeleteFlag(true);
			postPane.getChildren().remove(postHUD.getDisplayIndex());
			for (int i = postHUDs.indexOf(postHUD) + 1; i < postHUDs.size(); i++)
				postHUDs.get(i).displayIndexMinus1();
			System.out.print("deleted " + postHUD.getDisplayIndex());
		});
		postHUDs.add(postHUD);
		postPane.add(postHUD, 0, postHUD.getDisplayIndex());

	}
}
