package com.danco.client;

import java.net.InetAddress;
import java.net.Socket;

public class TestClient2 {
	public static void main(String[] args) throws Exception {
		int serverPort = 123; // 
        String address = "127.0.0.2"; // ��� IP-����� ����������, ��� ����������� ���� ��������� ���������.
       
       InetAddress ipAddress = InetAddress.getByName(address); // ������� ������ ������� ���������� ������������� IP-�����.
       
        Socket socket2 = new Socket(ipAddress, serverPort);
				
	}

}
