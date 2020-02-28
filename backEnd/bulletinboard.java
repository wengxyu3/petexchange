package backEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class bulletinboard {
	// TODO make your post number visible on the GUI when viewing a thread
	static File f1 = new File("");
	public int postnumber = 0;

	public bulletinboard(String name) {
		f1 = new File(name + ".txt");
		if (f1.exists())
			try {
				RandomAccessFile r1 = new RandomAccessFile(f1, "rw");
				r1.seek(0);
				postnumber = Integer.parseInt(r1.readLine());
				r1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				f1.createNewFile();
				FileWriter fw1 = new FileWriter(f1, false);
				PrintWriter p1 = new PrintWriter(fw1);
				p1.println("0");
				p1.println("");
				p1.println("");
				p1.println("");
				p1.println("");
				p1.close();
			} catch (java.io.IOException exception) {
				System.out.println("File could not be created at this time");
			}
	}

	public void addPost(String text, String username) {
		// TODO Username should be automatically added in the main class by using the
		// username
		// that is logged in
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			FileWriter fw1 = new FileWriter(f1, true);
			PrintWriter p1 = new PrintWriter(fw1);
			postnumber++;
			p1.println("|");
			p1.println(postnumber);
			p1.println(username);
			p1.println(text);
			p1.println(dtf.format(now));
			p1.close();
			// overwrites the initial number to stop the postnumber from starting from 0
			RandomAccessFile f = new RandomAccessFile(f1, "rw");
			f.seek(0);
			f.writeBytes(Integer.toString(postnumber));
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void deletePost(int postnumber, String username) {
		try {
			Scanner s1 = new Scanner(f1);
			File temp = File.createTempFile("postdeletion", ".txt");
			temp.deleteOnExit();
			FileWriter fw1 = new FileWriter(temp, false);
			PrintWriter p1 = new PrintWriter(fw1);
			String linetest = "";
			String number = Integer.toString(postnumber);
			String linetest2 = "";
			String linetest3 = "";
			while (s1.hasNextLine()) {
				linetest = s1.nextLine();
				if (linetest.equals("|") && s1.hasNextLine()) {
					linetest2 = s1.nextLine();
					if (linetest2.equals(number)) {
						linetest3 = s1.nextLine();
						// username check
						if (linetest3.equals(username)) {
							// number of nextlines must be changed if we add more entries to the post

							s1.nextLine();
							s1.nextLine();
							if (s1.hasNextLine()) {
								s1.nextLine();
								p1.println("|");
							}
						} else {
							// TODO If you want you can throw an exception here in terms of password
							System.out.println("Access Denied");
							temp.delete();
							break;
						}
					} else {
						p1.println(linetest);
						p1.println(linetest2);
					}
				} else
					p1.println(linetest);
			}
			p1.close();
			// overwrites permanent file with the temporary file
			if (temp.exists()) {
				FileChannel src = new FileInputStream(temp).getChannel();
				FileChannel dest = new FileOutputStream(f1).getChannel();
				dest.transferFrom(src, 0, src.size());
				src.close();
				dest.close();
			}
			s1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
