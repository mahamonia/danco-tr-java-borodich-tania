package com.danco.training.view.menudispaly;

public enum ItemMainMenu {
	
	TITLE_MENU ("\n Main menu:\n"),
	MENU_FOR_GUEST (" 1. Menu for work guest\n"),
	MENU_FOR_ROOM (" 2. Menu for work room\n"),
	MENU_FOR_SERVICE (" 3. Menu for work service\n"),
	EXIT (" 4. Exit\n");
	
	private String itemMenu;
	
	private ItemMainMenu (String itemMenu){
		this.itemMenu = itemMenu;
	}
	
	static public ItemMainMenu getItem(String itemMenu) {
		for (ItemMainMenu item: ItemMainMenu.values()) {
			if (item.getItemMenu().equals(itemMenu)) {
				return item;
			}
		}
		throw new RuntimeException("unknown type");
	}

	public String getItemMenu() {
		return itemMenu;
	}
	

}
