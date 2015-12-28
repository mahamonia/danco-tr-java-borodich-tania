package controller;

import model.Doctor;
import model.Patient;

public class DoctorController {

	private Doctor[] doctors;
	private final int MAX_DOCTORS;

	public DoctorController(Doctor[] doctors) {
		MAX_DOCTORS = doctors.length;
		this.doctors = doctors;
	}
	
	public int getMaxValueDoctors() {
		return MAX_DOCTORS;
	}

	public void createDoctor(Doctor Doctor) {

		int i = getNumberForNewDoctor();
		doctors[i] = Doctor;

	}

	public Doctor getDoctor(int RegNumber) {

		int i = getNumberDoctorByRegNumber(RegNumber);
		if (i != -1) {
			return doctors[i];
		}
		return null;
	}
	
	public Doctor[] getDoctors() {
		return doctors;
	}

	public void updateDoctor(Doctor Doctor) {
		int i = getNumberDoctor(Doctor);
		if (i != -1) {
			doctors[i] = Doctor;
		}
	}

	public void deleteDoctor(Doctor Doctor) {
		int i = getNumberDoctor(Doctor);
		if (i != -1) {
			doctors[i] = null;
		}
	}
	
	public Patient [] getRecordsDoc(Doctor Doctor){
		return Doctor.getRecordsPatient();
		
	}
	
	public void setRecordsDoc(Doctor doctor, Patient [] recordsDoc){		
		doctor.setRecordsPatient(recordsDoc);
	
	}

	private int getNumberForNewDoctor() {

		for (int i = 0; i < doctors.length; i++) {
			if (doctors[i] == null) {
				return i;
			}
		}return -1;
	}

	private int getNumberDoctor(Doctor Doctor) {

		int NumberDoctor = getNumberDoctorByRegNumber(Doctor.getRegNumber());

		return NumberDoctor;
	}

	private int getNumberDoctorByRegNumber(int RegNumber) {
		for (int i = 0; i < this.doctors.length; i++) {
			if (doctors[i] != null && doctors[i].getRegNumber() == RegNumber) {
				return i;
			}
		}return -1;
	}

}
