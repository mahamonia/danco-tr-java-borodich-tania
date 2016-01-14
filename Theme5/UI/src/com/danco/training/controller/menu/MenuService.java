package com.danco.training.controller.menu;

import java.util.List;

import com.danco.training.controller.item.itemmenu.AbstractItem;

public class MenuService extends AbstractMenu {

	public MenuService(String name, List<AbstractItem> menuList) {
		super(name, menuList);
	}

}
