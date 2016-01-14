package com.danco.training.controller.workmenu;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.controller.item.itemmenu.AbstractItem;
import com.danco.training.controller.item.menuguest.ItemAddService;
import com.danco.training.controller.item.menuguest.ItemAmountGuest;
import com.danco.training.controller.item.menuguest.ItemGetSumOrderGuest;
import com.danco.training.controller.item.menuguest.ItemOutSettle;
import com.danco.training.controller.item.menuguest.ItemPrintListGuest;
import com.danco.training.controller.item.menuguest.ItemRegistry;
import com.danco.training.controller.item.menuguest.ItemSettle;
import com.danco.training.controller.item.menuguest.printlist.ItemBackMenuGuest;
import com.danco.training.controller.item.menuguest.printlist.ItemPrintGuestThemRoomSortedByDate;
import com.danco.training.controller.item.menuguest.printlist.ItemPrintGuestThemRoomSortedByName;
import com.danco.training.controller.item.menumain.ItemBackMainMenu;
import com.danco.training.controller.item.menumain.ItemExit;
import com.danco.training.controller.item.menumain.ItemMenuGuest;
import com.danco.training.controller.item.menumain.ItemMenuRoom;
import com.danco.training.controller.item.menumain.ItemMenuService;
import com.danco.training.controller.item.menuroom.ItemAmountFreeRoom;
import com.danco.training.controller.item.menuroom.ItemChangePriceRoom;
import com.danco.training.controller.item.menuroom.ItemChangeStatusRoom;
import com.danco.training.controller.item.menuroom.ItemLastThreeGuestsRoom;
import com.danco.training.controller.item.menuroom.ItemPrintListRoom;
import com.danco.training.controller.item.menuroom.printlist.ItemBackMenuRoom;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintFreeRoomSortedByContent;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintFreeRoomSortedByPrice;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintFreeRoomSortedByStars;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintRoomSortedByContent;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintRoomSortedByPrice;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintRoomSortedByStars;
import com.danco.training.controller.item.menuservice.ItemChangePriceService;
import com.danco.training.controller.item.menuservice.ItemPrintListService;
import com.danco.training.controller.item.menuservice.printlist.ItemBackMenuService;
import com.danco.training.controller.item.menuservice.printlist.ItemPrintAdditionalServiceThemPriceSortedByName;
import com.danco.training.controller.item.menuservice.printlist.ItemPrintAdditionalServiceThemPriceSortedByPrice;
import com.danco.training.controller.item.menuservice.printlist.ItemPrintDailServiceThemPriceSortedByName;
import com.danco.training.controller.item.menuservice.printlist.ItemPrintDailServiceThemPriceSortedByPrice;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.controller.menu.MenuGuest;
import com.danco.training.controller.menu.MenuGuestForPrint;
import com.danco.training.controller.menu.MenuMain;
import com.danco.training.controller.menu.MenuRoom;
import com.danco.training.controller.menu.MenuRoomForPrint;
import com.danco.training.controller.menu.MenuService;
import com.danco.training.controller.menu.MenuServiceForPrint;
import com.danco.training.services.ServiceAdmin;

public class MenuBuilder {

	public final String TITLE_MAIN_MENU = "  Main menu";

	public final String ITEM_MENU_GUEST = "Menu for work guest";
	public final String ITEM_MENU_ROOM = "Menu for work room";
	public final String ITEM_MENU_SERVICE = "Menu for work service";
	public final String ITEM_EXIT = "Exit";

	public final String TITLE_MENU_GUEST = "  Menu for work guest";

	public final String ITEM_REGISTRY = "Registry guest";
	public final String ITEM_SETTLE = "Settle guest in room";
	public final String ITEM_OUT_SETTLE = "Settle guest out room";
	public final String ITEM_PRINT_LIST_GUEST = "Print list guest ...";
	public final String ITEM_ADD_SERVICE = "Add service for guest";
	public final String ITEM_AMOUNT_GUEST = "Show amount guest";
	public final String ITEM_SUM_ORDER = "Get sum order guest";
	public final String ITEM_BACK_IN_MAIN_MENU = "Back in main menu";

	public final String TITLE_MENU_PRINT_GUEST = "  Menu for print list guest";
	public final String ITEM_PRINT_LIST_GUEST_SORTED_BY_NAME = "Print Guest Them Room Sorted By Name";
	public final String ITEM_PRINT_LIST_GUEST_SORTED_BY_DATE = "Print Guest Them Room Sorted By Date";
	public final String ITEM_BACK_IN_MENU_GUEST = "Back in menu guest";

	public final String TITLE_MENU_ROOM = "Menu for work room";

