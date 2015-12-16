package controller;

import java.util.ArrayList;
import java.util.List;

import DAO.Doctor;
import DAO.Patient;

public class Addition implements IAddition {

	public Addition() {

	}

	@Override
	public void addDoctor(List<Doctor> doctorsList) {

		List<Patient> recordsNewDoc = new ArrayList<Patient>();

		Doctor newDoctor = new Doctor("Doctor3", 65, "Surgeon", recordsNewDoc);

		doctorsList.add(newDoctor);

		System.out.println(" add doctor " + newDoctor.getName());

	}

	@Override
	public void addPatient(List<Patient> patientsList) {

		Patient newPatient = new Patient("Patient4", 125);

		patientsList.add(newPatient);

		System.out.println(" add patient " + newPatient.getName());

	}

}
