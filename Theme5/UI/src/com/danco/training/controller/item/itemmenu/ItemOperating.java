package com.danco.training.controller.item.itemmenu;


import com.danco.training.services.ServiceAdmin;

public class ItemOperating extends Item {
	protected ServiceAdmin admin;

	public ItemOperating(String name, ServiceAdmin admin) {
		super(name);
		this.setAdmin(admin);
	}

	public ServiceAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(ServiceAdmin admin) {
		this.admin = admin;
	}
	

}
