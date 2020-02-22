package src;

import java.time.format.DateTimeFormatter;

import backEnd.Post;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PostHUD extends GridPane {
	Post post;
	// Pane parentPane;
	Button deleteButton;

	PostHUD(Post inputPost, String username) {
		//The username string is not the username of the post, its the accessor's username
		super();
		post = inputPost;
		// parentPane = parentInput;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");

		String toPost = post.getMessage();

		Label postText = new Label(toPost);
		Label display = new Label(post.getUsername() + "  " + post.getDateTime().format(formatter) + "   #" +Long.toString(post.getId()) );
		display.setFont(Font.font("calibri", 16));
//		Label dateDisplay = new Label(post.getDateTime().format(formatter));
//		dateDisplay.setFont(Font.font("calibri", 24));
//		Label idDisplay = new Label("#"+ Long.toString(post.getId()));
//		idDisplay.setFont(Font.font("calibri", 24));
//		Text t = new Text(toPost);
//		postText.setEditable(false);
//		postText.setWrapText(true);
//		postText.setPrefHeight(50);
//		t.setWrappingWidth(500);
		postText.setPrefWidth(250);
		postText.setWrapText(true);
		deleteButton = new Button("Delete");
		this.add(display, 0, 0);
//		this.add(dateDisplay, 1, 0);
		this.add(postText, 0, 1);
//		this.add(idDisplay, 2, 0);
		if(username.equals(post.getUsername())){
			this.add(deleteButton,1,0);
		}
		deleteButton.setOnAction(e -> {
			inputPost.delete(true, username);
		});
	}

}
