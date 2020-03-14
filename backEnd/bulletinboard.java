package backEnd;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class bulletinboard{
	public int postnumber = 0;
	public String title="";
	public ArrayList<Post> postcontents = new ArrayList<Post>();
	// TODO make your post number visible on the GUI when viewing a thread
	static File f1 = new File("");
	public bulletinboard(String name) {
		title= name;
		f1 = new File("bulletinstorage/"+name+".txt");
		if(f1.exists()) {
			String holds1 = "";
			try {
				Scanner s1 = new Scanner(f1);
				while(s1.hasNextLine()) {
					Post temppost = new Post();
					if(s1.nextLine().equals("|")) {
						temppost = new Post(Integer.parseInt(s1.nextLine()), s1.nextLine(), s1.nextLine(), LocalDateTime.parse(s1.nextLine()));
					}
					holds1 = s1.nextLine();
					if(holds1.equalsIgnoreCase("true")) {
						temppost.setDeleteFlag(true);
						postcontents.add(temppost);
					}
					else {
						temppost.setDeleteFlag(false);
						postcontents.add(temppost);
					}
				}
				postnumber = postcontents.size();
				s1.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
			f1.createNewFile();
		}
		catch(java.io.IOException exception) {
			exception.printStackTrace();
			System.out.println("File could not be created at this time");
			}
		}
	}
	public void addPost(Post toadd) {
		// TODO Username should be automatically added in the main class by using the username
		// that is logged in
		postcontents.add(toadd);
	}
	public void deletePost(Post todelete, String username) {
		for(int i =0; i< postcontents.size(); i++) {
			if(postcontents.get(i).getId() == todelete.getId()) {
				if(postcontents.get(i).getUsername().equals(username)) {
					postcontents.get(i).setDeleteFlag(true);
				}
				else {
					System.out.println("Access Denied");
				}
			}
		}
	}
	public void deletePost(long id, String username) {
		for(int i =0; i< postcontents.size(); i++) {
			if(postcontents.get(i).getId() == id){
				if(postcontents.get(i).getUsername().equals(username)) {
					postcontents.get(i).setDeleteFlag(true);
				}
				else {
					System.out.println("Access Denied");
				}
			}
		}
	}
	public long returnID() {
		if(postcontents.size()==0) {
			return 1;
		}
		return postcontents.get(postcontents.size()-1).getId()+1;
	}
	public void saveBulletin() {
		try {
			PrintWriter pw1 = new PrintWriter(f1);
			for(int i=0; i<postcontents.size(); i++) {
				pw1.println("|");
				pw1.println(postcontents.get(i).getId());
				pw1.println(postcontents.get(i).getUsername());
				pw1.println(postcontents.get(i).getMessage());
				pw1.println(postcontents.get(i).getDateTime());
				pw1.println(postcontents.get(i).getDeleteFlag());
			}
			pw1.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Post> listPosts(){
		ArrayList<Post> e = new ArrayList<Post>();
		for(int i=0; i<postcontents.size(); i++) {
			if(postcontents.get(i).getDeleteFlag() == false) {
				e.add(postcontents.get(i));
			}
		}
		return e;
	}
	public void displayPosts(){
		for(int i=0; i<postcontents.size(); i++) {
			if(postcontents.get(i).getDeleteFlag() == false) {
				System.out.println("ID: "+postcontents.get(i).getId());
				System.out.println("Username: "+ postcontents.get(i).getUsername());
				System.out.println("Message: "+ postcontents.get(i).getMessage());
				System.out.println("Date: "+ postcontents.get(i).getDateTime());
			}
		}
	}
}
