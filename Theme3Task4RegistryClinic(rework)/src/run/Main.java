package run;

import controller.DoctorController;
import controller.PatientController;

import model.Doctor;
import model.Patient;
import service.Registry;

public class Main {
	
	public static final String message1 = "Amount of patients records to ";
	public static final String message2 = "Amount of patients = ";
	public static final String message3 = "Amount of doctors = ";

	public static void main(String[] args) {
		
		Patient patient1 = new Patient("Patient1", 123);
		Patient patient2 = new Patient("Patient2", 125);
		Patient patient3 = new Patient("Patient3", 156);
		
		Patient [] patients = new Patient [10];
		
		Patient [] recordsTherapist = new Patient [10];
		Patient [] recordsSurgeon = new Patient [10];


		Doctor docTherapist = new Doctor ("Doctor1", 11, "Therapist", recordsTherapist);
		Doctor docSurgeon = new Doctor("Doctor2", 22, "Surgeon", recordsSurgeon);
		
		Doctor [] doctors = new Doctor [10];

		//Clinic clinic = new Clinic();
		
		DoctorController docController = new DoctorController(doctors);
		PatientController patientController = new PatientController(patients);
		
		
		Registry registry = new Registry(docController, patientController);
		
		registry.addDoctor(docTherapist);
		registry.addDoctor(docSurgeon);
		registry.addPatient(patient1);
		registry.addPatient(patient2);
		registry.addPatient(patient3);
		
		registry.recordToDoctor(docSurgeon, patient1);
		registry.recordToDoctor(docSurgeon, patient3);
		
		System.out.println(message3 + registry.printAmountDoctors());
		System.out.println(message2 + registry.printAmountPatients());
		System.out.println(message1 + registry.printAmountPatientRecordToDoctor(docSurgeon));
		
		
		
	}

}
