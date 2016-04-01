package com.danco.training.controller.menu;

import java.util.List;

import com.danco.training.controller.item.itemmenu.Item;

public class Menu {
	private String name;
	private List<Item> menuList;

	public Menu(String name, List<Item> menuList) {
		this.name = name;
	this.menuList = menuList;

	}

	public void display(){
		System.out.println(name);
		for (int i = 0; i < menuList.size(); i++) {
			System.out.println(i+1+ ". "+ menuList.get(i).getName());
		}
	}

	public List<Item> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Item> menuList) {
		this.menuList = menuList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
