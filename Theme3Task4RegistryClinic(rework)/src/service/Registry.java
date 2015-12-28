package service;

import model.Doctor;
import model.Patient;
import controller.DoctorController;
import controller.PatientController;

public class Registry implements IAddition, IRecord {

	private DoctorController docController;
	private PatientController patientController;

	private int AmountPatientsRecords;
	private int AmountPatients;
	private int AmountDoctors;

	public Registry(DoctorController docController,
			PatientController patientController) {
		this.docController = docController;
		this.patientController = patientController;
	}

	@Override
	public void recordToDoctor(Doctor doctor, Patient patient) {

		Patient[] records = docController.getRecordsDoc(doctor);

		for (int i = 0; i < records.length; i++) {
			if (records[i] == null && records[i] != patient) {
				records[i] = patient;
				break;
			}
		}
		docController.setRecordsDoc(doctor, records);
		System.out.println(patient + " record to " + doctor);
	}

	@Override
	public void unRecordToDoctor(Doctor doctor, Patient patient) {

		Patient[] records = docController.getRecordsDoc(doctor);

		for (int i = 0; i < records.length; i++) {
			if (records[i] == patient) {
				records[i] = null;
			}
		}
		docController.setRecordsDoc(doctor, records);
		System.out.println(patient + " UNrecord to " + doctor);
	}

	@Override
	public void addDoctor(Doctor Doctor) {

		docController.createDoctor(Doctor);
	}

	@Override
	public void addPatient(Patient Patient) {

		patientController.createPatient(Patient);
	}

	public int printAmountPatientRecordToDoctor(Doctor Doctor) {

		Patient[] records = docController.getRecordsDoc(Doctor);

		for (int i = 0; i < records.length; i++) {
			if (records[i] != null) {
				AmountPatientsRecords++;
			}
		}
		return AmountPatientsRecords;
	}

	public int printAmountPatients() {

		for (int i = 0; i < patientController.getPatients().length; i++) {
			if (patientController.getPatients()[i] != null) {
				AmountPatients++;
			}
		}
		return AmountPatients;
	}

	public int printAmountDoctors() {

		for (int i = 0; i < docController.getDoctors().length; i++) {
			if (docController.getDoctors()[i] != null) {
				AmountDoctors++;
			}
		}
		return AmountDoctors;
	}
}
