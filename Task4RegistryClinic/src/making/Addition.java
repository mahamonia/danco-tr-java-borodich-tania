package making;

import etity.Doctor;
import etity.Patient;

public class Addition implements IAddition{
	
	public Addition(){
		
	}

	@Override
	public void addDoctor() {
		
		Doctor newDoctor = new Doctor("Doctor3", 65, "Surgeon", 0);
		
		System.out.println(newDoctor.getName() + " add");
		
	}

	@Override
	public void addPatient() {
		
		Patient newPatient = new Patient("Patient4", 125);
	
		System.out.println(newPatient.getName() + " add");
		
	}
	
}
