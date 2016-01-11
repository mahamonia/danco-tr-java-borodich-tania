package com.danco.training.run;

import java.io.IOException;

import com.danco.training.controller.factory.mainmenu.AbstractFactoryMenu;
import com.danco.training.controller.factory.mainmenu.FactoryMenu;
import com.danco.training.controller.mainmenu.MainMenu;
import com.danco.training.view.menudispaly.DisplayMainMenu;

public class Run {

	public static void main(String[] args) throws IOException {
		
		AbstractFactoryMenu factoryMenu = new FactoryMenu();
		DisplayMainMenu displayMainMenu = new DisplayMainMenu();
		
		
		MainMenu menu = new MainMenu(factoryMenu, displayMainMenu);
		menu.work();
	}

}
