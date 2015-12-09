public class Medication {

private String Kind;
private String Description;

public Medication(){
System.out.println(Medication.class.getName());
}

public void PrescribeMedication(){
}

public void CancelMedication(){
}

@Override
public String toString(){
return "Medication";
}

}