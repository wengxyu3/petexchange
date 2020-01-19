package petexchange;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
public class OwnerList{
	private ArrayList<String> OwnerName = new ArrayList<String>();
	File f1 = new File("");
	OwnerList(String filename) {
		f1 = new File("/Users/cameronxu/Documents/Java/"+filename+".txt");
		try {
			Scanner fr1 = new Scanner(f1);
			while(fr1.hasNextLine()) {
				OwnerName.add(fr1.nextLine());
			}
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
	public Object[] getNames(){
		return OwnerName.toArray();
	}
	public boolean CheckSafe(String ownername) {
		if(OwnerName.contains(ownername)) {
			return false;
		}
		else {
			return true;
		}
	}
	public void Add(String name) {
		if(CheckSafe(name)) {
	//	TODO	Owner newowner = new Owner(name, password, true);
		try {
			FileWriter fw1 = new FileWriter(f1, true);
			PrintWriter fo1 = new PrintWriter(fw1);
			fo1.println(name);
			fo1.close();
		} catch (IOException e) {
			// TODO Leave alone
		}
		}
		else {
			System.out.println("Name already exists");
		}
	}
}