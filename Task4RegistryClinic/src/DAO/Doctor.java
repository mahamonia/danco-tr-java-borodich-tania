package DAO;

import java.util.List;


public class Doctor extends Man{
	
	private String Type;
	private List <Patient> patientsList;

	public Doctor(String Name, int RegNumber, String Type, List <Patient> patientsList) {
		super(Name, RegNumber);
		
		this.Type = Type;
		this.patientsList = patientsList;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public List<Patient> getPatientsList() {
		return patientsList;
	}

	public void setPatientsList(List<Patient> patientsList) {
		this.patientsList = patientsList;
	}

	

}
