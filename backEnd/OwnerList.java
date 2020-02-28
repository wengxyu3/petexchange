package backEnd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OwnerList {
	private ArrayList<String> owners = new ArrayList<>();
	File f1 = new File("");

	public OwnerList(String filename) {
		switch (OSUtility.getOS()) {
		case WINDOWS:
			f1 = new File(filename + ".txt");
		case MAC: // TODO see what happens with MacOS file stuff
			f1 = new File(filename + ".txt");
		default:
			// TODO add default handler
			break;
		}

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

	public boolean CheckSafe(String ownername) {
		for (int i = 0; i < owners.size(); i++) {
			System.out.println(owners.get(i));
			if (ownername.toUpperCase().equals(owners.get(i).toUpperCase()))
				return false;
		}
		return true;
	}

	public Object[] getNames() {
		return owners.toArray();
	}

	@SuppressWarnings("unused")
	public void Register(String name, String password) throws FileExistsException {
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
				owners.add(name);
			} catch (IOException e) {
				// TODO Leave alone
			}
		} else
			throw new FileExistsException();
	}
}