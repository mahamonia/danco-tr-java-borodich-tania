package com.danco.training.view.menudispaly;

public enum ItemMenuService {
	TITLE_MENU("\n Menu for work service:\n"),
	ITEM_CHANGE_PRISE(" 1. Change price service.\n"), 
	ITEM_ADDITIONAL_MENU(" 2. Print list service as....\n"), 
	ITEM_EXIT(" 3. Back in main menu.\n");

	private String itemMenu;

	private ItemMenuService(String itemMenu) {
		this.itemMenu = itemMenu;
	}

	public String getItemMenu() {
		return itemMenu;
	}

}
