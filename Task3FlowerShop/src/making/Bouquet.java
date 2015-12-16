package making;

import entity.Flower;

public class Bouquet implements IBouquet {

	private Flower[] flower = new Flower[3];
	private int price;
	private int amountFlowers = 0;

	public Bouquet() {

	}

	public Flower[] getFlower() {
		return flower;
	}

	public void setFlower(Flower[] flower) {
		this.flower = flower;
	}

	public int getPrice() {

		for (int i = 0; i < flower.length; i++) {
			price += flower[i].getPrice();
		}

		return price;
	}

	@Override
	public void addFlower(Flower flower) {

		this.flower[amountFlowers] = flower;
		amountFlowers++;

		System.out.println("add ... " + flower);
	}

	@Override
	public String toString() {
		return "Bouqet";
	}

}
