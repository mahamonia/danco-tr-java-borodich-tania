package com.danco.server.version1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.IWorkingMenu;

public class ThreadForNewClient implements Runnable {

	public Thread thread;
	@Injection
	private IWorkingMenu menu;

	private Socket clientSocket;
	private ParseUtilityFromUiToBackEnd utility;
	private boolean isAlive = true;
	private static Logger logger = LogManager
			.getLogger(ThreadForNewClient.class);

	public ThreadForNewClient(Socket clientSocket,
			ParseUtilityFromUiToBackEnd utility) {
		this.clientSocket = clientSocket;
		this.utility = utility;
		thread = new Thread(this, "New client");
	}

	@Override
	public void run() {
		try {
			DataInputStream in = null;
			DataOutputStream out = null;

			// ĞÀÁÎÒÀ ÑÅĞÂÅĞÀ Ñ ÊËÈÅÍÒÎÌ
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());

			String line = null;
			while (isAlive) {
				menu.workMenu(clientSocket);

				line = in.readUTF();
				if (line == null) {
					isAlive = false;
				} else {
					String rezult = utility.parseStringToObject(line)
							.toString();

					out.writeUTF(rezult); // îòïğàâëÿåì êëèåíòó
					out.flush();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
		}
	}

}
