package com.danco.training.controller.item.itemmenu;

import com.danco.training.controller.menu.Menu;


public class Item {
	private String name;
	private Menu menu;

	public Item(String name) {
		this.name = name;
	}

	public Menu work(){
		return this.getMenu();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
