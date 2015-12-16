package controller;

import DAO.Doctor;
import DAO.Patient;

public interface IRecord {
	
	public void recordDoctor(Doctor doctor, Patient patient);
	public void unRecordDoctor(Doctor doctor, Patient patient);

}
