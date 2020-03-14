package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import backEnd.Account;
import backEnd.bulletinboard;
import javafx.scene.layout.BorderPane;

public class MainPane extends BorderPane {
	Account account;
	InfoBar infoBar;
	BulletinPane bulletin;
	menubar menu;
	MainPane(Account inputAccount) {
		account = inputAccount;
		infoBar = new InfoBar(account);
		this.setLeft(infoBar);
		bulletinPaneSetup("testbulletinboard1");
	}

	void bulletinPaneSetup(String fileName) {
		bulletin = new BulletinPane(account, fileName);
		this.setCenter(bulletin);
	}
	public ArrayList<String> listBulletin(String filepath){
		//should take a folder where all bulletin boards are stored, maybe we can save it as a 
		//.bulletinboard extension in the future
		File folder = new File(filepath);
		ArrayList<String> toreturn = new ArrayList<String>();
		for(int i=0; i<folder.list().length; i++) {
			if(folder.list()[i].contains(".txt")) {
				toreturn.add((folder.list()[i]));
			}
		}
		return toreturn;
	}
	void dmSetup(Account toDM) {
		String[] sortArray = new String[] { account.getUsername(), toDM.getUsername() };
		Arrays.sort(sortArray);
		bulletinPaneSetup(sortArray[0] + "_" + sortArray[1]);
	}
	void menuBarSetup(String filepath) {
		menu = new menubar();
		menu.menubarsetup(listBulletin(filepath), this);
		this.setTop(menu);
	}
}
