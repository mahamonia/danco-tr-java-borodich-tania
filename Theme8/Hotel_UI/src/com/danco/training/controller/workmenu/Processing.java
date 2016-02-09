package com.danco.training.controller.workmenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;

public class Processing implements IProcessing{
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private static final Logger LOGGER = LogManager.getLogger(Processing.class);
	
	public Processing(Socket socket){
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}		
	}
	@Override
	public Object dataProcessing(StringBuilder str){
        Object object = new Object();
		try {
			out.writeObject(str);
			out.flush();
			object = in.readObject();

			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			if (str == null){
				System.out.println("ERROR SERVER");
			}
		}				
		return object;		
	}
	@Override
	public void processingClose() {
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		
	
	}


}
