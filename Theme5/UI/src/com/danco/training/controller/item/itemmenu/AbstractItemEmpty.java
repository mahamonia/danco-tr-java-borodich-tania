package com.danco.training.controller.item.itemmenu;

import com.danco.training.services.ServiceAdmin;

public abstract class  AbstractItemEmpty extends AbstractItem{
	protected ServiceAdmin admin;

	public AbstractItemEmpty(String name, ServiceAdmin admin) {
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
