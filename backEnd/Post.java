package backEnd;

import java.time.LocalDateTime;

public class Post {
	private long id;
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

	protected LocalDateTime getDateTime() {
		return dateTime;
	}

	protected boolean getDeleteFlag() {
		return deleteFlag;
	}

	protected long getId() {
		return id;
	}

	protected String getMessage() {
		return message;
	}

	protected String getUsername() {
		return username;
	}

	protected void replyPost() {
		// TODO
	}

	protected void setDeleteFlag(boolean toSet) {
		deleteFlag = toSet;
	}
}
