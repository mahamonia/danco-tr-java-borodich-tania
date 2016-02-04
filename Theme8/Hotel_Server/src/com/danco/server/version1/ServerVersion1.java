package com.danco.server.version1;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.services.ServiceAdmin;

public class ServerVersion1{

	private static Logger logger = LogManager.getLogger(ServerVersion1.class);

	public static void main(String[] args) {
		int port = 123;
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		ThreadForNewClient client = null;


		try {
			// загружаем программу HOTEL
			IServiceAdmin admin = new ServiceAdmin();
			DependencyInjection.getInstance().getDI(admin);
			admin.initData();
			
			ParseUtilityFromUiToBackEnd utility = new ParseUtilityFromUiToBackEnd(
					admin);
			
			try {
				int numberClient = 0;
				serverSocket = new ServerSocket(port);
				System.out.println("Waiting for a client...");

				while (true) {
					clientSocket = serverSocket.accept();
					System.out.println("Got a client ...");
					numberClient++;

					// создаем поток для подключившегося клиента
					client = new ThreadForNewClient(clientSocket, utility);
					DependencyInjection.getInstance().getDI(client);
					client.thread.start();
					client.thread.setName(numberClient+" Client");
					
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			} finally {
				admin.saveData();
				clientSocket.close();
				serverSocket.close();
				
			}		
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally{}
	}
}
