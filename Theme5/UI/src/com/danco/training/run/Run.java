package com.danco.training.run;

import java.io.IOException;

import com.danco.training.controller.workmenu.WorkingMenu;

public class Run {

	public static void main(String[] args) throws IOException {
		
		WorkingMenu menu = new WorkingMenu();
		menu.workMenu();
	}

}
