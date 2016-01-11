package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.danco.training.controller.factory.itemmenu.AbstractItem;

public class ItemRegistry extends AbstractItem{
	
	 public ItemRegistry() {

	}

	@Override
	public void work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));		
		
		try {
			System.out.println("Name...");
			String name = reader.readLine();
			System.out.println("Pasport...");
			String pasprot = reader.readLine();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}
