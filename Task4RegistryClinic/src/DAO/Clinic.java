package DAO;

import java.util.ArrayList;
import java.util.List;

public class Clinic {

	public List <Doctor> doctorsList = new ArrayList <Doctor> ();
	public List <Patient> patientsList = new ArrayList <Patient> ();
	
	public Clinic (){
		
}
	public void createDoctorPatient (){
	
	Patient patient1 = new Patient("Patient1", 123);
	Patient patient2 = new Patient("Patient2", 125);
	Patient patient3 = new Patient("Patient3", 156);
	
	patientsList.add(patient1);
	patientsList.add(patient2);
	patientsList.add(patient3);
	
	
	List <Patient> recordsdocTherapistList = new ArrayList <Patient>();
	List <Patient> recordsdocSurgeonList = new ArrayList <Patient>();
	
	
	Doctor docTherapist = new Doctor ("Doctor1", 11, "Therapist", recordsdocTherapistList);
	Doctor docSurgeon = new Doctor("Doctor2", 22, "Surgeon", recordsdocSurgeonList);

	doctorsList.add(docTherapist);
	doctorsList.add(docSurgeon);
	}

}
