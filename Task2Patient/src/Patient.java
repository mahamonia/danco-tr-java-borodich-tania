public class Patient extends Man{

private int RegistrNumber;
private Medication medication;
private Card card;
private Adress adress;

public Patient(){
System.out.println(Patient.class.getName());
}

public Medication getMedication() {
return medication;
}

public void setMedication(Medication medication) {
this.medication = medication;
}

public Card getCard() {
return card;
}
public void setCard(Card card) {
this.card = card;
}

public Adress getAdress() {
return adress;
}

public void setAdress(Adress adress) {
this.adress = adress;
}

public void RegistrationToDoctor(){
}

public void CancelVistitToDoctor(){
}

public void PayServices(){
}

}
