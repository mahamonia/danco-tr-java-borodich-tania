package com.danco.training.view.menudispaly;

public enum ItemMenuRoom {
	
	TITLE_MENU ("\n Menu for work room:\n"),
	ITEM_GET_AMOUNT_FREE_ROOM (" 1. Get amount free room.\n"),
	ITEM_CHANGE_PRISE ( " 2. Change price room.\n"),
	ITEM_CHANGE_STATUS (" 3. Change status room\n"),
	ITEM_ADDITIONAL_MENU (" 4. Print list room as...\n"),
	ITEM_EXIT ( " 5. Back in main menu.\n");
	
private String itemMenu;
	
	private ItemMenuRoom (String itemMenu){
		this.itemMenu = itemMenu;
	}

	public String getItemMenu() {
		return itemMenu;
	}
	
}