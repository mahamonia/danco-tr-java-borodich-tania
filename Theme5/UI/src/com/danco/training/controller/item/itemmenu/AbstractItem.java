package com.danco.training.controller.item.itemmenu;

import com.danco.training.controller.menu.AbstractMenu;

public abstract class AbstractItem {
	private String name;
	private AbstractMenu menu;

	public AbstractItem(String name) {
		this.name = name;
	}

	public abstract AbstractMenu work();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbstractMenu getMenu() {
		return menu;
	}

	public void setMenu(AbstractMenu menu) {
		this.menu = menu;
	}

}
