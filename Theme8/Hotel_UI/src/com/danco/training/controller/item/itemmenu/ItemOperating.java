package com.danco.training.controller.item.itemmenu;

import java.net.Socket;



public class ItemOperating extends Item {
	protected Socket socket;

	public ItemOperating(String name, Socket socket) {
		super(name);
		this.socket = socket;

	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}


}
