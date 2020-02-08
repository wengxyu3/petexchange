package backEnd;

import java.io.File;
import java.util.ArrayList;

public class BulletinBoard1 {
	private ArrayList<Post> posts = new ArrayList<>();

	BulletinBoard1(String filename) {
		File boardFile = new File("");
		if (boardFile.exists()) {
			// TODO read file
		} else {
			// TODO create file
		}
	}

	protected void addPost(Post inputPost) {
		posts.add(inputPost);
	}

	protected void deletePost(Post inputPost) {
		posts.remove(inputPost);
	}

	protected Post[] getPosts() {
		return (Post[]) posts.toArray();
	}

	protected void save() {
		// TODO
	}
}
