package com.danco.training.view.menudispaly;

public class DisplayMenuGuest implements IDisplayMenu{
		
	public DisplayMenuGuest() {

	}

	@Override
	public void displayMenu() {
		for (ItemMenuGuest item: ItemMenuGuest.values()) {
			System.out.print(item.getItemMenu());
			}
		
	}

	@Override
	public void displayAdditionalMenu() {

		
	}

}
