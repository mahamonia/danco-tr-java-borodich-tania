package com.danco.client;

import java.net.InetAddress;
import java.net.Socket;

public class TestClient2 {
	public static void main(String[] args) throws Exception {
		int serverPort = 123; // 
        String address = "127.0.0.2"; // это IP-адрес компьютера, где исполняется наша серверная программа.
       
       InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
       
        Socket socket2 = new Socket(ipAddress, serverPort);
				
	}

}
