package com.danco.client.version2;

import java.net.InetAddress;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.IWorkingMenu;

public class Client implements Runnable{
	
	public Thread thread;
	
	private static Logger logger = LogManager.getLogger(Client.class);
	@Injection
	private IWorkingMenu menu;
	
	public Client(){
		thread = new Thread(this, "Client");
	}

	@Override
	public void run() {
		int serverPort = 123; // 
        String address = "127.0.0.1"; // ��� IP-����� ����������, ��� ����������� ���� ��������� ���������. 
                                      
        try {
            InetAddress ipAddress = InetAddress.getByName(address); // ������� ������ ������� ���������� ������������� IP-�����.
           
            Socket socket = new Socket(ipAddress, serverPort); 
            
            menu.workMenu(socket);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }		
	}
}
