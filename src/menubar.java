package src;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import src.MainPane;


public class menubar extends MenuBar{
	menubar(){
	}
	public void menubarsetup(ArrayList<String> availablefiles, MainPane holding) {
		Menu chatlist = new Menu("chats");
		for(int i = 0; i<availablefiles.size(); i++) {
			chatlist.getItems().add(new MenuItem(availablefiles.get(i)));
		}
		for(int i=0; i<chatlist.getItems().size();i++) {
			int e=i;
			chatlist.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					holding.bulletinPaneSetup(availablefiles.get(e));
				}
			});
			this.getMenus().add(chatlist);
		}
	}
}
