package com.danco.training.view.menudispaly;

public enum ItemMenuGuest {
	
	TITLE_MENU ( "\n Menu for work guest:\n"),
	ITEM_REGISTRY ( "1. Registry guest.\n"),
	ITEM_SETTLE ( "2. Settel guest.\n"),
	ITEM_OUTSETTLE ("3. Out settle guest.\n"),
	ITEM_ADD_SERVICE ("4. Add service.\n"),
	ITEM_GET_AMOUNT_GUEST ( "5. Amount guest.\n"),
	ITEM_GET_SUM_ORDER_GUEST ( "5. Sum order guest.\n"),
	ITEM_ADDITIONAL_MENU ( "6. Print list guest as...\n"),
	ITEM_EXIT ( "7. Back in main menu.");

	private String itemMenu;
	
	private ItemMenuGuest (String itemMenu){
		this.itemMenu = itemMenu;
	}

	public String getItemMenu() {
		return itemMenu;
	}
	
}
