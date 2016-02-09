package com.danco.training.controller.item.itemmenu;

import com.danco.api.ui.IProcessing;

public class ItemOperating extends Item {
	protected IProcessing processing;

	public ItemOperating(String name, IProcessing processing) {
		super(name);
		this.processing = processing;
	}

	public IProcessing getProcessing() {
		return processing;
	}

	public void setProcessing(IProcessing processing) {
		this.processing = processing;
	}


}
