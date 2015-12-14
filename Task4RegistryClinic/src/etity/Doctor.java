package etity;


public class Doctor extends Man{
	
	private String Type;
	private int AmountRecords;

	public Doctor(String Name, int RegNumber, String Type,int AmountRecords) {
		super(Name, RegNumber);
		
		this.Type = Type;
		this.AmountRecords = AmountRecords;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getAmountRecords() {
		return AmountRecords;
	}

	public void setAmountRecords(int amountRecords) {
		AmountRecords = amountRecords;
	}
	
}
