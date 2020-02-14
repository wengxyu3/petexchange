package src;

import java.time.LocalDateTime;
import java.util.ArrayList;

import backEnd.Account;
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
	Account account;

	BulletinPane(Account inputAccount) {
		account = inputAccount;

		// TODO replace with bullitenBoard stuff
		postHUDs = new ArrayList<>();
		postPane = new GridPane();

		ScrollPane postScrollPane = new ScrollPane();
		postScrollPane.setContent(postPane);

		TextField textAddField = new TextField();
		textAddField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				// TODO replace values with username/message
				updateScene(
						new Post(postHUDs.size(), account.getUsername(), textAddField.getText(), LocalDateTime.now()));
				textAddField.clear();
			}
		});

		InfoBar infoBar = new InfoBar(account);

		this.setCenter(postScrollPane);
		this.setBottom(textAddField);

		this.setLeft(infoBar);
	}

	void updateScene(Post input) {
//		// creates new postHUD with displayIndex of the last postHUD
//		PostHUD postHUD1 = null;
//		if (postHUDs.size() == 0)
//			postHUD1 = new PostHUD(input, 0);
//		else
//			for (int i = postHUDs.size() - 1; i >= 0; i--)
//				if (!postHUDs.get(i).post.getDeleteFlag()) {
//					postHUD1 = new PostHUD(input, postHUDs.get(i).getDisplayIndex() + 1);
//					break;
//				} else if (i == 0)
//					postHUD1 = new PostHUD(input, 0);
//		PostHUD postHUD = postHUD1;
//
//		postHUD.deleteButton.setOnAction(e -> {
//			// deletes postHUD
//			// TODO
//			postHUDs.get(postHUDs.indexOf(postHUD)).post.setDeleteFlag(true);
//			postPane.getChildren().remove(postHUD.getDisplayIndex());
//			for (int i = postHUDs.indexOf(postHUD) + 1; i < postHUDs.size(); i++)
//				postHUDs.get(i).displayIndexMinus1();
//			System.out.print("deleted " + postHUD.getDisplayIndex());
//		});
//		postHUDs.add(postHUD);
//		postPane.add(postHUD, 0, postHUD.getDisplayIndex());

		PostHUD postHUD = new PostHUD(input);

		postHUD.deleteButton.setOnAction(e -> {
			postHUDs.get(postHUDs.indexOf(postHUD)).post.setDeleteFlag(true);
			postPane.getChildren().remove(postHUD);
		});

		postHUDs.add(postHUD);
		postPane.add(postHUD, 0, postPane.getChildren().size());
	}
}
