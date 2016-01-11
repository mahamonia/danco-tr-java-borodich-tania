package com.danco.training.controller.item.menuguest;

import com.danco.training.controller.factory.itemmenu.AbstractItem;

public class ItemAmountGuest extends AbstractItem{
	
	public ItemAmountGuest () {
	}

	@Override
	public void work() {
		int amountGuest = admin.getAmountGuest();
		System.out.println("Amount guest = " + amountGuest);
		
	}

}
