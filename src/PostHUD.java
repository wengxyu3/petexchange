package src;

import java.time.format.DateTimeFormatter;

import backEnd.Post;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class PostHUD extends GridPane {
	Post post;
	// Pane parentPane;
	Button deleteButton;

	PostHUD(Post inputPost, String username) {
		//The username string is not the username of the post, its the accessor's username
		super();
		post = inputPost;
		// parentPane = parentInput;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

		String toPost = post.getMessage();

		TextArea postText = new TextArea();
		Label usernameDisplay = new Label(post.getUsername());
		Label dateDisplay = new Label(post.getDateTime().format(formatter));
		Label idDisplay = new Label("#"+ Long.toString(post.getId()));
		postText.setText(toPost);
		postText.setEditable(false);
		postText.setWrapText(true);
		
		deleteButton = new Button("Delete");
		this.add(usernameDisplay, 0, 0);
		this.add(dateDisplay, 1, 0);
		this.add(postText, 0, 1);
		this.add(idDisplay, 2, 0);
		if(username.equals(post.getUsername())){
			this.add(deleteButton,2,1);
		}
		deleteButton.setOnAction(e -> {
			inputPost.delete(true, username);
		});
	}

}
