package making;

import etity.Doctor;
import etity.Patient;

public class Record implements IRecord{
	
	public Record(){
		
	}

	@Override
	public void recordDoctor(Doctor doctor, Patient patient) {
		doctor.setAmountRecords(doctor.getAmountRecords()+1);
		System.out.println(patient.getName() + " record to doctor "+ doctor.getName());
		
	}

	@Override
	public void unRecordDoctor(Doctor doctor, Patient patient) {
		doctor.setAmountRecords(doctor.getAmountRecords()-1);
		System.out.println(patient.getName() + " unrecord to doctor "+ doctor.getName());
		
	}

}
