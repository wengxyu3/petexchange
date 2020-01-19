package backEnd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OwnerList {
	private ArrayList<Account> owners = new ArrayList<>();
	File f1 = new File("");

	public OwnerList(String filename) {
		f1 = new File("/Users/cameronxu/Documents/Java/" + filename + ".txt");
		try {
			Scanner fr1 = new Scanner(f1);
			while (fr1.hasNextLine())
				owners.add(fr1.nextLine());
			fr1.close();
		} catch (IOException e) {
			try {
				f1.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void Add(String name, String password) {
		if (CheckSafe(name)) {
			try {
				Account newowner = new Account(name, password, true);
			} catch (PasswordMismatchException e) {
				// TODO Auto-generated catch block
			}
			try {
				FileWriter fw1 = new FileWriter(f1, true);
				PrintWriter fo1 = new PrintWriter(fw1);
				fo1.println(name);
				fo1.close();
			} catch (IOException e) {
				// TODO Leave alone
			}
		} else
			System.out.println("Name already exists");
	}

	public boolean CheckSafe(String ownername) {
		for (int i = 0; i < owners.size(); i++)
			if (ownername.equals(owners.get(i).getUsername()))
				return false;
		return true;
	}

	public Object[] getNames() {
		return owners.toArray();
	}
}