package com.danco.training.controller.mainmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.danco.training.controller.factory.mainmenu.AbstractFactoryMenu;
import com.danco.training.controller.factory.mainmenu.AbstractMenu;
import com.danco.training.view.menudispaly.DisplayMainMenu;

public class MainMenu {

	private AbstractFactoryMenu factoryMenu;
	private DisplayMainMenu display;

	private Map<Integer, AbstractMenu> itemsMainMenu;

	private AbstractMenu itemMenu;
	private final int ITEM_EXIT = 4;
	private boolean isExit = false;

	public MainMenu(AbstractFactoryMenu factoryMenu, DisplayMainMenu display) {

		this.factoryMenu = factoryMenu;
		this.display = display; 
		itemsMainMenu = new HashMap<Integer, AbstractMenu>();
		itemMenu = new AbstractMenu() {
			@Override
			public void workMenu() {
				System.exit(0);
			}
		};
		itemsMainMenu.put(ITEM_EXIT, itemMenu);

	}

	public void work() {

		while (!isExit) {
			display.displayMenu();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				String line = reader.readLine();
				int choiceItem = Integer.parseInt(line);
				for (int key : itemsMainMenu.keySet()) {
					if (key == choiceItem) {
						itemMenu = itemsMainMenu.get(key);
					}else itemMenu = null;
				}
				if (itemMenu == null) {
					itemMenu = factoryMenu.createMenu(choiceItem);
					itemsMainMenu.put(choiceItem, itemMenu);
				}
				itemMenu.workMenu();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
