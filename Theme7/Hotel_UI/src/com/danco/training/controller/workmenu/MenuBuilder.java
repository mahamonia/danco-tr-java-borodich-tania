package com.danco.training.controller.workmenu;

import java.util.ArrayList;
import java.util.List;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.Item;
import com.danco.training.controller.item.itemmenu.ItemExit;
import com.danco.training.controller.item.menuguest.ItemAddService;
import com.danco.training.controller.item.menuguest.ItemAmountGuest;
import com.danco.training.controller.item.menuguest.ItemExportGuestsList;
import com.danco.training.controller.item.menuguest.ItemGetSumOrderGuest;
import com.danco.training.controller.item.menuguest.ItemImportGuestsList;
import com.danco.training.controller.item.menuguest.ItemOutSettle;
import com.danco.training.controller.item.menuguest.ItemRegistry;
import com.danco.training.controller.item.menuguest.ItemSettle;
import com.danco.training.controller.item.menuguest.printlist.ItemPrintGuestSortedById;
import com.danco.training.controller.item.menuguest.printlist.ItemPrintGuestThemRoomSortedByDate;
import com.danco.training.controller.item.menuguest.printlist.ItemPrintGuestThemRoomSortedByName;
import com.danco.training.controller.item.menuroom.ItemAmountFreeRoom;
import com.danco.training.controller.item.menuroom.ItemChangePriceRoom;
import com.danco.training.controller.item.menuroom.ItemChangeStatusRoom;
import com.danco.training.controller.item.menuroom.ItemCreateRoom;
import com.danco.training.controller.item.menuroom.ItemExportRoomList;
import com.danco.training.controller.item.menuroom.ItemForCloningRoom;
import com.danco.training.controller.item.menuroom.ItemImportRoomList;
import com.danco.training.controller.item.menuroom.ItemLastThreeGuestsRoom;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintFreeRoomSortedByContent;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintFreeRoomSortedByPrice;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintFreeRoomSortedByStars;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintRoomSortedByContent;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintRoomSortedByPrice;
import com.danco.training.controller.item.menuroom.printlist.ItemPrintRoomSortedByStars;
import com.danco.training.controller.item.menuservice.ItemChangePriceService;
import com.danco.training.controller.item.menuservice.ItemCreateAdditionalService;
import com.danco.training.controller.item.menuservice.ItemCreateDailService;
import com.danco.training.controller.item.menuservice.ItemExportAdditionalService;
import com.danco.training.controller.item.menuservice.ItemExportDailService;
import com.danco.training.controller.item.menuservice.ItemImportAdditionalService;
import com.danco.training.controller.item.menuservice.ItemImportDailServiceList;
import com.danco.training.controller.item.menuservice.printlist.ItemPrintAdditionalServiceThemPriceSortedByName;
import com.danco.training.controller.item.menuservice.printlist.ItemPrintAdditionalServiceThemPriceSortedByPrice;
import com.danco.training.controller.item.menuservice.printlist.ItemPrintDailServiceThemPriceSortedByName;
import com.danco.training.controller.item.menuservice.printlist.ItemPrintDailServiceThemPriceSortedByPrice;
import com.danco.training.controller.menu.Menu;

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
	public final String ITEM_IMPORT_LIST_GUEST ="Import list guest";
	public final String ITEM_EXPORT_LIST_GUEST ="Export list guest";
	public final String ITEM_BACK_IN_MAIN_MENU = "Back in main menu";

	public final String TITLE_MENU_PRINT_GUEST = "  Menu for print list guest";
	public final String ITEM_PRINT_LIST_GUESTS_SORTED_BY_NAME = "Print Guest";
	public final String ITEM_PRINT_LIST_GUEST_SORTED_BY_NAME = "Print Guest Them Room Sorted By Name";
	public final String ITEM_PRINT_LIST_GUEST_SORTED_BY_DATE = "Print Guest Them Room Sorted By Date";
	public final String ITEM_BACK_IN_MENU_GUEST = "Back in menu guest";

	public final String TITLE_MENU_ROOM = "Menu for work room";

	public final String ITEM_CREATE_ROOM = "Create room ";
	public final String ITEM_PRINT_LIST_ROOM = "Print list room ...";
	public final String ITEM_AMOUNT_FREE_ROOM = "Show amount free room";
	public final String ITEM_THREE_GUEST_ROOM = "Show the last three guests of room";
	public final String ITEM_CHANGE_PRICE_ROOM = "Change price room";
	public final String ITEM_CHANGE_STATUS_ROOM = "Change status room";
	public final String ITEM_CLONE_ROOM = "Clone room";
	public final String ITEM_IMPORT_LIST_ROOM = "Import list room";
	public final String ITEM_EXPORT_LIST_ROOM = "Export list room";

	public final String TITLE_MENU_PRINT_ROOM = "Menu for print list room";
	public final String ITEM_PRINT_ROOM_SORTED_BY_CONTENT = "Print room sorted by content";
	public final String ITEM_PRINT_ROOM_SORTED_BY_PRICE = " Print room sorted by price";
	public final String ITEM_PRINT_ROOM_SORTED_BY_STARS = " Print room sorted by stars";
	public final String ITEM_PRINT_FREE_ROOM_SORTED_BY_CONTENT = "Print free room sorted by content";
	public final String ITEM_PRINT_FREE_ROOM_SORTED_BY_PRICE = " Print free room sorted by price";
	public final String ITEM_PRINT_FREE_ROOM_SORTED_BY_STARS = " Print free room sorted by stars";
	public final String ITEM_BACK_IN_MENU_ROOM = "Back in menu room";

	public final String TITLE_MENU_SERVICE = " Menu for work service";
	
	public final String ITEM_CREATE_DAIL_SERVICE = "Create dail service ";
	public final String ITEM_CREATE_ADDITIONAL_SERVICE = "Create additional service ";
	public final String ITEM_CHANGE_PRICE_SERVICE = "Change price service";
	public final String ITEM_PRINT_LIST_SERVICE = "Print list service ...";
	public final String ITEM_DAIL_SERVICE_IMPORT = "Dail service list import";
	public final String ITEM_DAIL_SERVICE_EXPORT = "Dail service list export";
	public final String ITEM_ADDITIONAL_SERVICE_IMPORT = "Additional service list import";
	public final String ITEM_ADDITIONAL_SERVICE_EXPORT = "Additional service list export";

	public final String TITLE_MENU_PRINT_SERVICE = "  Menu for print list service";
	public final String ITEM_PRINT_DAIL_SERVICE_SORTED_BY_NAME = "Print dail service them price sorted by name";
	public final String ITEM_PRINT_DAIL_SERVICE_SORTED_BY_PRICE = "Print dail service them price sorted by price";
	public final String ITEM_PRINT_ADD_SERVICE_SORTED_BY_NAME = "Print additional service them price sorted by name";
	public final String ITEM_PRINT_ADD_SERVICE_SORTED_BY_PRICE = "Print additional service them price sorted by price";
	public final String ITEM_BACK_IN_MENU_SERVICE = "Back menu service";

	private IServiceAdmin admin;

	public MenuBuilder(IServiceAdmin admin) {
		this.admin = admin;

	}

	public Menu buildMenu() {

		// ITEM MAIN MENU
		Item itemMenuGuest = new Item(ITEM_MENU_GUEST);
		Item itemMenuRoom = new Item(ITEM_MENU_ROOM);
		Item itemMenuService = new Item(ITEM_MENU_SERVICE);
		Item itemExit = new ItemExit(ITEM_EXIT);

		// List items main menu
		List<Item> itemsMenuMain = new ArrayList<Item>();
		itemsMenuMain.add(itemMenuGuest);
		itemsMenuMain.add(itemMenuRoom);
		itemsMenuMain.add(itemMenuService);
		itemsMenuMain.add(itemExit);

		// === MENU MAIN ===
		Menu menuMain = new Menu(TITLE_MAIN_MENU, itemsMenuMain);

		// Guest ============================

		// ITEMS MENU FOR WORK GUEST
		Item itemRegistry = new ItemRegistry(ITEM_REGISTRY, admin);
		Item itemSettle = new ItemSettle(ITEM_SETTLE, admin);
		Item itemOutSettle = new ItemOutSettle(ITEM_OUT_SETTLE, admin);
		Item itemPrintListGuest = new Item(ITEM_PRINT_LIST_GUEST);
		Item itemAddService = new ItemAddService(ITEM_ADD_SERVICE, admin);
		Item itemAmountGuest = new ItemAmountGuest(ITEM_AMOUNT_GUEST, admin);
		Item itemGetSumOrder = new ItemGetSumOrderGuest(ITEM_SUM_ORDER, admin);
		Item itemImportListGuest = new ItemImportGuestsList(ITEM_IMPORT_LIST_GUEST, admin);
		Item itemExportListGuest = new ItemExportGuestsList(ITEM_EXPORT_LIST_GUEST, admin);
		Item itemBackMainMenu = new Item(ITEM_BACK_IN_MAIN_MENU);

		List<Item> itemsMenuGuest = new ArrayList<Item>();
		itemsMenuGuest.add(itemRegistry);
		itemsMenuGuest.add(itemSettle);
		itemsMenuGuest.add(itemOutSettle);
		itemsMenuGuest.add(itemPrintListGuest);
		itemsMenuGuest.add(itemAddService);
		itemsMenuGuest.add(itemAmountGuest);
		itemsMenuGuest.add(itemGetSumOrder);
		itemsMenuGuest.add(itemImportListGuest);
		itemsMenuGuest.add(itemExportListGuest);
		itemsMenuGuest.add(itemBackMainMenu);

		// == MENU GUEST ==
		Menu menuGuest = new Menu(TITLE_MENU_GUEST, itemsMenuGuest);

		for (int i = 0; i < itemsMenuGuest.size(); i++) {
			itemsMenuGuest.get(i).setMenu(menuGuest);
		}

		itemMenuGuest.setMenu(menuGuest); // item go to menu guest
		itemBackMainMenu.setMenu(menuMain);

		// ITEMS MENU PRINT LIST GUEST
		Item itemPrintGuestsList = new ItemPrintGuestSortedById(ITEM_PRINT_LIST_GUESTS_SORTED_BY_NAME, admin);
		Item itemPrintGuestThemRoomSortedByDate = new ItemPrintGuestThemRoomSortedByDate(
				ITEM_PRINT_LIST_GUEST_SORTED_BY_DATE, admin);
		Item itemPrintGuestThemRoomSortedByName = new ItemPrintGuestThemRoomSortedByName(
				ITEM_PRINT_LIST_GUEST_SORTED_BY_NAME, admin);
		Item itemBackMenuGuest = new Item(ITEM_BACK_IN_MENU_GUEST);

		List<Item> itemsMenuPrintGuest = new ArrayList<Item>();
		itemsMenuPrintGuest.add(itemPrintGuestsList);
		itemsMenuPrintGuest.add(itemPrintGuestThemRoomSortedByName);
		itemsMenuPrintGuest.add(itemPrintGuestThemRoomSortedByDate);
		itemsMenuPrintGuest.add(itemBackMenuGuest);

		// == MENU PRINT GUEST ==
		Menu menuPrintGuest = new Menu(TITLE_MENU_PRINT_GUEST,
				itemsMenuPrintGuest);

		for (int i = 0; i < itemsMenuPrintGuest.size() - 1; i++) {
			itemsMenuPrintGuest.get(i).setMenu(menuPrintGuest);
		}
		itemPrintListGuest.setMenu(menuPrintGuest); // item go to menu for print
													// guest
		itemBackMenuGuest.setMenu(menuGuest);

		// Room ==================================

		// ITEMS MENU FOR WORK ROOM
		Item itemCreateRoom = new ItemCreateRoom(ITEM_CREATE_ROOM, admin);
		Item itemPrintListRoom = new Item(ITEM_PRINT_LIST_ROOM);
		Item itemAmountFreeRoom = new ItemAmountFreeRoom(ITEM_AMOUNT_FREE_ROOM,
				admin);
		Item itemLastThreeGuest = new ItemLastThreeGuestsRoom(
				ITEM_THREE_GUEST_ROOM, admin);
		Item itemChangePriceRoom = new ItemChangePriceRoom(
				ITEM_CHANGE_PRICE_ROOM, admin);
		Item itemChangeStatus = new ItemChangeStatusRoom(
				ITEM_CHANGE_STATUS_ROOM, admin);
		Item itemCloneRoom = new ItemForCloningRoom(ITEM_CLONE_ROOM, admin);
		Item itemImportRoom = new ItemImportRoomList(ITEM_IMPORT_LIST_ROOM, admin);
		Item itemExportRoom = new ItemExportRoomList(ITEM_EXPORT_LIST_ROOM, admin);

		List<Item> itemsMenuRoom = new ArrayList<Item>();
		itemsMenuRoom.add(itemCreateRoom);
		itemsMenuRoom.add(itemPrintListRoom);
		itemsMenuRoom.add(itemAmountFreeRoom);
		itemsMenuRoom.add(itemLastThreeGuest);
		itemsMenuRoom.add(itemChangePriceRoom);
		itemsMenuRoom.add(itemChangeStatus);
		itemsMenuRoom.add(itemCloneRoom);
		itemsMenuRoom.add(itemImportRoom);
		itemsMenuRoom.add(itemExportRoom);
		itemsMenuRoom.add(itemBackMainMenu);

		// == MENU ROOM ==
		Menu menuRoom = new Menu(TITLE_MENU_ROOM, itemsMenuRoom);

		for (int i = 0; i < itemsMenuRoom.size() - 1; i++) {
			itemsMenuRoom.get(i).setMenu(menuRoom);
		}

		itemMenuRoom.setMenu(menuRoom);// item go to menu room

		// ITEMS MENU PRINT LIST ROOM
		Item itemPrintRoomSortedByContent = new ItemPrintRoomSortedByContent(
				ITEM_PRINT_ROOM_SORTED_BY_CONTENT, admin);
		Item itemPrintRoomSortedByPrice = new ItemPrintRoomSortedByPrice(
				ITEM_PRINT_ROOM_SORTED_BY_PRICE, admin);
		Item itemPrintRoomSortedByStars = new ItemPrintRoomSortedByStars(
				ITEM_PRINT_ROOM_SORTED_BY_STARS, admin);
		Item itemPrintFreeRoomSortedByContent = new ItemPrintFreeRoomSortedByContent(
				ITEM_PRINT_FREE_ROOM_SORTED_BY_CONTENT, admin);
		Item itemPrintFreeRoomSortedByPrice = new ItemPrintFreeRoomSortedByPrice(
				ITEM_PRINT_FREE_ROOM_SORTED_BY_PRICE, admin);
		Item itemPrintFreeRoomSortedByStars = new ItemPrintFreeRoomSortedByStars(
				ITEM_PRINT_FREE_ROOM_SORTED_BY_STARS, admin);
		Item itemBackMenuRoom = new Item(ITEM_BACK_IN_MENU_ROOM);

		List<Item> itemsMenuPrintRoom = new ArrayList<Item>();
		itemsMenuPrintRoom.add(itemPrintRoomSortedByContent);
		itemsMenuPrintRoom.add(itemPrintRoomSortedByPrice);
		itemsMenuPrintRoom.add(itemPrintRoomSortedByStars);
		itemsMenuPrintRoom.add(itemPrintFreeRoomSortedByContent);
		itemsMenuPrintRoom.add(itemPrintFreeRoomSortedByPrice);
		itemsMenuPrintRoom.add(itemPrintFreeRoomSortedByStars);
		itemsMenuPrintRoom.add(itemBackMenuRoom);

		// == MENU PRINT ROOM ==
		Menu menuPrintRoom = new Menu(TITLE_MENU_PRINT_ROOM, itemsMenuPrintRoom);

		for (int i = 0; i < itemsMenuPrintRoom.size() - 1; i++) {
			itemsMenuPrintRoom.get(i).setMenu(menuPrintRoom);
		}
		itemPrintListRoom.setMenu(menuPrintRoom); // item go to menu for print
													// room
		itemBackMenuRoom.setMenu(menuRoom);

		// Service ============================

		// ITEMS MENU FOR WOR SERVICE
		Item itemCreateDailService = new ItemCreateDailService(ITEM_CREATE_DAIL_SERVICE, admin);
		Item itemCeateAdditionalService = new ItemCreateAdditionalService(ITEM_CREATE_ADDITIONAL_SERVICE, admin);
		Item itemChangePriceService = new ItemChangePriceService(
				ITEM_CHANGE_PRICE_SERVICE, admin);
		Item itemPrintListService = new Item(ITEM_PRINT_LIST_SERVICE);
		Item itemImportDailServiceList = new ItemImportDailServiceList(ITEM_DAIL_SERVICE_IMPORT, admin);
		Item itemExportDailServiceList = new ItemExportDailService(ITEM_DAIL_SERVICE_EXPORT, admin);
		Item itemImportAdditionalServiceList = new ItemImportAdditionalService(ITEM_ADDITIONAL_SERVICE_IMPORT, admin);
		Item itemExportAdditionalServiceList = new ItemExportAdditionalService(ITEM_ADDITIONAL_SERVICE_EXPORT, admin);

		List<Item> itemsMenuService = new ArrayList<Item>();
		itemsMenuService.add(itemCreateDailService);
		itemsMenuService.add(itemCeateAdditionalService);
		itemsMenuService.add(itemChangePriceService);
		itemsMenuService.add(itemPrintListService);
		itemsMenuService.add(itemImportDailServiceList);
		itemsMenuService.add(itemExportDailServiceList);
		itemsMenuService.add(itemImportAdditionalServiceList);
		itemsMenuService.add(itemExportAdditionalServiceList);
		
		itemsMenuService.add(itemBackMainMenu);

		// == MENU SERVICE ==
		Menu menuService = new Menu(TITLE_MENU_SERVICE, itemsMenuService);

		for (int i = 0; i < itemsMenuService.size() - 1; i++) {
			itemsMenuService.get(i).setMenu(menuService);
		}

		itemMenuService.setMenu(menuService);// item go to menu service

		// ITEMS MENU PRINT LIST SERVICE
		Item itemPrintDailServiceThemPriceSortedByName = new ItemPrintDailServiceThemPriceSortedByName(
				ITEM_PRINT_DAIL_SERVICE_SORTED_BY_NAME, admin);
		Item itemPrintDailServiceThemPriceSortedByPrice = new ItemPrintDailServiceThemPriceSortedByPrice(
				ITEM_PRINT_DAIL_SERVICE_SORTED_BY_PRICE, admin);
		Item itemPrintAdditionalServiceThemPriceSortedByName = new ItemPrintAdditionalServiceThemPriceSortedByName(
				ITEM_PRINT_ADD_SERVICE_SORTED_BY_NAME, admin);
		Item itemPrintAdditionalServiceThemPriceSortedByPrice = new ItemPrintAdditionalServiceThemPriceSortedByPrice(
				ITEM_PRINT_ADD_SERVICE_SORTED_BY_PRICE, admin);
		Item itemBackMenuService = new Item(ITEM_BACK_IN_MENU_SERVICE);

		List<Item> itemsMenuPrintService = new ArrayList<Item>();
		itemsMenuPrintService.add(itemPrintDailServiceThemPriceSortedByName);
		itemsMenuPrintService.add(itemPrintDailServiceThemPriceSortedByPrice);
		itemsMenuPrintService
				.add(itemPrintAdditionalServiceThemPriceSortedByName);
		itemsMenuPrintService
				.add(itemPrintAdditionalServiceThemPriceSortedByPrice);
		itemsMenuPrintService.add(itemBackMenuService);

		// == MENU PRINT SERVICE ==
		Menu menuPrintService = new Menu(TITLE_MENU_PRINT_SERVICE,
				itemsMenuPrintService);

		for (int i = 0; i < itemsMenuPrintService.size() - 1; i++) {
			itemsMenuPrintService.get(i).setMenu(menuPrintService);
		}
		itemPrintListService.setMenu(menuPrintService); // item go to menu for
														// print service
		itemBackMenuService.setMenu(menuService);

		return menuMain;

	}

}
