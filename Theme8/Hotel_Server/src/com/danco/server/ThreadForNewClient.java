package com.danco.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadForNewClient implements Runnable {

	public Thread thread;
	
	private Socket clientSocket;
	private ProtocolFromUiToBackEnd protocol;
	private boolean isAlive = true;
	private static Logger logger = LogManager
			.getLogger(ThreadForNewClient.class);

	public ThreadForNewClient(Socket clientSocket,
			ProtocolFromUiToBackEnd protocol) {
		this.clientSocket = clientSocket;
		this.protocol = protocol;
		this.thread = new Thread(this, "New client");
	}

	@Override
	public void run() {
		String line = null;
		try {			
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());			
			
			// WORK SERVER WITH CLIENT
								
			while (isAlive) {			
				line = in.readObject().toString(); // data with ui
				if (line == null) {
					isAlive = false;
				} else {
					// processing data and send of client
					out.writeObject(protocol.parseStringToObject(line));
					out.flush();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			// when client exits - save data
			line = "0"+";"+"saveData";
			protocol.parseStringToObject(line);
		}
	}

}
