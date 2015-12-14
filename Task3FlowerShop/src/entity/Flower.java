package entity;

public abstract class Flower{
	
	protected String Name;
	protected int amount;
	protected int price;
	
	public Flower (String Name, int amount, int price){
		this.Name = Name;
		this.amount = amount;
		this.price = price;
		
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
