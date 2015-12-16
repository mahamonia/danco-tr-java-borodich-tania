package entity;

public abstract class Flower{
	
	protected String Name;
	protected int price;
	
	public Flower (String Name, int price){
		this.Name = Name;
		this.price = price;
		
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
