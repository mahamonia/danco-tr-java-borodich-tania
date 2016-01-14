package com.danco.training.controller.menu;

import java.util.List;

import com.danco.training.controller.item.itemmenu.AbstractItem;

public abstract class AbstractMenu {
	private String name;
	private List<AbstractItem> menuList;

	public AbstractMenu(String name, List<AbstractItem> menuList) {
		this.name = name;
	this.menuList = menuList;

	}

	public void display(){
		System.out.println(name);
		for (int i = 0; i < menuList.size(); i++) {
			System.out.println(i+1+ ". "+ menuList.get(i).getName());
		}
	}

	public List<AbstractItem> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AbstractItem> menuList) {
		this.menuList = menuList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
