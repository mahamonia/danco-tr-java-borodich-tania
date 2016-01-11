package com.danco.training.view.menudispaly;

public class DisplayMenuRoom implements IDisplayMenu{
	
	

	@Override
	public void displayMenu() {
		for (ItemMenuRoom item: ItemMenuRoom.values()) {
			System.out.print(item.getItemMenu());
			}
		
	}

	@Override
	public void displayAdditionalMenu() {
		// TODO Auto-generated method stub
		
	}

}
