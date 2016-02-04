package com.danco.server.version2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IServiceAdmin;
import com.danco.client.version2.Client;
import com.danco.dependency.DependencyInjection;
import com.danco.services.ServiceAdmin;

public class ServerVersion2 {
	private static Logger logger = LogManager.getLogger(ServerVersion2.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int port = 123;
		try {
			// загружаем программу HOTEL
			IServiceAdmin admin = new ServiceAdmin();
			DependencyInjection.getInstance().getDI(admin);
			admin.initData();

			ParseUtilityFromUiToBackEnd utility = new ParseUtilityFromUiToBackEnd(
					admin);
			
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Waiting for a client...");

			// подключается клиент
			Client client = new Client();
			DependencyInjection.getInstance().getDI(client);
			client.thread.start();
			
			Socket socket = ss.accept();
			System.out.println("Got a client ...");
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());

			String line = null;

			while (client.thread.isAlive()) {

				line = in.readUTF();
				String rezult = utility.parseStringToObject(line).toString();

				out.writeUTF(rezult); // отправляем клиенту
				out.flush();
			}
			client.thread.join();
			admin.saveData();
			socket.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
