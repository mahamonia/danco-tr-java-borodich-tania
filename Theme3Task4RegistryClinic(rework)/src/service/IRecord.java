package service;

import model.Doctor;
import model.Patient;

public interface IRecord {
	
	public void recordToDoctor(Doctor doctor, Patient patient);
	public void unRecordToDoctor(Doctor doctor, Patient patient);

}
