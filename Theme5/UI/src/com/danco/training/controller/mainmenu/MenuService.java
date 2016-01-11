package com.danco.training.controller.mainmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.danco.training.controller.factory.itemmenu.AbstractFacoryItemMenu;
import com.danco.training.controller.factory.itemmenu.AbstractItem;
import com.danco.training.controller.factory.mainmenu.AbstractMenu;
import com.danco.training.view.menudispaly.DisplayMenuService;
import com.danco.training.view.menudispaly.IDisplayMenu;

public class MenuService extends AbstractMenu {
	private AbstractFacoryItemMenu factoryItemMenu;
	private IDisplayMenu display;

	private Map<Integer, AbstractItem> itemsMenuService;
	private AbstractItem itemMenu;

	private final int ITEM_EXIT = 3;
	private boolean isExit = false;

	public MenuService(AbstractFacoryItemMenu factoryItemMenu) {
		this.factoryItemMenu = factoryItemMenu;
		this.display = new DisplayMenuService();

		itemsMenuService = new HashMap<Integer, AbstractItem>();
		itemMenu = new AbstractItem() {
			@Override
			public void work() {
				isExit = true;
			}
		};
		itemsMenuService.put(ITEM_EXIT, itemMenu);

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
				for (int key : itemsMenuService.keySet()) {
					if (key == choiceItem) {
						itemMenu = itemsMenuService.get(key);
					} else
						itemMenu = null;
				}
				if (itemMenu == null) {
					itemMenu = factoryItemMenu.createItemMenu(choiceItem);
					itemsMenuService.put(choiceItem, itemMenu);
				}
				itemMenu.work();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