	public final String ITEM_PRINT_LIST_ROOM = "Print list room ...";
	public final String ITEM_AMOUNT_FREE_ROOM = "Show amount free room";
	public final String ITEM_THREE_GUEST_ROOM = "Show the last three guests of room";
	public final String ITEM_CHANGE_PRICE_ROOM = "Change price room";
	public final String ITEM_CHANGE_STATUS_ROOM = "Change status room";

	public final String TITLE_MENU_PRINT_ROOM = "Menu for print list room";
	public final String ITEM_PRINT_ROOM_SORTED_BY_CONTENT = "Print room sorted by content";
	public final String ITEM_PRINT_ROOM_SORTED_BY_PRICE = " Print room sorted by price";
	public final String ITEM_PRINT_ROOM_SORTED_BY_STARS = " Print room sorted by stars";
	public final String ITEM_PRINT_FREE_ROOM_SORTED_BY_CONTENT = "Print free room sorted by content";
	public final String ITEM_PRINT_FREE_ROOM_SORTED_BY_PRICE = " Print free room sorted by price";
	public final String ITEM_PRINT_FREE_ROOM_SORTED_BY_STARS = " Print free room sorted by stars";
	public final String ITEM_BACK_IN_MENU_ROOM = "Back in menu room";

	public final String TITLE_MENU_SERVICE = " Menu for work service";

	public final String ITEM_CHANGE_PRICE_SERVICE = "Change price service";
	public final String ITEM_PRINT_LIST_SERVICE = "Print list service ...";

	public final String TITLE_MENU_PRINT_SERVICE = "  Menu for print list service";
	public final String ITEM_PRINT_DAIL_SERVICE_SORTED_BY_NAME = "Print dail service them price sorted by name";
	public final String ITEM_PRINT_DAIL_SERVICE_SORTED_BY_PRICE = "Print dail service them price sorted by price";
	public final String ITEM_PRINT_ADD_SERVICE_SORTED_BY_NAME = "Print additional service them price sorted by name";
	public final String ITEM_PRINT_ADD_SERVICE_SORTED_BY_PRICE = "Print additional service them price sorted by price";
	public final String ITEM_BACK_IN_MENU_SERVICE = "Back menu service";

	private ServiceAdmin admin;

	public MenuBuilder(ServiceAdmin admin) {
		this.admin = admin;

	}

