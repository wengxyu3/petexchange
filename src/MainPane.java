package src;

import java.util.Arrays;

import backEnd.Account;
import javafx.scene.layout.BorderPane;

public class MainPane extends BorderPane {
	Account account;
	InfoBar infoBar;

	MainPane(Account inputAccount) {
		account = inputAccount;
		infoBar = new InfoBar(account);
		this.setLeft(infoBar);
		bulletinPaneSetup("test");
	}

	void bulletinPaneSetup(String fileName) {
		this.setCenter(new BulletinPane(account, fileName));
	}

	void dmSetup(Account toDM) {
		String[] sortArray = new String[] { account.getUsername(), toDM.getUsername() };
		Arrays.sort(sortArray);
		bulletinPaneSetup(sortArray[0] + "_" + sortArray[1]);
	}
}
