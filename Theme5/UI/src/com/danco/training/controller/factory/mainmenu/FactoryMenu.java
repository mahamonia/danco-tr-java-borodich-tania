package com.danco.training.controller.factory.mainmenu;

import com.danco.training.controller.factory.itemmenu.AbstractFacoryItemMenu;
import com.danco.training.controller.factory.itemmenu.FactoryItemMenuGuest;
import com.danco.training.controller.factory.itemmenu.FactoryItemMenuRoom;
import com.danco.training.controller.factory.itemmenu.FactoryItemMenuService;
import com.danco.training.controller.mainmenu.MenuGuest;
import com.danco.training.controller.mainmenu.MenuRoom;
import com.danco.training.controller.mainmenu.MenuService;

public class FactoryMenu extends AbstractFactoryMenu{
	
	private AbstractFacoryItemMenu factoryItemMenu;
	private AbstractMenu menu;
	
	public FactoryMenu() {

	}

	@Override
	public AbstractMenu createMenu(int nItem) {
		
		switch (nItem) {
		case 1:
			factoryItemMenu = new FactoryItemMenuGuest();
			menu = new MenuGuest(factoryItemMenu);
			break;
		case 2:
			factoryItemMenu = new FactoryItemMenuRoom();
			menu = new MenuRoom(factoryItemMenu);
			break;
		case 3:
			factoryItemMenu = new FactoryItemMenuService();
			menu = new MenuService(factoryItemMenu);
			break;
		default:
			break;
		}
		return menu;
	}

}
