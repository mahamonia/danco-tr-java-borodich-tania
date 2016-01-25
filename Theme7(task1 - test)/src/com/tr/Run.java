package com.tr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Run {

	public static void main(String[] args) {
		

		Guest guest = new Guest(1, "Ivanov", "125");
		Order order = new Order(1, "Order", guest);
		
		// where used
		InformationAboutObject infoObgect = new InformationAboutObject(order);
		ProcessorAnnotation proc = new ProcessorAnnotation(infoObgect);
		proc.processAnnotation();
		
	}

}
