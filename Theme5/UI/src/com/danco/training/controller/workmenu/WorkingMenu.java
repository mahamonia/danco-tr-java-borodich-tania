package com.danco.training.controller.workmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.danco.training.controller.item.itemmenu.AbstractItem;
import com.danco.training.controller.menu.AbstractMenu;


public class WorkingMenu {
	
	private AbstractMenu currentMenu; // текущее меню
	private AbstractItem currentItemMenu;
	
	private AbstractMenu mainMenu;

	public WorkingMenu(AbstractMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public void workMenu() {
	
		currentMenu = mainMenu;
		
		while (currentMenu!=null) {
			currentMenu.display(); 

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				String line = reader.readLine(); // read what input the user
				int choiceItem = Integer.parseInt(line);// translate to the item
				
				//search for item in the menu list
				currentItemMenu = currentMenu.getMenuList().get(choiceItem-1);
				currentMenu = currentItemMenu.work();// item menu is executed and return menu

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
