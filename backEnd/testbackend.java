package backEnd;
import java.time.LocalDateTime;
public class testbackend{
	public static void main(String[] args) {
		LocalDateTime current = LocalDateTime.now();
		bulletinboard j = new bulletinboard("testbulletinboard1");
		j.displayPosts();
		j.deletePost(5, "alice");
		j.deletePost(6, "charles");
		j.addPost(new Post(j.returnID(), "david", "test5", current));
		j.displayPosts();
		j.saveBulletin();
	}
}