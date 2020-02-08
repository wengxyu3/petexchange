package backEnd;

import java.time.LocalDateTime;

public class Post {
	private long postId;
	private String postUsername;
	private String postContent;
	private boolean postDeleted;
	private LocalDateTime postDate;
	Post(){
		
	}
	public Post(int id1, String username1, String message1, LocalDateTime dateTime1) {
		postId = id1;
		postUsername = username1;
		postContent = message1;
		postDate = dateTime1;
		postDeleted = false;
	}

	protected LocalDateTime getDateTime() {
		return postDate;
	}

	protected boolean getDeleteFlag() {
		return postDeleted;
	}

	protected long getId() {
		return postId;
	}

	protected String getMessage() {
		return postContent;
	}

	protected String getUsername() {
		return postUsername;
	}

	protected void replyPost() {
		// TODO
	}

	protected void setDeleteFlag(boolean toSet) {
		postDeleted = toSet;
	}
	
}
