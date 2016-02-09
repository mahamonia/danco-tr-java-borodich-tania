package com.danco.training.controller.workmenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IWorkingMenu;
import com.danco.training.controller.item.itemmenu.Item;
import com.danco.training.controller.menu.Menu;


public class WorkingMenu implements IWorkingMenu {

	private Menu currentMenu;
	private Item currentItemMenu;
	private Menu mainMenu;

	private static final Logger LOGGER = LogManager
			.getLogger(WorkingMenu.class);

	public WorkingMenu() {
	}

	@Override
	public void workMenu(Socket socket) {
		
		MenuBuilder builder = new MenuBuilder(socket);
		mainMenu = builder.buildMenu();

		currentMenu = mainMenu;
		try {
			while (currentMenu != null) {
				currentMenu.display();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(System.in));

				String line = reader.readLine(); // read what input the user
				int choiceItem = Integer.parseInt(line);// translate to the item

				// search for item in the menu list
				currentItemMenu = currentMenu.getMenuList().get(choiceItem - 1);
				currentMenu = currentItemMenu.work();// item menu is executed
														// and return menu
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally{
			builder.processing.processingClose();
		}
		
	}
}