	public AbstractMenu buildMenu() {

		// ITEM MAIN MENU
		AbstractItem itemMenuGuest = new ItemMenuGuest(ITEM_MENU_GUEST);
		AbstractItem itemMenuRoom = new ItemMenuRoom(ITEM_MENU_ROOM);
		AbstractItem itemMenuService = new ItemMenuService(ITEM_MENU_SERVICE);
		AbstractItem itemExit = new ItemExit(ITEM_EXIT);

		// List items main menu
		List<AbstractItem> itemsMenuMain = new ArrayList<AbstractItem>();
		itemsMenuMain.add(itemMenuGuest);
		itemsMenuMain.add(itemMenuRoom);
		itemsMenuMain.add(itemMenuService);
		itemsMenuMain.add(itemExit);

		// === MENU MAIN ===
		AbstractMenu menuMain = new MenuMain(TITLE_MAIN_MENU, itemsMenuMain);

		// Guest ============================

		// ITEMS MENU FOR WORK GUEST
		AbstractItem itemRegistry = new ItemRegistry(ITEM_REGISTRY, admin);
		AbstractItem itemSettle = new ItemSettle(ITEM_SETTLE, admin);
		AbstractItem itemOutSettle = new ItemOutSettle(ITEM_OUT_SETTLE, admin);
		AbstractItem itemPrintListGuest = new ItemPrintListGuest(
				ITEM_PRINT_LIST_GUEST);
		AbstractItem itemAddService = new ItemAddService(ITEM_ADD_SERVICE,
				admin);
		AbstractItem itemAmountGuest = new ItemAmountGuest(ITEM_AMOUNT_GUEST,
				admin);
		AbstractItem itemGetSumOrder = new ItemGetSumOrderGuest(ITEM_SUM_ORDER,
				admin);
		AbstractItem itemBackMainMenu = new ItemBackMainMenu(
				ITEM_BACK_IN_MAIN_MENU);

		List<AbstractItem> itemsMenuGuest = new ArrayList<AbstractItem>();
		itemsMenuGuest.add(itemRegistry);
		itemsMenuGuest.add(itemSettle);
		itemsMenuGuest.add(itemOutSettle);
		itemsMenuGuest.add(itemPrintListGuest);
		itemsMenuGuest.add(itemAddService);
		itemsMenuGuest.add(itemAmountGuest);
		itemsMenuGuest.add(itemGetSumOrder);
		itemsMenuGuest.add(itemBackMainMenu);

		// == MENU GUEST ==
		AbstractMenu menuGuest = new MenuGuest(TITLE_MENU_GUEST, itemsMenuGuest);

		for (int i = 0; i < itemsMenuGuest.size(); i++) {
			itemsMenuGuest.get(i).setMenu(menuGuest);
		}

		itemMenuGuest.setMenu(menuGuest); // item go to menu guest
		itemBackMainMenu.setMenu(menuMain);

		// ITEMS MENU PRINT LIST GUEST
		AbstractItem itemPrintGuestThemRoomSortedByDate = new ItemPrintGuestThemRoomSortedByDate(
				ITEM_PRINT_LIST_GUEST_SORTED_BY_DATE, admin);
		AbstractItem itemPrintGuestThemRoomSortedByName = new ItemPrintGuestThemRoomSortedByName(
				ITEM_PRINT_LIST_GUEST_SORTED_BY_NAME, admin);
		AbstractItem itemBackMenuGuest = new ItemBackMenuGuest(
				ITEM_BACK_IN_MENU_GUEST);

		List<AbstractItem> itemsMenuPrintGuest = new ArrayList<AbstractItem>();
		itemsMenuPrintGuest.add(itemPrintGuestThemRoomSortedByName);
		itemsMenuPrintGuest.add(itemPrintGuestThemRoomSortedByDate);
		itemsMenuPrintGuest.add(itemBackMenuGuest);

		// == MENU PRINT GUEST ==
		AbstractMenu menuPrintGuest = new MenuGuestForPrint(
				TITLE_MENU_PRINT_GUEST, itemsMenuPrintGuest);

		for (int i = 0; i < itemsMenuPrintGuest.size() - 1; i++) {
			itemsMenuPrintGuest.get(i).setMenu(menuPrintGuest);
		}
		itemPrintListGuest.setMenu(menuPrintGuest); // item go to menu for print
													// guest
		itemBackMenuGuest.setMenu(menuGuest);

		// Room ==================================

		// ITEMS MENU FOR WORK ROOM
		AbstractItem itemPrintListRoom = new ItemPrintListRoom(
				ITEM_PRINT_LIST_ROOM);
		AbstractItem itemAmountFreeRoom = new ItemAmountFreeRoom(
				ITEM_AMOUNT_FREE_ROOM, admin);
		AbstractItem itemLastThreeGuest = new ItemLastThreeGuestsRoom(
				ITEM_THREE_GUEST_ROOM, admin);
		AbstractItem itemChangePriceRoom = new ItemChangePriceRoom(
				ITEM_CHANGE_PRICE_ROOM, admin);
		AbstractItem itemChangeStatus = new ItemChangeStatusRoom(
				ITEM_CHANGE_STATUS_ROOM, admin);

		List<AbstractItem> itemsMenuRoom = new ArrayList<AbstractItem>();
		itemsMenuRoom.add(itemPrintListRoom);
		itemsMenuRoom.add(itemAmountFreeRoom);
		itemsMenuRoom.add(itemLastThreeGuest);
		itemsMenuRoom.add(itemChangePriceRoom);
		itemsMenuRoom.add(itemChangeStatus);
		itemsMenuRoom.add(itemBackMainMenu);

		// == MENU ROOM ==
		AbstractMenu menuRoom = new MenuRoom(TITLE_MENU_ROOM, itemsMenuRoom);

		for (int i = 0; i < itemsMenuRoom.size() - 1; i++) {
			itemsMenuRoom.get(i).setMenu(menuRoom);
		}

		itemMenuRoom.setMenu(menuRoom);// item go to menu room

		// ITEMS MENU PRINT LIST ROOM
		AbstractItem itemPrintRoomSortedByContent = new ItemPrintRoomSortedByContent(
				ITEM_PRINT_ROOM_SORTED_BY_CONTENT, admin);
		AbstractItem itemPrintRoomSortedByPrice = new ItemPrintRoomSortedByPrice(
				ITEM_PRINT_ROOM_SORTED_BY_PRICE, admin);
		AbstractItem itemPrintRoomSortedByStars = new ItemPrintRoomSortedByStars(
				ITEM_PRINT_ROOM_SORTED_BY_STARS, admin);
		AbstractItem itemPrintFreeRoomSortedByContent = new ItemPrintFreeRoomSortedByContent(
				ITEM_PRINT_FREE_ROOM_SORTED_BY_CONTENT, admin);
		AbstractItem itemPrintFreeRoomSortedByPrice = new ItemPrintFreeRoomSortedByPrice(
				ITEM_PRINT_FREE_ROOM_SORTED_BY_PRICE, admin);
		AbstractItem itemPrintFreeRoomSortedByStars = new ItemPrintFreeRoomSortedByStars(
				ITEM_PRINT_FREE_ROOM_SORTED_BY_STARS, admin);
		AbstractItem itemBackMenuRoom = new ItemBackMenuRoom(
				ITEM_BACK_IN_MENU_ROOM);

		List<AbstractItem> itemsMenuPrintRoom = new ArrayList<AbstractItem>();
		itemsMenuPrintRoom.add(itemPrintRoomSortedByContent);
		itemsMenuPrintRoom.add(itemPrintRoomSortedByPrice);
		itemsMenuPrintRoom.add(itemPrintRoomSortedByStars);
		itemsMenuPrintRoom.add(itemPrintFreeRoomSortedByContent);
		itemsMenuPrintRoom.add(itemPrintFreeRoomSortedByPrice);
		itemsMenuPrintRoom.add(itemPrintFreeRoomSortedByStars);
		itemsMenuPrintRoom.add(itemBackMenuRoom);

		// == MENU PRINT ROOM ==
		AbstractMenu menuPrintRoom = new MenuRoomForPrint(
				TITLE_MENU_PRINT_ROOM, itemsMenuPrintRoom);

		for (int i = 0; i < itemsMenuPrintRoom.size() - 1; i++) {
			itemsMenuPrintRoom.get(i).setMenu(menuPrintRoom);
		}
		itemPrintListRoom.setMenu(menuPrintRoom); // item go to menu for print
													// room
		itemBackMenuRoom.setMenu(menuRoom);

		// Service ============================

		// ITEMS MENU FOR WOR SERVICE
		AbstractItem itemChangePriceService = new ItemChangePriceService(
				ITEM_CHANGE_PRICE_SERVICE, admin);
		AbstractItem itemPrintListService = new ItemPrintListService(
				ITEM_PRINT_LIST_SERVICE);

		List<AbstractItem> itemsMenuService = new ArrayList<AbstractItem>();
		itemsMenuService.add(itemChangePriceService);
		itemsMenuService.add(itemPrintListService);
		itemsMenuService.add(itemBackMainMenu);

		// == MENU SERVICE ==
		AbstractMenu menuService = new MenuService(TITLE_MENU_SERVICE,
				itemsMenuService);

		for (int i = 0; i < itemsMenuService.size() - 1; i++) {
			itemsMenuService.get(i).setMenu(menuService);
		}

		itemMenuService.setMenu(menuService);// item go to menu service

		// ITEMS MENU PRINT LIST SERVICE
		AbstractItem itemPrintDailServiceThemPriceSortedByName = new ItemPrintDailServiceThemPriceSortedByName(
				ITEM_PRINT_DAIL_SERVICE_SORTED_BY_NAME, admin);
		AbstractItem itemPrintDailServiceThemPriceSortedByPrice = new ItemPrintDailServiceThemPriceSortedByPrice(
				ITEM_PRINT_DAIL_SERVICE_SORTED_BY_PRICE, admin);
		AbstractItem itemPrintAdditionalServiceThemPriceSortedByName = new ItemPrintAdditionalServiceThemPriceSortedByName(
				ITEM_PRINT_ADD_SERVICE_SORTED_BY_NAME, admin);
		AbstractItem itemPrintAdditionalServiceThemPriceSortedByPrice = new ItemPrintAdditionalServiceThemPriceSortedByPrice(
				ITEM_PRINT_ADD_SERVICE_SORTED_BY_PRICE, admin);
		AbstractItem itemBackMenuService = new ItemBackMenuService(
				ITEM_BACK_IN_MENU_SERVICE);

		List<AbstractItem> itemsMenuPrintService = new ArrayList<AbstractItem>();
		itemsMenuPrintService.add(itemPrintDailServiceThemPriceSortedByName);
		itemsMenuPrintService.add(itemPrintDailServiceThemPriceSortedByPrice);
		itemsMenuPrintService
				.add(itemPrintAdditionalServiceThemPriceSortedByName);
		itemsMenuPrintService
				.add(itemPrintAdditionalServiceThemPriceSortedByPrice);
		itemsMenuPrintService.add(itemBackMenuService);

		// == MENU PRINT SERVICE ==
		AbstractMenu menuPrintService = new MenuServiceForPrint(
				TITLE_MENU_PRINT_SERVICE, itemsMenuPrintService);

		for (int i = 0; i < itemsMenuPrintService.size() - 1; i++) {
			itemsMenuPrintService.get(i).setMenu(menuPrintService);
		}
		itemPrintListService.setMenu(menuPrintService); // item go to menu for
														// print service
		itemBackMenuService.setMenu(menuService);

		return menuMain;

	}

}
