package backEnd;

import java.time.LocalDateTime;

public class Post {
	private int id;
	private String username;
	private String message;
	private boolean deleteFlag;
	private LocalDateTime dateTime;

	public Post(int id1, String username1, String message1, LocalDateTime dateTime1) {
		id = id1;
		username = username1;
		message = message1;
		dateTime = dateTime1;
		deleteFlag = false;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public boolean getDeleteFlag() {
		return deleteFlag;
	}

	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public String getUsername() {
		return username;
	}

	protected void replyPost() {
		// TODO
	}

	protected void setDeleteFlag(boolean toSet) {
		deleteFlag = toSet;
	}
}
