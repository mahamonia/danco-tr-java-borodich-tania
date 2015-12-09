public class Main {

public static void main(String[] args) {

  Patient patient1= new Patient();
  Medication medication1 = new Medication();
  Card card1 = new Card();
  Adress adress1 = new Adress(); 

  patient1.setMedication(medication1);
  patient1.setCard(card1);
  patient1.setAdress(adress1);
  
  System.out.print("Patient have ");
  System.out.print(patient1.getMedication()+", ");
  System.out.print(patient1.getCard()+", ");
  System.out.print(patient1.getAdress()+", ");
}
}