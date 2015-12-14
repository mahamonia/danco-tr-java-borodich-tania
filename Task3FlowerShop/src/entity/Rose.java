package entity;


public class Rose extends Flower{

	public Rose(String Name, int amount, int price) {
		super(Name, amount, price);
		
	}
	
	@Override
	public String toString() {
		return "Rose";
	}

}
