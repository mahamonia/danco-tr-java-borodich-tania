package making;

import entity.Aster;
import entity.Chamomile;
import entity.Rose;

public class Bouquet implements IBouquet{

	private int price;

	public Bouquet() {

	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void addRose(Rose rose, int amount) {
		System.out.println("add rose ... "+ amount);
		
	}

	@Override
	public void addAster(Aster aster, int amount) {
		System.out.println("add aster ... "+ amount);
		
	}

	@Override
	public void addChamomile(Chamomile chamomile, int amount) {
		System.out.println("add chamomile ... "+ amount);
		
	}

	

}
