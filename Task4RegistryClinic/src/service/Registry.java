package service;

import DAO.Clinic;
import DAO.Doctor;
import controller.Addition;
import controller.Record;

public class Registry {
	
	public Clinic clinic;
	public Record record;
	public Addition add;
	
	final String message1 = "Amount of patients records to ";
	final String message2 = "Amount of patients = ";
	final String message3 = "Amount of doctors = ";
	
	private int AmountPatientsRecords;
	private Doctor currentDoctor;
	
	public Registry (){	
	}

	public void working () {
		
		Clinic clinic = new Clinic();
		clinic.createDoctorPatient();
				
		Record record = new Record();
		record.recordDoctor(clinic.doctorsList.get(1), clinic.patientsList.get(1));
		record.unRecordDoctor(clinic.doctorsList.get(0), clinic.patientsList.get(0));
		
		record.recordDoctor(clinic.doctorsList.get(1), clinic.patientsList.get(0));
		
		Addition add = new Addition();
		add.addPatient(clinic.patientsList);
		add.addDoctor(clinic.doctorsList);
		
		AmountPatientsRecords = clinic.doctorsList.get(1).getPatientsList().size();
		currentDoctor = clinic.doctorsList.get(1);
		
		System.out.println(message1 + currentDoctor + " = " + AmountPatientsRecords);
		
		System.out.println(message2 + clinic.patientsList.size());
	    System.out.println(message3 + clinic.doctorsList.size());
	
		System.out.println();
		
	}

}
