package com.danco.training.controller.mainmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.danco.training.controller.factory.itemmenu.AbstractFacoryItemMenu;
import com.danco.training.controller.factory.itemmenu.AbstractItem;
import com.danco.training.controller.factory.mainmenu.AbstractMenu;
import com.danco.training.view.menudispaly.DisplayMenuRoom;
import com.danco.training.view.menudispaly.IDisplayMenu;


public class MenuRoom extends AbstractMenu{
	
	private AbstractFacoryItemMenu factoryItemMenu;
	private IDisplayMenu display;

	private Map<Integer, AbstractItem> itemsMenuRoom;
	private AbstractItem itemMenu;

	public final int ITEM_EXIT = 5;
	private boolean isExit = false;


	public MenuRoom(AbstractFacoryItemMenu factoryItemMenu) {
		this.factoryItemMenu = factoryItemMenu;
		this.display = new DisplayMenuRoom();

		itemsMenuRoom = new HashMap<Integer, AbstractItem>();
		itemMenu = new AbstractItem() {
			@Override
			public void work() {
				isExit = true;
			}
		};
		itemsMenuRoom.put(ITEM_EXIT, itemMenu);
	}
	
	@Override
	public void workMenu() {
		while (!isExit) {
			display.displayMenu();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				String line = reader.readLine();
				int choiceItem = Integer.parseInt(line);
				for (int key : itemsMenuRoom.keySet()) {
					if (key == choiceItem) {
						itemMenu = itemsMenuRoom.get(key);
					}else itemMenu = null;
				}
				if (itemMenu == null) {
					itemMenu = factoryItemMenu.createItemMenu(choiceItem);
					itemsMenuRoom.put(choiceItem, itemMenu);
				}
				itemMenu.work();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		


}
