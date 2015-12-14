package making;

import etity.Doctor;
import etity.Patient;

public interface IRecord {
	
	public void recordDoctor(Doctor doctor, Patient patient);
	public void unRecordDoctor(Doctor doctor, Patient patient);

}
