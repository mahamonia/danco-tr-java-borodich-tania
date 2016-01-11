package com.danco.training.controller.factory.itemmenu;

import com.danco.training.controller.item.menuguest.ItemAddService;
import com.danco.training.controller.item.menuguest.ItemAmountGuest;
import com.danco.training.controller.item.menuguest.ItemOutSettle;
import com.danco.training.controller.item.menuguest.ItemPrintListGuest;
import com.danco.training.controller.item.menuguest.ItemRegistry;
import com.danco.training.controller.item.menuguest.ItemSettel;

public class FactoryItemMenuGuest extends AbstractFacoryItemMenu {

	private AbstractItem item;

	public FactoryItemMenuGuest() {
		super();
	}

	@Override
	public AbstractItem createItemMenu(int nItem) {
		switch (nItem) {
		case 1:
			item = new ItemRegistry();
			break;
		case 2:
			item = new ItemSettel();
			break;
		case 3:
			item = new ItemOutSettle();
			break;
		case 4:
			item = new ItemAddService();
			break;
		case 5:
			item = new ItemAmountGuest();
			break;
		case 6:
			item = new ItemPrintListGuest();
			break;
		}
		return item;
	}

}
