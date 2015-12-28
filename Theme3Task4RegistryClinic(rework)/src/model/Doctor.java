package model;

public class Doctor extends Man{
	
	private String Type;
	private Patient [] recordsPatient;
	private final int MaxRecords;

	public Doctor(String Name, int RegNumber, String Type, Patient [] recordsPatient) {
		super(Name, RegNumber);
		MaxRecords = recordsPatient.length;
		this.Type = Type;
		this.recordsPatient = recordsPatient;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Patient[] getRecordsPatient() {
		return recordsPatient;
	}

	public void setRecordsPatient(Patient[] recordsPatient) {
		this.recordsPatient = recordsPatient;
	}

	public int getMaxRecords() {
		return MaxRecords;
	}

	

}
