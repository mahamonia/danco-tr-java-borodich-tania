
public class Main {

	public static void main(String[] args) {
		
		String message1 = "Generated number ";
		String mesage2 = "Maximum numeral of number ";
		
		NumberOfThree number = new NumberOfThree();
		
		System.out.println(message1+number.getNumber());
		
		System.out.println(mesage2+number.MaxNumeral(number.getNumber()));

	}

}
