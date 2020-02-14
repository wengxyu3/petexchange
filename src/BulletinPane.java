package src;

import java.time.LocalDateTime;
import java.util.ArrayList;

import backEnd.Account;
import backEnd.Post;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import backEnd.bulletinboard;

public class BulletinPane extends BorderPane {
	// TODO replace this with BulletinBoard
	// ArrayList<Post> posts;
	ArrayList<Post> posts = new ArrayList<Post>();
	GridPane postPane;
	ArrayList<PostHUD> postHUDs = new ArrayList<PostHUD>();
	String username;
	Label userlabel;
	BulletinPane(String user, String file) {
		username = user;
		userlabel = new Label("Logged in as: "+username);
		bulletinboard bb = new bulletinboard(file);
		// TODO replace with bullitenBoard stuff
		postPane = new GridPane();
		
		ScrollPane postScrollPane = new ScrollPane();
		postScrollPane.setContent(postPane);
		
		TextField textAddField = new TextField();
		textAddField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER && !textAddField.getText().equals("")) {
				// TODO replace values with username/message
				createPost(new Post(bb.returnID(), username, textAddField.getText(), LocalDateTime.now()), bb);
				textAddField.clear();
			}
		});
		this.setTop(userlabel);
		this.setCenter(postScrollPane);
		this.setBottom(textAddField);
		refreshScene(bb);
	}
	void createPost(Post input, bulletinboard bb) {
		// creates new postHUD with displayIndex of the last postHUD
		bb.addPost(input);
		refreshScene(bb);
	}
	void removePost(Post input, bulletinboard bb) {
		bb.deletePost(input, username);
		refreshScene(bb);
	}
	void refreshScene(bulletinboard bb) {
		bb.saveBulletin();
		postHUDs.clear();
		postPane.getChildren().clear();
		for(int i=0; i<bb.listPosts().size(); i++) {
			postHUDs.add(new PostHUD(bb.listPosts().get(i), username));
		}
		for(int i=0; i<postHUDs.size(); i++) {
			postPane.add(postHUDs.get(i), 0, i);
		}
		
	}
}
