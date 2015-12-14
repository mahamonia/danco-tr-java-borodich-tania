package run;

import making.Addition;
import making.Record;
import etity.Doctor;
import etity.Patient;

public class Registry {
	
	public static void main(String[] args) {
		
		final String message1 = "Amount of patients records to ";
		final String message2 = "Amount of patients = ";
		final String message3 = "Amount of doctors = ";
		
		final int RegNumberDocTherapist = 23;
		final int RegNumberDocSurgeon = 51;
		
		final int AmountRecordsTherapist = 20;
		final int AmountRecordsSurgeon = 20;
		
		Doctor docTherapist = new Doctor ("Doctor1", RegNumberDocTherapist, "Therapist", AmountRecordsTherapist);
		Doctor docSurgeon = new Doctor("Doctor2", RegNumberDocSurgeon, "Surgeon", AmountRecordsSurgeon);
		
		Doctor [] doctors = {docTherapist, docSurgeon};
		
		Patient patient1 = new Patient("Patient1", 123);
		Patient patient2 = new Patient("Patient2", 125);
		Patient patient3 = new Patient("Patient3", 156);
		
		Patient [] patients = {patient1, patient2, patient3};
		
		Record record = new Record();
		record.recordDoctor(docTherapist, patient1);
		record.unRecordDoctor(docSurgeon, patient2);
		
		Addition add = new Addition();
		add.addPatient();
		add.addDoctor();
		
		System.out.println(message1 + docTherapist.getName() + " = " + docTherapist.getAmountRecords());
		System.out.println(message2 + patients.length);
		System.out.println(message3 + doctors.length);
	
		System.out.println();
		



	}

	

}
