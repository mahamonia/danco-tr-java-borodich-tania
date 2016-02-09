package com.danco.client;

import java.net.InetAddress;
import java.net.Socket;

import com.danco.api.ui.IWorkingMenu;
import com.danco.training.controller.workmenu.WorkingMenu;

public class TestClient1 {

	public static void main(String[] args) throws Exception {
		int serverPort = 123; //
		String address = "127.0.0.1"; // это IP-адрес компьютера, где
										// исполняется наша серверная программа.

		Socket clientSocket = new Socket(InetAddress.getByName(address), serverPort);

		IWorkingMenu menu = new WorkingMenu();
		menu.workMenu(clientSocket);	

	}
}
