package model;

import java.util.ArrayList;
import java.util.List;

public class Clinic {

	private List <Doctor> doctorsList = new ArrayList <Doctor> ();
	private List <Patient> patientsList = new ArrayList <Patient> ();
	
	public Clinic (){
			
}

	public List<Doctor> getDoctorsList() {
		return doctorsList;
	}

	public void setDoctorsList(List<Doctor> doctorsList) {
		this.doctorsList = doctorsList;
	}

	public List<Patient> getPatientsList() {
		return patientsList;
	}

	public void setPatientsList(List<Patient> patientsList) {
		this.patientsList = patientsList;
	}
	
	

}
