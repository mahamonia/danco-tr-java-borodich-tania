package controller;

import java.util.List;

import DAO.Doctor;
import DAO.Patient;

public interface IAddition {
	
	public void addDoctor(List <Doctor> doctorsList);
	public void addPatient(List <Patient> patientsList);

}
