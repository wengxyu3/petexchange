package src;

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
//		this.setTop();
		this.setCenter(new BulletinPane(account, fileName));
	}
}
