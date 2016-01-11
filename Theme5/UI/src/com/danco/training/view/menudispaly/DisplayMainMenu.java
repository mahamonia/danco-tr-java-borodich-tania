package com.danco.training.view.menudispaly;

public class DisplayMainMenu {

	public DisplayMainMenu() {
		
	}
	
	public void displayMenu() {
		for (ItemMainMenu item: ItemMainMenu.values()) {
			System.out.print(item.getItemMenu());
			}

	}

}
