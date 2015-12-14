package entity;

import entity.IProductPart;


public class Tank implements IProduct {
	
	private IProductPart corps;
	private IProductPart engine;
	private IProductPart tower;
	
	public Tank (IProductPart corps, IProductPart engine, IProductPart tower){
		
		this.corps = corps;
		this.engine = engine;
		this.tower = tower;

	}

	@Override
	public void installFirstPart(IProductPart productPart) {
		System.out.println("install " + productPart);
	}

	@Override
	public void installSecondPart(IProductPart productPart) {
		System.out.println("install " + productPart);
	}

	@Override
	public void installThirdPart(IProductPart productPart) {
		System.out.println("install " + productPart);
	}

	@Override
	public String toString() {
		return "Tank";
	}

	
}
