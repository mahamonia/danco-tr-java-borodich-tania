package com.danco.training.controller.workmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.danco.training.controller.item.itemmenu.Item;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class WorkingMenu {

	private Menu currentMenu; // текущее меню
	private Item currentItemMenu;

	private Menu mainMenu;

	public WorkingMenu() {

	}

	public void workMenu() {
		ServiceAdmin admin = ServiceAdmin.getInstance();
		admin.initData();
		MenuBuilder builder = new MenuBuilder(admin);
		mainMenu = builder.buildMenu();

		currentMenu = mainMenu;

		while (currentMenu != null) {
			currentMenu.display();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				String line = reader.readLine(); // read what input the user
				int choiceItem = Integer.parseInt(line);// translate to the item

				// search for item in the menu list
				currentItemMenu = currentMenu.getMenuList().get(choiceItem - 1);
				currentMenu = currentItemMenu.work();// item menu is executed
														// and return menu

			} catch (Exception e) {
				e.printStackTrace();
			} 
		}admin.saveData();
	}
}
