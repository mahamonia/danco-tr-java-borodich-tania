package controller;

import model.Patient;

public class PatientController {

	private Patient[] patients;
	private final int MAX_PATIENS;

	public PatientController(Patient[] patients) {
		MAX_PATIENS = patients.length;
		this.patients = patients;

	}

	public void createPatient(Patient Patient) {

		int i = getNumberForNewPatient();
		patients[i] = Patient;

	}

	public Patient getPatient(int RegNumber) {

		int i = getNumberPatientByRegNumber(RegNumber);
		if (i != -1) {
			return patients[i];
		}
		return null;
	}

	public Patient[] getPatients() {
		return patients;
	}

	public void updatePatient(Patient Patient) {
		int i = getNumberPatient(Patient);
		if (i != -1) {
			patients[i] = Patient;
		}
	}

	public void deletePatient(Patient Patient) {
		int i = getNumberPatient(Patient);
		if (i != -1) {
			patients[i] = null;
		}
	}

	private int getNumberForNewPatient() {

		for (int i = 0; i < patients.length; i++) {
			if (patients[i] == null) {
				return i;
			}
		}
		return -1;
	}

	private int getNumberPatient(Patient Patient) {

		int NumberDoctor = getNumberPatientByRegNumber(Patient.getRegNumber());

		return NumberDoctor;
	}

	private int getNumberPatientByRegNumber(int RegNumber) {
		for (int i = 0; i < this.patients.length; i++) {
			if (patients[i] != null && patients[i].getRegNumber() == RegNumber) {
				return i;
			}
		}
		return -1;
	}

	public int getMaxValuePatients() {
		return MAX_PATIENS;
	}
}
