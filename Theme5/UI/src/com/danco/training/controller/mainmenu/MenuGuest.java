package com.danco.training.controller.mainmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.danco.training.controller.factory.itemmenu.AbstractFacoryItemMenu;
import com.danco.training.controller.factory.itemmenu.AbstractItem;
import com.danco.training.controller.factory.mainmenu.AbstractMenu;
import com.danco.training.view.menudispaly.DisplayMenuGuest;
import com.danco.training.view.menudispaly.IDisplayMenu;

public class MenuGuest extends AbstractMenu {

	private AbstractFacoryItemMenu factoryItemMenu;
	private IDisplayMenu display;

	private Map<Integer, AbstractItem> itemsMenuGuest;
	private AbstractItem itemMenu;
	
	private final int ITEM_EXIT = 7;
	private boolean isExit = false;

	public MenuGuest(AbstractFacoryItemMenu factoryItemMenu) {

		this.factoryItemMenu = factoryItemMenu;
		this.display = new DisplayMenuGuest();

		itemsMenuGuest = new HashMap<Integer, AbstractItem>();
		itemMenu = new AbstractItem() {
			@Override
			public void work() {
				isExit = true;
			}
		};
		itemsMenuGuest.put(ITEM_EXIT, itemMenu);

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
				for (int key : itemsMenuGuest.keySet()) {
					if (key == choiceItem) {
						itemMenu = itemsMenuGuest.get(key);
					}else itemMenu = null;
				}
				if (itemMenu == null) {
					itemMenu = factoryItemMenu.createItemMenu(choiceItem);
					itemsMenuGuest.put(choiceItem, itemMenu);
				}
				itemMenu.work();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
