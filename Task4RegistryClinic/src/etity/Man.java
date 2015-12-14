package etity;

public abstract class Man {
	
	protected String Name;
	protected int RegNumber;
	
	public Man (String Name, int RegNumber){
		this.Name = Name;
		this.RegNumber = RegNumber;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getRegNumber() {
		return RegNumber;
	}

	public void setRegNumber(int regNumber) {
		RegNumber = regNumber;
	}
	

}
