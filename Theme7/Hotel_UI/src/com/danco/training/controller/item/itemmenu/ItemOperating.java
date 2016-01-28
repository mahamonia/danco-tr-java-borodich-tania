package com.danco.training.controller.item.itemmenu;

import com.danco.api.IServiceAdmin;


public class ItemOperating extends Item {
	protected IServiceAdmin admin;

	public ItemOperating(String name, IServiceAdmin admin) {
		super(name);
		this.setAdmin(admin);
	}

	public IServiceAdmin getAdmin() {
		return admin;
	}

	public  void setAdmin(IServiceAdmin admin) {
		this.admin = admin;
	}
	

}
