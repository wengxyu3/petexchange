package backEnd;

import java.time.LocalDateTime;

public class Post {
	private long postId;
	private String postUsername;
	private String postContent;
	private boolean postDeleted;
	private LocalDateTime postDate;

	Post() {

	}

	public Post(long id1, String username1, String message1, LocalDateTime dateTime1) {
		postId = id1;
		postUsername = username1;
		postContent = message1;
		postDate = dateTime1;
		postDeleted = false;
	}

	public void delete(boolean toSet, String username) {
		if (username.equals(postUsername))
			postDeleted = toSet;
	}

	public LocalDateTime getDateTime() {
		return postDate;
	}

	public boolean getDeleteFlag() {
		return postDeleted;
	}

	public long getId() {
		return postId;
	}

	public String getMessage() {
		return postContent;
	}

	public String getUsername() {
		return postUsername;
	}

	protected void replyPost() {
		// TODO
	}

	public void setDeleteFlag(boolean toSet) {
		postDeleted = toSet;
	}

}
