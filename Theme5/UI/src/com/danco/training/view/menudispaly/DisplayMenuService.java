package com.danco.training.view.menudispaly;

public class DisplayMenuService implements IDisplayMenu{
	
	@Override
	public void displayMenu() {
		for (ItemMenuService item: ItemMenuService.values()) {
			System.out.print(item.getItemMenu());
			}
				
	}

	@Override
	public void displayAdditionalMenu() {
		// TODO Auto-generated method stub
		
	}

}
