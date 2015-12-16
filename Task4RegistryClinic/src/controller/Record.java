package controller;

import DAO.Doctor;
import DAO.Patient;

public class Record implements IRecord {

	public Record() {

	}

	@Override
	public void recordDoctor(Doctor doctor, Patient patient) {

		doctor.getPatientsList().add(patient);

		System.out.println(patient.getName() + " record to doctor "
				+ doctor.getName());

	}

	@Override
	public void unRecordDoctor(Doctor doctor, Patient patient) {

		doctor.getPatientsList().remove(patient);

		System.out.println(patient.getName() + " unrecord to doctor "
				+ doctor.getName());

	}

}
