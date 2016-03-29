package com.danco.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

	private final static Logger LOGGER = LogManager.getLogger(Server.class);
	private static boolean isWorking = true;

	public static void main(String[] args) {
		int port = 123;
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		ThreadForNewClient client = null;

		try {
			// loading HOTEL
			IServiceAdmin admin = new ServiceAdmin();
			DependencyInjection.getInstance().getDI(admin);
			// add admin in list created objects
			DependencyInjection.createdObjects.put(
					"com.danco.api.backend.IServiceAdmin", admin);
			admin.initData();

			ProtocolFromUiToBackEnd protocol = new ProtocolFromUiToBackEnd(
					admin);

			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for a client...");

			while (isWorking) {
				clientSocket = serverSocket.accept();
				System.out.println("Got a client ...");

				// create thread for connect client
				client = new ThreadForNewClient(clientSocket, protocol);
				DependencyInjection.getInstance().getDI(client);
				client.thread.start();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				clientSocket.close();
				serverSocket.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}
}
