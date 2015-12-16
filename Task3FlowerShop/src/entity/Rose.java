package entity;


public class Rose extends Flower{

	public Rose(String Name, int price) {
		super(Name, price);
		
	}
	
	@Override
	public String toString() {
		return "Rose";
	}

}
